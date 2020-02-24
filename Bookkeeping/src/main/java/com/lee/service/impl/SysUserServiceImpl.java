package com.lee.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lee.mapper.SysUserMapper;
import com.lee.domain.SysUser;
import com.lee.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService{

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUser record) {
        return sysUserMapper.insertSelective(record);
    }

    @Override
    public SysUser selectByPrimaryKey(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUser selectUserByLoginName(String loginName) {

        return sysUserMapper.selectUserByLoginName(loginName);
    }

}
