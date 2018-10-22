package com.rfxdevelop.utils.copy.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 中文编码工具类
 *copy from com.wealthlake.common.util
 * @author rsh
 * @Description
 * @Date: 2018/4/23
 * @Time: 9:59
 */
public class ChineseUtill {

    private static Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");

    /**
     * 是否中文
     *
     * @param c
     * @return
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 是否乱码
     *
     * @param strName
     * @return
     */
    public static boolean isMessyCode(String strName) {
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
                chLength++;
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ISO8859-1 转 UTF-8
     *
     * @param msg
     * @return
     */
    public static String toChinese(String msg) {
        if (msg != null && isMessyCode(msg)) {
            try {
                return new String(msg.getBytes("ISO8859-1"), "UTF-8");
            } catch (Exception e) {
            }
        }
        return msg;
    }

    public static void main(String[] args) {
        System.out.println(toChinese("\u6d4b\u8bd5"));
        System.out.println(toChinese("测试"));
    }

}
