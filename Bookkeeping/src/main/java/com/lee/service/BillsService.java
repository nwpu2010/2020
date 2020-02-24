package com.lee.service;


import com.lee.domain.Bills;
import java.util.Map;

public interface BillsService{


    int insert(Bills record);

    int insertSelective(Bills record);

    Bills selectByPrimaryKey(Integer id);

    Map<String,Object>  selectBillsList(Map param);
}
