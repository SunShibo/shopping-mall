package com.wisewintech.base.config.constants;

public enum Constants {

    /*正常状态*/YES("yes"),
    /*删除|异常状态*/NO("no"),
    /*账号正常*/USABLE("usable"),
    /*QQ*/QQ("qq"),
    /*微信*/WEIXIN("weixin"),
    /*验证码后缀*/CAPTCHA("captcha");

    Constants(String value) {
        this.value = value;
    }
    private String value;

    public String getValue() {
        return value;
    }

}
