package com.lee.common.base.mapper;

import com.lee.common.base.Query;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表的基本操作 增删改查
 *
 * @param <F>  添加数据时的类型
 * @param <V>   查询数据时使用的类型
 */
public interface BaseMapper<F, V> {
    /**
     * 新增数据
     * @param f
     * @return
     */
    int insert(F f);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);

    /**
     * 根据id删除数据
     * @param id
     */
    V selectOne(Integer id);

    /**
     * 查询列表数据
     * @param query
     * @return
     */
    List<V> selectList(Query query);

    /**
     * 更新信息
     * @param f
     * @return
     */
    int update(F f);


}
