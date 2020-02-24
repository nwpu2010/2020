package com.lee.controller;

import com.lee.common.ActiveUser;
import com.lee.domain.SysUser;
import com.lee.service.BillTypeService;
import com.lee.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {


    @RequestMapping("/login")
    public String login(SysUser user, HttpServletRequest req){

        UsernamePasswordToken token=new UsernamePasswordToken(user.getLoginname(),user.getPwd());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            req.getSession().setAttribute("loginName",activeUser.getSysUser());
            return "forward:/billType/list.do";
        }catch (AuthenticationException e){
            System.out.println("用户名或密码不正确");
            req.setAttribute("error","用户名或密码不正确");
            return "login";
        }


    }
}
