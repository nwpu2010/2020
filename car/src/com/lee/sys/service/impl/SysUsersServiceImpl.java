package com.lee.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lee.common.Constant;
import com.lee.common.Result;
import com.lee.common.base.PageInfo;
import com.lee.common.base.Query;
import com.lee.common.exception.BussinessException;
import com.lee.common.util.WebUtil;
import com.lee.sys.form.SysUsersForm;
import com.lee.sys.query.SysUsersQuery;
import com.lee.sys.service.SysUsersService;
import com.lee.sys.vo.SysUsersVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.lee.sys.mapper.SysUsersMapper;
import com.lee.sys.pojo.SysUsers;

@Service
public class SysUsersServiceImpl implements SysUsersService {

    @Resource
    private SysUsersMapper sysUsersMapper;


    @Override
    public Result deleteByPrimaryKey(Integer id) {
        sysUsersMapper.delete(id);
        return new Result();
    }

    @Override
    public Result insert(SysUsersForm record) {
        sysUsersMapper.insert(record);
        return new Result();
    }

    @Override
    public Result insertSelective(SysUsersForm record) {

        sysUsersMapper.insert(record);
        return new Result();
    }

    @Override
    public Result selectByPrimaryKey(Integer id) {
        SysUsersVO sysUsersVO = sysUsersMapper.selectOne(id);

        return new Result(sysUsersVO);
    }

    @Override
    public Result selectPage(Integer page, Integer limit, Query query) {

        Page<Object> sysUsersVOs = PageHelper.startPage(page, limit);
        sysUsersMapper.selectList(query);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setLimit(limit);
        pageInfo.setTotal(sysUsersVOs.getTotal());
        pageInfo.setList(sysUsersVOs.getResult());
        pageInfo.setPage(page);
        return new Result(pageInfo);
    }

    @Override
    public Result updateByPrimaryKey(SysUsersForm record) {

        sysUsersMapper.updateByPrimaryKey(record);
        return new Result();
    }

    @Override
    public Result checkLogin(String loginName, String password, String checkCode) {

        HttpSession session = WebUtil.getSession();
        Object code = session.getAttribute(Constant.CHECK_CODE);
        if (!code.equals(checkCode)) {
            throw new BussinessException(Constant.CHECK_CODE_ERROR, "验证码错误");
        }
        //判断用户名和密码的有效xing
        if (StrUtil.isBlank(loginName)) {
            return new Result(Constant.LOGINNAME_ISBLANK_ERROR, "用户名不能为空");
        }
        if (StrUtil.isBlank(password)) {
            return new Result(Constant.PASSWORD_ISBLANK_ERROR, "密码不能为空");
        }
        SysUsers sysUsers = sysUsersMapper.checkLogin(loginName, password);
        if (sysUsers == null)
            return new Result(Constant.LOGIN_FAILED_ERROR, "用户名或者密码错误");

        //把sysUsers放入session
        WebUtil.getSession().setAttribute(Constant.CURRENT_USER, sysUsers);
        return new Result();
    }

    @Override
    public Result addUser(SysUsersForm form) {
        //检查  loginName  ,idCard，phone是否存在
        SysUsersQuery sysUsersQuery = new SysUsersQuery();
        SysUsersVO sysUsersVO = null;

        //已经存在用户的情况,已经存在身份证号
        sysUsersQuery.setIdCard(form.getIdCard());
        sysUsersVO = sysUsersMapper.selectUserByParam(sysUsersQuery);
        if (null != sysUsersVO) {
            throw new BussinessException(Constant.ADD_USER_ERROR,"身份证号已经存在");
        }

        //已经存在电话号码
        sysUsersQuery.setPhone(form.getPhone());
        sysUsersVO = sysUsersMapper.selectUserByParam(sysUsersQuery);
        if (null != sysUsersVO) {
            throw new BussinessException(Constant.ADD_USER_ERROR,"电话号码已经存在");
        }

        //已经存在用户名
        sysUsersQuery.setLoginName(form.getLoginName());
        sysUsersVO = sysUsersMapper.selectUserByParam(sysUsersQuery);
        if (null != sysUsersVO) {
            throw new BussinessException(Constant.ADD_USER_ERROR,"用户名已经存在");
        }

        //把密码加密
        form.setPassword(SecureUtil.md5(form.getPassword()));
        int insert = sysUsersMapper.insert(form);
        return new Result(insert);
    }

    @Override
    public Result resetPwd(SysUsersForm form) {
        form.setPassword(SecureUtil.md5(Constant.SYSUSER_DEFAULT_PWD));
         sysUsersMapper.updateByPrimaryKey(form);
        return new Result();
    }
}
