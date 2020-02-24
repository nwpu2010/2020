package com.lee.bussi.controller;

import com.lee.bussi.form.BusCarsForm;
import com.lee.bussi.form.BusRentsForm;
import com.lee.bussi.query.BusCarsQuery;
import com.lee.bussi.query.BusRentsQuery;
import com.lee.bussi.service.IBusRentsService;
import com.lee.common.Result;
import com.lee.common.validator.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("bussi/rent")
public class BusRentsController {
    @Autowired
    private IBusRentsService busRentsService;

    @RequestMapping("list.do")
    @ResponseBody
    public  Object list(BusRentsQuery query){
        Result result = busRentsService.queryPage(query);
        return  result;
    }

    @RequestMapping("rent.do")
    @ResponseBody
    public Object rent(BusRentsForm form){
        //进行数据校验
        ValidatorUtil.setValidator(form);
        Result result = busRentsService.rent(form);
        return  result;
    }
}
