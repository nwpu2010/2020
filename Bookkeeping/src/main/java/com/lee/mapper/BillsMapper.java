package com.lee.mapper;

import com.lee.PO.BillsPO;
import com.lee.domain.Bills;

import java.util.List;
import java.util.Map;

public interface BillsMapper {
    int insert(Bills record);

    int insertSelective(Bills record);

    Bills selectByPrimaryKey(Integer id);

    List<BillsPO> selectBillsListAndName(Map param);

    List<BillsPO> selectBillsList(Map param);

}