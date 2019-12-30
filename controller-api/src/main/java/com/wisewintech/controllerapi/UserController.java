package com.wisewintech.controllerapi;

import com.wisewintech.base.entity.bo.UserBO;
import com.wisewintech.base.util.RedisUtil;
import com.wisewintech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;
    @RequestMapping("/getUserBOList")
    public String getUserBOList(HttpServletResponse response, HttpServletRequest request) {
        String str = "";
        List<UserBO> userBOS = userService.getUserBOList();
        for (UserBO user : userBOS) { 
            System.out.println(user.getName());
            str=str+user.getName()+"666";
        }
        redisUtil.set("user",str,10000);
        return redisUtil.get("user").toString();
    }

}
