package com.lee.mapper;

import com.lee.domain.SysUser;

public interface SysUserMapper {
    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    SysUser  selectUserByLoginName(String loginName);
}