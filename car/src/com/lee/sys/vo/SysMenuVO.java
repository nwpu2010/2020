package com.lee.sys.vo;

import com.lee.sys.pojo.SysMenu;

import java.util.List;

public class SysMenuVO extends  SysMenu {


    /**
     *  用户权限复选框
     */
    private String checkArr = "0";


    /**
     *  checkArr  只是为了适应 dtree这个树形组件
     * @return
     */
    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }

}
