package com.lee.bussi.service.impl;

import cn.hutool.core.date.DateUtil;
import com.lee.bussi.form.BusRentsForm;
import com.lee.bussi.mapper.BusCarsMapper;
import com.lee.bussi.mapper.BusRentsMapper;
import com.lee.bussi.mapper.BussiCustomerMapper;
import com.lee.bussi.query.BusCarsQuery;
import com.lee.bussi.query.BussiCustomerQuery;
import com.lee.bussi.service.IBusRentsService;
import com.lee.bussi.vo.BusCarsVO;
import com.lee.bussi.vo.BussiCustVO;
import com.lee.common.CodeMsg;
import com.lee.common.Constant;
import com.lee.common.Result;
import com.lee.common.base.service.impl.BaseServiceImpl;
import com.lee.common.exception.BussinessException;
import com.lee.common.util.WebUtil;
import com.lee.sys.vo.SysUsersVO;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BusRentsServiceImpl extends BaseServiceImpl<BusRentsForm> implements IBusRentsService {
    @Autowired
    private BusRentsMapper busRentsMapper;

    @Autowired
    private BussiCustomerMapper bussiCustomerMapper;
    @Autowired
    private BusCarsMapper busCarsMapper;

    public BusRentsServiceImpl(BusRentsMapper busRentsMapper) {
        super(busRentsMapper);
    }

    @Override
    @Transactional
    public Result rent(BusRentsForm form) {
        //业务数据校验
        //1. 校验客户信息是否存在  根据身份证号查询客户
        //获取身份证号
        String idCard = form.getIdCard();
        //根据身份证号查询客户信息 ：
        BussiCustomerQuery query = new BussiCustomerQuery();
        query.setIdCard(idCard);
        List<BussiCustVO> bussiCustomerVOS = bussiCustomerMapper.selectList(query);
        //如果根据身份证号  没有查询到客户信息
        if (bussiCustomerVOS == null || bussiCustomerVOS.isEmpty()){
            return new Result(CodeMsg.RENT_CUSTOMER_IDCARD_ERROR);
        }
        //2. 校验汽车状态 ：汽车是否属于未出租状态
        // 根据车牌号查询汽车信息
        BusCarsQuery busCarsQuery = new BusCarsQuery();
        busCarsQuery.setCarNum(form.getCarNum());
        List<BusCarsVO> busCarsVOS = busCarsMapper.selectList(busCarsQuery);
        if (busCarsVOS == null || busCarsVOS.isEmpty()){
            return new Result(CodeMsg.RENT_CAR_CARNUM_ERROR);
        }
        BusCarsVO busCarsVO = busCarsVOS.get(0);
        //校验汽车状态 是否出租
        if (busCarsVO.getIsRent().equals(Constant.CAR_RENT_ED)){
            return new Result(CodeMsg.RENT_CAR_RENTED_ERROR);
        }
        //3. 新增出租单
        form.setFlag(Constant.RENT_FLAG_NOT_RETURN);
        form.setCreateTime(DateUtil.format(new Date(),Constant.YYYY_MM_DD_HH_MM_SS));
        //获取当前用户ID
        form.setUserId(WebUtil.getCurrentUser(SysUsersVO.class).getId());
        //生成一个rent no
        String rentNo = "RENT"+DateUtil.format(new Date(),Constant.YYYYMMDDHHMMSSSSS);
        form.setRentNo(rentNo);
        busRentsMapper.insert(form);
        //4.修改汽车状态  改为已出租
        int m = busCarsMapper.updateFlag(busCarsVO.getId(), Constant.CAR_RENT_ED, busCarsVO.getVersion());
        //如果m为0  说明修改失败  只能是版本号不一致
        if (m == 0){
            //抛异常 数据回滚
            throw new BussinessException(CodeMsg.RENT_FAILD_ERROR.code,CodeMsg.RENT_FAILD_ERROR.msg);
        }
        return new Result();
    }
}
