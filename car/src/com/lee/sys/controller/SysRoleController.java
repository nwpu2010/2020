package com.lee.sys.controller;

import com.lee.common.Result;
import com.lee.common.validator.ValidatorUtil;
import com.lee.sys.form.SysRoleForm;
import com.lee.sys.form.SysUserRoleRelForm;
import com.lee.sys.query.SysRoleQuery;
import com.lee.sys.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sys/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 分页获取角色信息列表
     * @param query
     * @return
     */
    @RequestMapping(value = "list.do")
    @ResponseBody
    public Result queryPage(SysRoleQuery query){
        return  sysRoleService.queryPage(query);
    }

    @RequestMapping(value = "add.do")
    @ResponseBody
    public Result add(SysRoleForm form){
        ValidatorUtil.setValidator(form);
        return  sysRoleService.add(form);
    }

    @RequestMapping(value = "update.do")
    @ResponseBody
    public Result update(SysRoleForm form){
        ValidatorUtil.setValidator(form);
        return  sysRoleService.update(form);
    }

    /**
     *  获取角色菜单
     *      实现方案：
     *          1. 获取所有权限,根据角色获取对应的权限ID,使用js 将相应的权限设置为选中状态
     *          2. 获取所有权限,根据角色获取对应的权限ID,在后台,设置所有权限中,拥有的权限的checkArr 字段的值为  1
     * @param id
     * @return
     */
    @RequestMapping(value = "getRoleMenu.do")
    @ResponseBody
    public Result getRoleMenu(Integer id){
        //获取所有的权限
        Result rs = sysRoleService.queryRoleMenu(id);
        return rs;
    }
    @RequestMapping(value = "setRoleMenu.do")
    @ResponseBody
    public Result setRoleMenu(@RequestParam("menuId") List<Integer> menuId, @RequestParam("roleId")  Integer roleId){
        //获取所有的权限
        Result rs = sysRoleService.setRoleMenu(menuId,roleId);
        return rs;
    }


    /**
     *  获取所有角色
     * @param query
     * @return
     */
    @RequestMapping(value = "allRole.do")
    @ResponseBody
    public  Result allRole(SysRoleQuery query){
        return  sysRoleService.queryList(query);
    }

    /**
     * 根据用户ID 获取角色
     * @param userId
     * @return
     */
    @RequestMapping(value = "getUserRole.do")
    @ResponseBody
    public Result getUserRole(Integer userId){
        return  sysRoleService.queryUserRole(userId);
    }

    @RequestMapping(value = "setUserRole.do")
    @ResponseBody
    public Result setUserRole(SysUserRoleRelForm form){
        return  sysRoleService.setUserRole(form);

    }


}
