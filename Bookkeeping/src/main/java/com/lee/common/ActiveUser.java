package com.lee.common;

import com.lee.domain.SysUser;

import java.util.List;

/**
 * 用来封装用户、及其相关的权限和角色信息
 */
public class ActiveUser {
    /**
     * 角色
     */
    private List<String> roles;
    /*
    权限
     */
    private  List<String>  permissions;
    /*
    用户
     */
    private SysUser sysUser;

    public ActiveUser() {
    }

    public ActiveUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public ActiveUser(List<String> roles, List<String> permissions, SysUser sysUser) {
        this.roles = roles;
        this.permissions = permissions;
        this.sysUser = sysUser;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
