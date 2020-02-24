package com.lee.sys.controller;


import com.lee.common.Result;
import com.lee.sys.form.SysMenuForm;
import com.lee.sys.query.SysMenuQuery;
import com.lee.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 根据登录的用户，将其菜单查出来返回给客户端
     * @param sysMenuQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("menu")
    public  Object  userMenu(SysMenuQuery sysMenuQuery){
        System.out.println("sssssss");

        return  sysMenuService.getMenu(sysMenuQuery);
    }

    @RequestMapping("menuList")
    @ResponseBody
    public  Result menuPage(SysMenuQuery sysMenuQuery){
        Result rs = sysMenuService.queryPage(sysMenuQuery);
        return rs;
    }

    @ResponseBody
    @RequestMapping("add")
    public Result addMenu(SysMenuForm form){
    //验证表单数据是符合条件
        sysMenuService.addMenu(form);

        return  new Result();

    }

}
