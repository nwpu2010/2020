package com.lee.sys.service;

import com.lee.common.Result;
import com.lee.common.base.Query;
import com.lee.sys.form.SysUsersForm;
import com.lee.sys.pojo.SysUsers;
import com.lee.sys.vo.SysUsersVO;

import java.util.List;

public interface SysUsersService {
    /**
     * 按id删除一个用户
     * @param id
     * @return
     */
    Result deleteByPrimaryKey(Integer id);

    /**
     * 添加一个用户
     * @param record
     * @return
     */
    Result insert(SysUsersForm record);

    Result insertSelective(SysUsersForm record);

    Result selectByPrimaryKey(Integer id);

    Result selectPage(Integer page,Integer limits,Query query);

    Result updateByPrimaryKey(SysUsersForm record);

     Result checkLogin(String loginName, String password,String checkCode);

    Result addUser(SysUsersForm form);

    Result resetPwd(SysUsersForm form);
}
