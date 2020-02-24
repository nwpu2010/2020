package com.lee.mapper;

import com.lee.domain.BillType;

import java.util.List;

public interface BillTypeMapper {
    int insert(BillType record);

    int insertSelective(BillType record);

    BillType selectByPrimaryKey(Integer id);

    List<BillType> selectBillTypeList();
}