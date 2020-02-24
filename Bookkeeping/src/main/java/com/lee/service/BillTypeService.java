package com.lee.service;

import com.lee.domain.BillType;

import java.util.List;

public interface BillTypeService{


    int insert(BillType record);

    int insertSelective(BillType record);

    String selectByPrimaryKey(Integer id);

    List<BillType> selectBillTypeList();

}
