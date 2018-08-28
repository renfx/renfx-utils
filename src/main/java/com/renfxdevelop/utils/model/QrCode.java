package com.renfxdevelop.utils.model;

import java.util.Date;

/**
 * Created by renfxdevelop on 2017/5/11.
 */
public class QrCode {
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
