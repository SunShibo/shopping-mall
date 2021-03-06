package com.wisewintech.base.entity.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单信息
 */
public class MenuBO {
    private Integer id; // 菜单

    private String menuName; // 菜单名称

    private Integer pid; // 父id

    private String url;//菜单路径

    private String path;//前端跳转路径

    public String getPath() {
        return path;
    }

    private List<MenuBO> children = new ArrayList<MenuBO>();

    public List<MenuBO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuBO> children) {
        this.children = children;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

}
