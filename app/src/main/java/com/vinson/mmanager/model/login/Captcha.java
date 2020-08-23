package com.vinson.mmanager.model.login;

public class Captcha {
    String captchaId;
    String picPath;

    public Captcha(String captchaId, String picPath) {
        this.captchaId = captchaId;
        this.picPath = picPath;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "captchaId='" + captchaId + '\'' +
                ", picPath='" + picPath + '\'' +
                '}';
    }
}
