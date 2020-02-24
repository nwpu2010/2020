package com.lee.sys.mapper;

import com.lee.common.base.mapper.BaseMapper;
import com.lee.sys.form.SysUsersForm;
import com.lee.sys.query.SysUsersQuery;
import com.lee.sys.vo.SysUsersVO;
import org.apache.ibatis.annotations.Param;

public interface SysUsersMapper extends BaseMapper<SysUsersForm, SysUsersVO> {

   int  updateByPrimaryKey(SysUsersForm sysUsersForm);

   SysUsersVO checkLogin(@Param("userName") String userName ,@Param("password") String password);

   SysUsersVO  selectUserByParam(SysUsersQuery sysUsersQuery);

}