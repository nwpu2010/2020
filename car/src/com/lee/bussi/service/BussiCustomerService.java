package com.lee.bussi.service;

import com.lee.bussi.form.BussiCustomerForm;
import com.lee.bussi.pojo.BussiCustomer;
import com.lee.bussi.query.BussiCustomerQuery;
import com.lee.bussi.vo.BussiCustVO;
import com.lee.common.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BussiCustomerService {


    int deleteByPrimaryKey(Integer id);

    int insert(BussiCustomer record);

    int insertSelective(BussiCustomer record);

    BussiCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussiCustomer record);

    int update(BussiCustomerForm form);

    /**
     * 查询客户列表
     *
     * @param query
     * @return
     */
    Result selectPage(BussiCustomerQuery query);

    /**
     * 添加客户信息
     * @param form
     * @return
     */
    Result add(BussiCustomerForm form);

    List<BussiCustVO> selectExportData(BussiCustomerQuery query);
}
