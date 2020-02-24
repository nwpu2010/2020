package com.lee.sys.service;

import com.lee.common.Result;
import com.lee.sys.form.SysMenuForm;
import com.lee.sys.query.SysMenuQuery;

public interface SysMenuService {

    public Result getMenu(SysMenuQuery sysMenuQuery);



    Result queryPage(SysMenuQuery sysMenuQuery);

    void addMenu(SysMenuForm form);
}
