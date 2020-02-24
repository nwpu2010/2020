package com.lee.bussi.controller;


import com.lee.bussi.form.BusCarsForm;
import com.lee.bussi.query.BusCarsQuery;
import com.lee.bussi.service.BusCarsService;
import com.lee.bussi.service.BussiCustomerService;
import com.lee.common.Result;
import com.lee.common.validator.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("bussi/car")
public class BusCarController {

    @Autowired
    private BusCarsService busCarsService;

    /**
     * 查询汽车列表
     * @param query
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public Result  queryPage(BusCarsQuery query){
        return busCarsService.queryPage((query));

    }
    @RequestMapping("add.do")
    @ResponseBody
    public Result  add(MultipartFile  carImg, BusCarsForm form){
       /*
       校验数据
        */
        ValidatorUtil.setValidator(form);
       Result rs = busCarsService.addCar( carImg,form);
       return  rs;
    }
}


