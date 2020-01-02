package com.wisewintech.base.entity.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * (MaBrand)实体类
 *
 * @author makejava
 * @since 2019-12-31 10:04:56
 */
public class BrandBO implements Serializable {
    private static final long serialVersionUID = 654858461621039977L;
    /**
    * 品牌
    */
    private Long id;
    /**
    * 名称
    */
    private String name;
    /**
    * 状态
    */
    private String status;
    /**
    * 图标
    */
    private String icon;
    
    private Date createTime;
    
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}