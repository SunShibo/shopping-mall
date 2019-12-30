package com.wisewintech.base.entity.bo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(MaUser)实体类
 *
 * @author makejava
 * @since 2019-12-30 14:15:33
 */
public class UserBO implements Serializable {
    private static final long serialVersionUID = 950013083765825426L;
    
    private Long id;
    /**
    * 用户名称
    */
    private String username;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 性别
    */
    private String gender;
    /**
    * 生日
    */
    private Object birthday;
    /**
    * 最近一次登录时间
    */
    private Date lastLoginTime;
    /**
    * 最近一次登录IP地址
    */
    private String lastLoginIp;
    /**
    * 用户昵称或网络名称
    */
    private String nickname;
    /**
    * 用户手机号码
    */
    private String mobile;
    /**
    * 用户头像图片
    */
    private String avatar;
    
    private String qqOpenid;
    /**
    * 微信登录openid
    */
    private String weixinOpenid;
    /**
    * usable 可用, block 禁用, logout 注销
    */
    private String status;
    /**
    * 创建时间
    */
    private Date addTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 逻辑删除
    */
    private String deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

}