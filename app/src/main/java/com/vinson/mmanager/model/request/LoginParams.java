package com.vinson.mmanager.model.request;

import java.util.Map;

public class LoginParams {
    String phoneNumber;
    String password;
    String captcha;
    String captchaId;

    public LoginParams() {
        this.phoneNumber = null;
        this.password = null;
        this.captcha = null;
        this.captchaId = null;
    }

    public LoginParams(String phoneNumber, String password, String captcha, String captchaId) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.captcha = captcha;
        this.captchaId = captchaId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String verify()  {
        if (phoneNumber == null || password == null || captcha == null || captchaId == null) {
            return "输入不能为空";
        }
        return null;
    }
}
