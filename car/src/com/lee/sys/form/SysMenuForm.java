package com.lee.sys.form;

import com.lee.sys.pojo.SysMenu;

import java.util.List;

public class SysMenuForm {

    /**
     * 菜单id
     */
    private Integer id;
    /**
     * 父菜单的id
     */
    private Integer parentId;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单的跳转地址
     */
    private String href;

    /**
     * 菜单的打开方式  true 展开 false 不展开

     */
    private Integer spread;
    /**
     * 跳转的地址
     */
    private String target;

    /**
     * 菜单的排序
     */
    private Integer sort;

    private List<SysMenu> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getSpread() {
        return spread;
    }

    public void setSpread(Integer spread) {
        this.spread = spread;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
}

