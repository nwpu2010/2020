package com.lee.sys.service;

import com.lee.common.Result;
import com.lee.common.base.service.IBaseService;
import com.lee.sys.form.SysRoleForm;
import com.lee.sys.form.SysUserRoleRelForm;
import com.lee.common.Result;
import com.lee.common.base.service.IBaseService;
import com.lee.sys.form.SysRoleForm;
import com.lee.sys.form.SysUserRoleRelForm;

import java.util.List;

public interface ISysRoleService extends IBaseService<SysRoleForm> {
    /**
     *  获取用户所有权限， 获取所有权限，将用户权限设置为选中状态
     * @param id
     * @return
     */
    Result queryRoleMenu(Integer id);

    /**
     *  设置角色的权限
     * @param menuId   所有的权限ID
     * @param roleId    角色ID
     * @return
     */
    Result setRoleMenu(List<Integer> menuId, Integer roleId);

    /**
     *  查询用户角色
     * @param userId
     * @return
     */
    Result queryUserRole(Integer userId);

    /**
     *  设置用户角色
     * @param form
     * @return
     */
    Result setUserRole(SysUserRoleRelForm form);
}
