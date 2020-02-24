package com.lee.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lee.common.Constant;

import com.lee.common.Result;
import com.lee.common.base.PageInfo;
import com.lee.common.base.service.impl.BaseServiceImpl;
import com.lee.sys.form.SysMenuForm;
import com.lee.sys.mapper.SysMenuMapper;
import com.lee.sys.query.SysMenuQuery;
import com.lee.sys.service.SysMenuService;
import com.lee.sys.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuForm> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper) {
        super(sysMenuMapper);
    }

    @Override
    public Result getMenu(SysMenuQuery sysMenuQuery) {
        /**
         * 查询所有菜单及其子菜单
         */
        List<SysMenuVO> sysMenuVOs = sysMenuMapper.selectList(sysMenuQuery);

        List<SysMenuVO> parents = new ArrayList<>();

        for (SysMenuVO element : sysMenuVOs) {
            /**
             * 获取父菜单
             */
            if (element.getParentId().equals(Constant.SYSMENU_LEVEL_1_MENUID)) {
                parents.add(element);
            }
        }

        /**
         * 把子菜单装入对应的父菜单中
         */
        for (SysMenuVO  parent :parents){

                    for (SysMenuVO element : sysMenuVOs){

                    if ( element.getParentId().intValue() == parent.getId().intValue()){
                            //如果子菜单为空，则创建
                            if(null == parent.getChildren()){
                               parent.setChildren(new ArrayList<SysMenuVO>());
                            }
                                //把子菜单装入父菜单之中
                                parent.getChildren().add(element);

                    }

            }
        }

            return  new Result(parents);

    }

    @Override
    public Result queryPage(SysMenuQuery sysMenuQuery) {
        //开启分页
        Page<Object> data = PageHelper.startPage(sysMenuQuery.getPage(), sysMenuQuery.getLimit());
        sysMenuMapper.selectList(sysMenuQuery);
        //封装查询结果
        PageInfo pageInfo = new PageInfo();
        pageInfo.setLimit(data.getPageSize());
        pageInfo.setList(data.getResult());
        pageInfo.setPage(data.getPageNum());
        pageInfo.setTotal(data.getTotal());


        return new Result(pageInfo);
    }

    @Override
    public void addMenu(SysMenuForm form) {
        //不能插入null,设置为0
        if(form.getId() == null){
            form.setId(0);
        }
        sysMenuMapper.insert(form);
    }

}
