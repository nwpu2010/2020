package com.lee.bussi.mapper;

import com.lee.bussi.form.BussiCustomerForm;
import com.lee.bussi.pojo.BussiCustomer;
import com.lee.bussi.query.BussiCustomerQuery;
import com.lee.bussi.vo.BussiCustVO;
import com.lee.common.base.mapper.BaseMapper;

import java.util.List;

public interface BussiCustomerMapper extends BaseMapper<BussiCustomerForm, BussiCustVO> {
    int deleteByPrimaryKey(Integer id);

    int insert(BussiCustomer record);

    int insertSelective(BussiCustomer record);

    BussiCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussiCustomer record);

    List<BussiCustVO> checkExist(BussiCustomerQuery query);

}