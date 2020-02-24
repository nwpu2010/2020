package com.lee.common.base.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lee.common.Result;
import com.lee.common.base.PageInfo;
import com.lee.common.base.Query;
import com.lee.common.base.mapper.BaseMapper;
import com.lee.common.base.service.IBaseService;

public class BaseServiceImpl<F> implements IBaseService<F> {

    /*由于不知道具体是哪个mapper的实现类，所以能不能使用注解自动装配*/
    private BaseMapper baseMapper;

    public BaseServiceImpl(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public Result add(F f) {
        baseMapper.insert(f);

        return new Result();
    }

    @Override
    public Result delete(Integer id) {
        baseMapper.delete(id);

        return new Result();
    }

    @Override
    public Result query(Integer id) {
        Object o = baseMapper.selectOne(id);

        Result result = new Result(o);
        return result;
    }

    @Override
    public Result queryPage(Query query) {

        Page<Object> data = PageHelper.startPage(query.getPage(),query.getLimit());
        baseMapper.selectList(query);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(data.getPageNum());
        pageInfo.setLimit(data.getPageSize());
        pageInfo.setTotal(data.getTotal());
        pageInfo.setList(data.getResult());

        return new Result(pageInfo);
    }

    @Override
    public Result update(F f) {
        baseMapper.update(f);
        return new Result();
    }

    @Override
    public Result queryList(Query query) {
        return new Result(baseMapper.selectList(query)) ;
    }
}
