package com.lee.bussi.mapper;

import com.lee.bussi.pojo.BusCars;
import com.lee.common.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BusCarsMapper extends BaseMapper {
    int insert(BusCars record);

    int insertSelective(BusCars record);

    /**
     * 根据ID 和 versoin 修改汽车状态
     * @param id   汽车ID
     * @param carFlag   汽车状态
     * @param version  版本号
     * @return
     */
    int updateFlag(@Param("id") Integer id, @Param("carFlag")  Integer carFlag, @Param("version") Integer version);
}