package com.lee.service;

import com.lee.domain.SysUser;
public interface SysUserService{


    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    SysUser selectUserByLoginName(String  loginName);

}
