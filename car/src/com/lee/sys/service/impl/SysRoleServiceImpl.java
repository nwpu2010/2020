package com.lee.sys.service.impl;

import com.lee.common.base.service.impl.BaseServiceImpl;
import com.lee.sys.form.SysRoleForm;
import com.lee.sys.mapper.SysMenuMapper;
import com.lee.sys.mapper.SysRoleMapper;
import com.lee.sys.mapper.SysRoleMenuRelMapper;
import com.lee.sys.mapper.SysUserRoleRelMapper;
import com.lee.sys.service.ISysRoleService;
import com.lee.common.Result;
import com.lee.common.base.service.impl.BaseServiceImpl;
import com.lee.sys.form.SysRoleForm;
import com.lee.sys.form.SysUserRoleRelForm;
import com.lee.sys.mapper.SysMenuMapper;
import com.lee.sys.mapper.SysRoleMapper;
import com.lee.sys.mapper.SysRoleMenuRelMapper;
import com.lee.sys.mapper.SysUserRoleRelMapper;
import com.lee.sys.query.SysMenuQuery;
import com.lee.sys.query.SysRoleMenuRelQuery;
import com.lee.sys.service.ISysRoleService;
import com.lee.sys.vo.SysMenuVO;
import com.lee.sys.vo.SysRoleMenuRelVO;
import com.lee.sys.vo.SysUserRoleRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleForm> implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuRelMapper sysRoleMenuRelMapper;
    @Autowired
    private SysUserRoleRelMapper sysUserRoleRelMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        super(sysRoleMapper);
    }

    @Override
    public Result queryRoleMenu(Integer id) {
        //获取所有的权限
        List<SysMenuVO> allMenu = sysMenuMapper.selectList(new SysMenuQuery());
        //根据角色ID 查询拥有的权限
        SysRoleMenuRelQuery  query = new SysRoleMenuRelQuery();
        query.setRoleId(id);
        List<SysRoleMenuRelVO> sysRoleMenuRelVOS = sysRoleMenuRelMapper.selectList(query);
        //根据拥有权限，将所有权限中，对应的权限checkArr的值设置为1 ,选中状态
        for (SysMenuVO menu:allMenu) {
            for (SysRoleMenuRelVO srm:sysRoleMenuRelVOS) {
                //获取所有权限中的权限ID
                Integer menuId = menu.getId();
                //获取拥有权限的权限ID
                Integer roleMenuId = srm.getMenuId();
                if (menuId.equals(roleMenuId)){
                    //将已拥有的权限设置为选中状态
                    menu.setCheckArr("1");
                }
            }
        }
        return new Result(allMenu);
    }

    @Override
    @Transactional
    public Result setRoleMenu(List<Integer> menuId, Integer roleId) {
        //清空当前角色的权限
        sysRoleMenuRelMapper.deleteByRole(roleId);
        //新增角色权限
        sysRoleMenuRelMapper.insertRoleMenu(menuId,roleId);
        return new Result();
    }

    @Override
    public Result queryUserRole(Integer userId) {
        SysUserRoleRelVO sysUserRoleRelVO = sysUserRoleRelMapper.selectByUser(userId);
        return new Result(sysUserRoleRelVO);
    }

    @Override
    @Transactional
    public Result setUserRole(SysUserRoleRelForm form) {
        //1. 首先删除用户当前角色
        sysUserRoleRelMapper.deleteByUser(form.getUserId());
        //2. 新增一个用户角色
        sysUserRoleRelMapper.insert(form);
        return new Result();
    }

}
