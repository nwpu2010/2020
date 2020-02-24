package com.lee.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lee.domain.BillType;
import com.lee.mapper.BillTypeMapper;
import com.lee.service.BillTypeService;

import java.util.List;

@Service
public class BillTypeServiceImpl implements BillTypeService{

    @Resource
    private BillTypeMapper billTypeMapper;

    @Override
    public int insert(BillType record) {
        return billTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(BillType record) {
        return billTypeMapper.insertSelective(record);
    }

    @Override
    public String selectByPrimaryKey(Integer id) {
        return billTypeMapper.selectByPrimaryKey(id).getName();
    }

    @Override
    public List<BillType> selectBillTypeList() {
        return billTypeMapper.selectBillTypeList();
    }

}
