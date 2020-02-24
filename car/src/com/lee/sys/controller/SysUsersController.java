package com.lee.sys.controller;


import cn.hutool.crypto.SecureUtil;

import com.lee.common.Result;
import com.lee.common.exception.BussinessException;
import com.lee.common.validator.ValidatorUtil;
import com.lee.sys.form.SysUsersForm;
import com.lee.sys.query.SysUsersQuery;
import com.lee.sys.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("sys/user")
public class SysUsersController {

    @Autowired
    private SysUsersService sysUsersService;


    @RequestMapping("list")
    @ResponseBody
    public Result queryPage(SysUsersQuery query) {

        Result rs = sysUsersService.selectPage(query.getPage(), query.getLimit(), query);

        return rs;
    }

    @RequestMapping(value = "add.do")
    @ResponseBody
    public Result add(SysUsersForm form) {
        ValidatorUtil.setValidator(form);
        Result rs = sysUsersService.addUser(form);

        return rs;
    }

    @RequestMapping(value = "resetPwd.do")
    @ResponseBody
    public Result resetPwd(SysUsersForm form) {

        Result rs = sysUsersService.resetPwd(form);

        return rs;
    }

    @RequestMapping("login")
    @ResponseBody
    public Result login(String loginName, String password, String checkCode) {

        //给密码加密
        String md5password = SecureUtil.md5(password);
        System.out.println(md5password);



        return sysUsersService.checkLogin(loginName, md5password, checkCode);

    }
}
