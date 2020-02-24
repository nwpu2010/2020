package com.lee.bussi.service.impl;

import cn.hutool.core.date.DateUtil;
import com.lee.bussi.form.BusChecksForm;
import com.lee.bussi.mapper.BusCarsMapper;
import com.lee.bussi.mapper.BusChecksMapper;
import com.lee.bussi.mapper.BusRentsMapper;
import com.lee.bussi.query.BusCarsQuery;
import com.lee.bussi.query.BusRentsQuery;
import com.lee.bussi.service.IBusChecksService;
import com.lee.bussi.vo.BusCarsVO;
import com.lee.bussi.vo.BusRentsVO;
import com.lee.common.CodeMsg;
import com.lee.common.Constant;
import com.lee.common.Result;
import com.lee.common.base.service.impl.BaseServiceImpl;
import com.lee.common.exception.BussinessException;
import com.lee.common.util.WebUtil;
import com.lee.sys.vo.SysUsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BusChecksServiceImpl extends BaseServiceImpl<BusChecksForm> implements IBusChecksService {

    @Autowired
    private BusChecksMapper busChecksMapper;

    @Autowired
    private BusRentsMapper busRentsMapper;

    @Autowired
    private BusCarsMapper busCarsMapper;


    public BusChecksServiceImpl(BusChecksMapper busChecksMapper) {
        super(busChecksMapper);
    }

    @Override
    @Transactional
    public Result createCheckOrder(BusChecksForm form) {
        //进行业务数据校验
        //1. 校验出租单是否有效
        //  出租单是否存在  且 状态是未还车
        BusRentsQuery query = new BusRentsQuery();
        query.setRentNo(form.getRentNo());
        List<BusRentsVO> busRentsVOS = busRentsMapper.selectList(query);
        if(busRentsVOS == null || busRentsVOS.isEmpty() ||busRentsVOS.get(0).getFlag() == Constant.CAR_RENT_ED){
            return new Result(CodeMsg.CEHCK_RENT_NO_ERROR);
        }
        BusRentsVO rentsVO = busRentsVOS.get(0);
        //生成一个检查单  时间戳 + 唯一标识码
        String checkNo = "CHECK" + DateUtil.format(new Date(),Constant.YYYYMMDDHHMMSSSSS);
        form.setCheckNo(checkNo);
        String checkTime = DateUtil.format(new Date(),Constant.YYYY_MM_DD_HH_MM_SS);
        form.setCheckTime(checkTime);
        form.setCreateTime(DateUtil.format(new Date(),Constant.YYYY_MM_DD_HH_MM_SS));
        String beginTime = rentsVO.getBeginTime();
        //相差的天数
        long days = DateUtil.betweenDay(DateUtil.parse(beginTime, "yyyy-MM-dd"), new Date(), true);
        //计算钱
        //出租金额
        long rentMoney = rentsVO.getPrice() * days;
        form.setRentMoney((int)rentMoney);
        //总金额
        long totalMoney = rentMoney + form.getPayMoney();
        form.setTotalMoney((int)totalMoney);
        //设置用户ID
        form.setUserId(WebUtil.getCurrentUser(SysUsersVO.class).getId().toString());
        //添加检查车辆记录
        busChecksMapper.insert(form);
        //修改租车记录状态
        busRentsMapper.updateFlag(rentsVO.getId(),Constant.RENT_FLAG_RETURNED);
        //根据车牌号查询车辆信息
        BusCarsQuery busCarQuery = new BusCarsQuery();
        busCarQuery.setCarNum(rentsVO.getCarNum());
        List<BusCarsVO> busCarsVOS = busCarsMapper.selectList(busCarQuery);
        BusCarsVO busCarsVO = busCarsVOS.get(0);
        //修改车辆状态
        int m = busCarsMapper.updateFlag(busCarsVO.getId(),Constant.CAR_NOT_RENT,busCarsVO.getVersion());
        if(m == 0){
            throw  new BussinessException(CodeMsg.CEHCK_CREATE_ERROR.code,CodeMsg.CEHCK_CREATE_ERROR.msg);
        }
        return new Result();
    }


}
