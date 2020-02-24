package com.lee.bussi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lee.bussi.form.BussiCustomerForm;
import com.lee.bussi.query.BussiCustomerQuery;
import com.lee.bussi.vo.BussiCustVO;
import com.lee.common.Constant;
import com.lee.common.Result;
import com.lee.common.base.PageInfo;
import com.lee.common.exception.BussinessException;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lee.bussi.mapper.BussiCustomerMapper;
import com.lee.bussi.pojo.BussiCustomer;
import com.lee.bussi.service.BussiCustomerService;

import java.util.List;

@Service
public class BussiCustomerServiceImpl implements BussiCustomerService {

    @Resource
    private BussiCustomerMapper bussiCustomerMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bussiCustomerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BussiCustomer record) {
        return bussiCustomerMapper.insert(record);
    }

    @Override
    public int insertSelective(BussiCustomer record) {
        return bussiCustomerMapper.insertSelective(record);
    }

    @Override
    public BussiCustomer selectByPrimaryKey(Integer id) {
        return bussiCustomerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BussiCustomer record) {
        return bussiCustomerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int update(BussiCustomerForm form) {
        /**
         * 判断电话号码和 身份证是否已经存在
         */
        BussiCustomerQuery query = new BussiCustomerQuery();
        /*添加id*/
        query.setId(form.getId());
        /*查询手机号*/
        query.setPhone(form.getPhone());
        List<BussiCustVO> bussiCustVOS = bussiCustomerMapper.checkExist(query);
        if (bussiCustVOS != null && !bussiCustVOS.isEmpty()) {
            //已经存在手机号
            throw new BussinessException(Constant.PHONE_EXIST_ERROR, "电话号码已经存在");
        }
        /*查询身份证是否存在*/
        query.setPhone(null);
        query.setIdCard(form.getIdCard());
        bussiCustVOS = bussiCustomerMapper.checkExist(query);
        if (bussiCustVOS != null && !bussiCustVOS.isEmpty()) {
            //已经存在手机号
            throw new BussinessException(Constant.IDCARD_EXIST_ERROR, "身份证号码已经存在");
        }


        return  bussiCustomerMapper.update(form);
    }

 /*
 分页查询客户列表
  */
    @Override
    public Result selectPage(BussiCustomerQuery query) {
        Page<Object> data = PageHelper.startPage(query.getPage(), query.getLimit());
        bussiCustomerMapper.selectList(query);
        List<Object> list = data.getResult();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(data.getPageNum());
        pageInfo.setTotal(data.getTotal());
        pageInfo.setLimit(data.getPageSize());
        pageInfo.setList(list);

        return new Result(pageInfo);
    }

    @Override
    public Result add(BussiCustomerForm form) {

        // 验证 添加的客户  身份证号   和手机号是否存  是否已经存在
        BussiCustomerQuery query = new BussiCustomerQuery();
        /*查询手机号*/
        query.setPhone(form.getPhone());
        List<BussiCustVO> bussiCustVOS = bussiCustomerMapper.selectList(query);
        if (bussiCustVOS != null && !bussiCustVOS.isEmpty()) {
            //已经存在手机号
            throw new BussinessException(Constant.PHONE_EXIST_ERROR, "电话号码已经存在");
        }
        /*查询身份证是否存在*/
        query.setPhone(null);
        query.setIdCard(form.getIdCard());
        bussiCustVOS = bussiCustomerMapper.selectList(query);
        if (bussiCustVOS != null && !bussiCustVOS.isEmpty()) {
            //已经存在手机号
            throw new BussinessException(Constant.IDCARD_EXIST_ERROR, "身份证号码已经存在");
        }

        //没有错误，直接插入数据
        bussiCustomerMapper.insert(form);

        return new Result();
    }

    @Override
    public List<BussiCustVO> selectExportData(BussiCustomerQuery query) {
        return  bussiCustomerMapper.selectList(query);
    }



}
