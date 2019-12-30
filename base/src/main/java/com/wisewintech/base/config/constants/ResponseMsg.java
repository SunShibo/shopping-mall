package com.wisewintech.base.config.constants;

import java.util.HashMap;
import java.util.Map;

public class ResponseMsg {
    public static Map<String,String> msg;
    static {
        msg=new HashMap<>();
        //00开头标识公用
        msg.put("00000","成功");
        msg.put("00001","参数异常");
        msg.put("00002","用户未登录");
        msg.put("00003","发生异常请稍后从试");


        //01开头系统用户相关
        msg.put("01000","密码错误");
        msg.put("01001","账号已存在");
        msg.put("01002","用户不存在");
        msg.put("01003","密码不能与之前一样");
        msg.put("01004","该角色已存在");
        msg.put("01005","该角色下存在用户");

        //02开头用户相关
        msg.put("02000","手机号码格式错误");
        msg.put("02001","验证码过期");
        msg.put("02002","验证码错误");
        msg.put("02003","账号异常");
        msg.put("02004","未绑定手机");

    }

}