package com.lee.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("main")
    public  String  main(){
        //跳转到登录成功的页面
        return  "main.jsp";
    }

    @RequestMapping("sys/menuList.do")
    public  String menuList(){
        return "sys/menu/dtree.jsp";
    }

    @RequestMapping("sys/userList")
    public  String userList(){

        return  "sys/user/list.jsp";
    }


    @RequestMapping("sys/roleList.do")
    public  String roleList(){
        return "sys/role/list.jsp";
    }


    @RequestMapping("sys/updateImg.do")
    public  String updateImg(){
        return "sys/user/updateImg.jsp";
    }

}
