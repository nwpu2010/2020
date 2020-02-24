package com.lee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lee.PO.BillsPO;
import com.lee.domain.BillType;
import com.lee.service.BillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lee.mapper.BillsMapper;
import com.lee.domain.Bills;
import com.lee.service.BillsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillsServiceImpl implements BillsService {

    @Resource
    private BillsMapper billsMapper;
    /**
     * aop没办法拦截mapper的接口,所以不用，用service
     * private BillTypeMapper billTypeMapper
     */
    @Autowired
    private BillTypeService billTypeService;


    @Override
    public int insert(Bills record) {
        return billsMapper.insert(record);
    }

    @Override
    public int insertSelective(Bills record) {
        return billsMapper.insertSelective(record);
    }

    @Override
    public Bills selectByPrimaryKey(Integer id) {
        return billsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> selectBillsList(Map param) {
        Page<BillsPO> data = PageHelper.startPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("limit").toString()));
        billsMapper.selectBillsList(param);
        List<BillsPO> result = data.getResult();
        for (BillsPO bill : result) {
            Integer typeid = bill.getTypeid();
            bill.setName( billTypeService.selectByPrimaryKey(typeid));
        }

        long total = data.getTotal();
        Map<String, Object> map = new HashMap();
        map.put("data", result);
        map.put("count", total);
        return map;
    }


}
