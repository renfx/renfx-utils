package com.renfxdevelop.utils.original.excel;




import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by renfxdevelop on 2017/5/10.
 */
public class QrCodeUtil {
    private static String key = "45d135153d13sdfi(*(*&32jijd";
    public static int timeRange= 1800;//30分钟

    /**
     * 生成opt 精度到秒
     * @param time 毫秒数
     * @return otp
     */
    public static String gensOtp(long time){
        String timeStr= time/1000+key;
        String hashCodeOtp = String.valueOf(Math.abs(timeStr.hashCode()));

        String otp = "";
        if(hashCodeOtp.length()>=5){
            otp = hashCodeOtp.substring(hashCodeOtp.length()-5,hashCodeOtp.length());
        }
        return otp ;
    }

    /**
     * 加密二维码
     * @param qrCode 参考QrCode model
     * @return 加密后的二维码
     */
    public static String encode(QrCode qrCode){
        Random random = new Random();
        int n = random.nextInt(9)+1;
        StringBuilder builder = new StringBuilder();
        builder.append(qrCode.getId());
        long code = Long.parseLong(builder.toString());
        String otp = gensOtp((qrCode.getDate() == null ? new Date() : qrCode.getDate()).getTime());
        builder = new StringBuilder();
        builder.append(code * n).append(otp).append(n);
        qrCode.setOtp(otp);
        return String.valueOf(builder.toString());
    }

    /**
     *  解密二维码
     * @param codeStr 客户端传入的二维码信息
     * @return 解密后的二维码
     */
    public static QrCode decode(String codeStr){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(codeStr);
        if(!isNum.matches()) return null;
        String idEncode = codeStr.substring(0, codeStr.length() - 6);
        String otp = codeStr.substring(codeStr.length() - 6, codeStr.length() - 1);
        int n = Integer.parseInt(codeStr.substring(codeStr.length()-1, codeStr.length()));
        long code = Long.parseLong(idEncode)/n;
        String codeBefore = String.valueOf(code);
        int id = Integer.parseInt(codeBefore);

        QrCode qrCode = new QrCode();
        qrCode.setOtp(otp);
        qrCode.setId(id);
        return qrCode;
    }


    /**
     * 校验otp
     * @param otp
     * @return 是否校验通过
     */
    public static boolean checkOtp(String otp){
        return checkOtp(otp,timeRange);
    }

    /**
     * 校验otp
     * @param otp
     * @return 是否校验通过
     */
    public static boolean checkOtp(String otp ,int timeRange){
        long time = new Date().getTime();
        for (long timeGens = time/1000-timeRange; timeGens < time/1000+timeRange ; timeGens++) {
            String optGens = gensOtp(timeGens * 1000);
            if(optGens.equals(otp)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
//        System.out.println(gensOtp(new Date().getTime()));

    }
    public static class QrCode {
        private int id; //店铺或者用户id
        private Date date;//日期 默认当前日期
        private String otp;//用于解密时验证

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }


    }

}

