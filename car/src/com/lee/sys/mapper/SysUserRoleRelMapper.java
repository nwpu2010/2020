package com.lee.sys.mapper;

import com.lee.sys.form.SysUserRoleRelForm;
import com.lee.sys.vo.SysUserRoleRelVO;
import com.lee.common.base.mapper.BaseMapper;
import com.lee.sys.form.SysUserRoleRelForm;
import com.lee.sys.vo.SysUserRoleRelVO;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleRelMapper extends BaseMapper<SysUserRoleRelForm, SysUserRoleRelVO> {
    /**
     *  根据用户ID 删除其角色
     * @param userId
     * @return
     */
    int deleteByUser(@Param("userId") Integer userId);

    /**
     *  根据用户ID 新增角色
     * @param userId
     * @return
     */
    SysUserRoleRelVO selectByUser(@Param("userId") Integer userId);

}