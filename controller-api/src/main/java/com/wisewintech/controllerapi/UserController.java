package com.wisewintech.controllerapi;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.CommonResponse;
import com.wisewintech.base.config.constants.Constants;
import com.wisewintech.base.entity.bo.entity.UserBO;
import com.wisewintech.base.entity.dto.ResultDTO;
import com.wisewintech.base.entity.dto.ResultDTOBuilder;
import com.wisewintech.base.util.AccountValidatorUtil;
import com.wisewintech.base.util.RedisUtil;
import com.wisewintech.base.util.RequestIP;
import com.wisewintech.base.util.SendMessageUtil;
import com.wisewintech.controllerapi.base.BaseCotroller;
import com.wisewintech.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController extends BaseCotroller {
    static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 发送验证码
     * 登陆注册
     * 修改绑定
     *
     * @param phone
     * @return
     */
    @RequestMapping("/send")
    public ResultDTO send(String phone, String templateCode, HttpServletRequest request, HttpServletResponse response) {
        log.info("start...........send.................");
        if (!verifyParam(phone, templateCode)) {
            return ResultDTOBuilder.failure("00001");
        }
        //手机号非空+格式判断
        if (!AccountValidatorUtil.isMobile(phone)) {
            return ResultDTOBuilder.failure("02000");
        }
        String captcha = String.valueOf(RandomUtil.randomInt(100000, 999999));
        CommonResponse commonResponse = SendMessageUtil.sendSignInCodeMessage(phone, captcha, null);
        redisUtil.set(phone + Constants.CAPTCHA.getValue(), captcha, 60 * 5);  //有效期
        return ResultDTOBuilder.success(commonResponse);
    }

    /**
     * 手机号验证通过,对比用户验证码,修改绑定手机号
     *
     * @param phone
     * @param verify 用户验证码
     * @return
     */
    @RequestMapping("/updatePhone")
    public ResultDTO updatePhone(String phone, String verify, HttpServletResponse response, HttpServletRequest request) {
        log.info("start..........updatePhone..................................");
        if (!verifyParam(phone, verify)) {
            return ResultDTOBuilder.failure("00001");
        }
        if (!AccountValidatorUtil.isMobile(phone)) {
            return ResultDTOBuilder.failure("02000");
        }
        Object o = redisUtil.get(phone + Constants.CAPTCHA.getValue());
        if (o == null) {
            return ResultDTOBuilder.failure("02001");
        }
        String mobileAuthCode = String.valueOf(o);
        if (!verify.equals(mobileAuthCode)) {
            return ResultDTOBuilder.failure("02002");
        }
        UserBO loginUser = super.getLoginUser(request);
        UserBO update = userService.update(loginUser);
        return ResultDTOBuilder.success(update);
    }


    /**
     * 登录即注册
     *
     * @param phone
     * @param verify 用户验证码
     * @return
     */
    @RequestMapping("/register")
    public ResultDTO register(String phone, String verify, HttpServletResponse response, HttpServletRequest request) {
        log.info("start.....................register.....................................");
        //用户传参非空判断
        if (!verifyParam(verify, phone)) {
            return ResultDTOBuilder.failure("00001");
        }
        if (!AccountValidatorUtil.isMobile(phone)) {
            return ResultDTOBuilder.failure("02000");
        }
        //获取Redis中的用户验证码
        Object o = redisUtil.get(phone + Constants.CAPTCHA.getValue());
        if (o == null) {
            return ResultDTOBuilder.failure("02001");
        }
        String mobileAuthCode = String.valueOf(o);
        log.info("获取Redis中的用户验证码:{}", mobileAuthCode);
        Map<String, Object> mapUser = new HashMap<String, Object>();
        //如果和用户收到的验证码相同
        if (!verify.equals(mobileAuthCode)) {
            return ResultDTOBuilder.failure("02002");
        }
        //通过手机号查询表中是否有该用户
        log.info("通过手机号查询表中是否有该用户");
        UserBO userBO = userService.queryByPhone(phone);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (userBO != null) {
            if (Constants.USABLE.getValue().equals(userBO.getStatus())) {
                userBO.setLastLoginTime(new Date());
                userBO.setLastLoginIp(RequestIP.getIPAddress(request));
                userService.update(userBO);
                super.putLoginUser(userBO, response);
            }
            resultMap.put("isLogin", Constants.YES.getValue());
            resultMap.put("user", userBO);
            return ResultDTOBuilder.failure("02003");
        } else {
            //注册
            UserBO newUser = new UserBO();
            //设置默认昵称
            newUser.setNickname("用户_" + phone);
            newUser.setMobile(phone);
            newUser.setLastLoginTime(new Date());
            newUser.setLastLoginIp(RequestIP.getIPAddress(request));
            newUser.setAddTime(new Date());
            newUser.setStatus(Constants.USABLE.getValue());
            userService.insert(newUser);
            super.putLoginUser(newUser, response);
            resultMap.put("isLogin", Constants.NO.getValue());
            resultMap.put("user", newUser);
        }
        return ResultDTOBuilder.success(resultMap);
    }


    /**
     * QQ and 微信登陆
     * param openid为微信账号 或者qq号
     * status代表微信或者qq
     *
     * @return
     */
    @RequestMapping("/openidLogin")
    public ResultDTO openidLogin(HttpServletRequest request, HttpServletResponse response, String openid, String status) {
        log.info("start...............openidLogin..........................................");
        //参数验证
        if (!verifyParam(openid, status)) {
            return ResultDTOBuilder.failure("00001");
        }
        log.info("获取到和openid绑定的手机号");
        //获取到和openid绑定的手机号
        String mobile = userService.queryByOpenid(openid, status);
        log.info("com.wisewin.api.service.UserService.checkBind返回{}", mobile);
        if (mobile == null) {
            return ResultDTOBuilder.failure("02004");
        }
        //返回给前端的数据
        Map<String, Object> resultMap = new HashMap<String, Object>();
        UserBO userBO = userService.queryByPhone(mobile);
        if (!Constants.USABLE.getValue().equals(userBO.getStatus())) {
            return ResultDTOBuilder.failure("02003");
        }
        userBO.setLastLoginTime(new Date());
        userBO.setLastLoginIp(RequestIP.getIPAddress(request));
        userService.update(userBO);
        super.putLoginUser(userBO, response);
        resultMap.put("isLogin", Constants.YES.getValue());
        resultMap.put("user", userBO);
        return ResultDTOBuilder.success(userBO);
    }


    /**
     * 绑定openid 用户注册 登陆
     *
     * @return
     */
    @RequestMapping("/bindOpenidLogin")
    public ResultDTO bindOpenidLogin(String phone, String verify, String status, String openid, HttpServletRequest request, HttpServletResponse response) {
        log.info("start***********************bindOpenidLogin***********************");
        //用户传参非空判断
        if (verifyParam(phone, verify, status, openid)) {
            return ResultDTOBuilder.failure("00001");
        }
        if (!AccountValidatorUtil.isMobile(phone)) {
            return ResultDTOBuilder.failure("02000");
        }
        Object o = redisUtil.get(phone + Constants.CAPTCHA.getValue());
        if (o == null) {
            return ResultDTOBuilder.failure("02001");
        }
        String mobileAuthCode = String.valueOf(o);

        if (!verify.equals(mobileAuthCode)) {
            return ResultDTOBuilder.failure("02002");
        }

        UserBO userBO = userService.queryByPhone(phone);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (userBO != null) {
            if (Constants.USABLE.getValue().equals(userBO.getStatus())) {
                userBO.setLastLoginTime(new Date());
                userBO.setLastLoginIp(RequestIP.getIPAddress(request));
                if (status.equals(Constants.QQ.getValue())) {
                    userBO.setQqOpenid(openid);
                } else if (status.equals(Constants.WEIXIN.getValue())) {
                    userBO.setWeixinOpenid(openid);
                }
                userBO.setLastLoginIp(RequestIP.getIPAddress(request));
                userService.update(userBO);
                super.putLoginUser(userBO, response);
            }
            resultMap.put("isLogin", Constants.YES.getValue());
            resultMap.put("user", userBO);
            return ResultDTOBuilder.failure("02003");
        } else {
            //注册
            UserBO newUser = new UserBO();
            //设置默认昵称
            newUser.setNickname("用户_" + phone);
            newUser.setMobile(phone);
            newUser.setLastLoginTime(new Date());
            newUser.setLastLoginIp(RequestIP.getIPAddress(request));
            newUser.setAddTime(new Date());
            newUser.setStatus(Constants.USABLE.getValue());
            if (status.equals(Constants.QQ.getValue())) {
                newUser.setQqOpenid(openid);
            } else if (status.equals(Constants.WEIXIN.getValue())) {
                newUser.setWeixinOpenid(openid);
            }
            userService.insert(newUser);
            super.putLoginUser(newUser, response);
            resultMap.put("isLogin", Constants.NO.getValue());
            resultMap.put("user", newUser);
        }
        return ResultDTOBuilder.success(resultMap);
    }


    /**
     * 解除绑定
     * @return
     */
    @RequestMapping("/removeOpenid")
    public ResultDTO removeOpenid(String status, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中获取他的user对象的id
        UserBO loginUser = super.getLoginUser(request);
        //判断参数
        if (StringUtils.isEmpty(status)) {
            return ResultDTOBuilder.failure("00001");
        }
        userService.removeOpenId(loginUser.getId(),status);
        return ResultDTOBuilder.success();
    }


    /**
     * 修改用户信息
     *  @param response
     * @param request
     * @return
     */
    @RequestMapping("/update")
    public ResultDTO updateUser(HttpServletResponse response, HttpServletRequest request, UserBO userParam) {
        UserBO loginUser = super.getLoginUser(request);
        userParam.setId(loginUser.getId());
        userService.update(userParam);
        return ResultDTOBuilder.success(userParam);
    }

    /**
     * 查询用户信息
     *
     * @param response
     * @param request
     * @throws Exception
     * @return
     */
    @RequestMapping("/selectUser")
    public ResultDTO<UserBO> selectUser(HttpServletResponse response, HttpServletRequest request) throws Exception {
        UserBO user = this.getLoginUser(request);
        return ResultDTOBuilder.success(user);
    }

}