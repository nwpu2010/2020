package com.lee.sys.mapper;

import com.lee.sys.form.SysRoleMenuRelForm;
import com.lee.common.base.mapper.BaseMapper;
import com.lee.sys.vo.SysRoleMenuRelVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuRelMapper extends BaseMapper<SysRoleMenuRelForm, SysRoleMenuRelVO> {
    /**
     *  根据角色删除 对应的权限
     * @param roleId
     * @return
     */
    int deleteByRole(@Param("roleId") Integer roleId);


    int insertRoleMenu(@Param("menuIds") List<Integer> menuIds, @Param("roleId") Integer roleId);

}