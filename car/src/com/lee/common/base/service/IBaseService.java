package com.lee.common.base.service;


import com.lee.common.Result;
import com.lee.common.base.Query;
import org.apache.ibatis.annotations.Param;

public interface IBaseService<F> {
    /**
     * 新增数据
     * @param f
     * @return
     */
    Result add(F f);

    /**
     * 删除数据
     * @param id
     * @return
     */
    Result delete(Integer id);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */

    Result query(Integer id);

    /**
     * 根据参数查询 一页数据
     * @param query
     * @return
     */
    Result queryPage(Query query);

    /**
     * 更细信息
     * @param f
     * @return
     */
    Result update(F f);

    /**
     * 查询列表数据
     * @param query
     * @return
     */
    Result queryList(Query query);
}
