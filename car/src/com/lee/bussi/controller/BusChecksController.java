package com.lee.bussi.controller;

import com.lee.bussi.form.BusCarsForm;
import com.lee.bussi.form.BusChecksForm;
import com.lee.bussi.query.BusChecksQuery;
import com.lee.bussi.service.IBusChecksService;
import com.lee.common.Result;
import com.lee.common.validator.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("bussi/checks")
public class BusChecksController {

    @Autowired
    private IBusChecksService busChecksService;

    @RequestMapping("list.do")
    @ResponseBody
    public Object list(BusChecksQuery query){
        return busChecksService.queryPage(query);
    }
    @RequestMapping("add.do")
    @ResponseBody
    public Object add(BusChecksForm form){
        ValidatorUtil.setValidator(form);
        Result rs = busChecksService.createCheckOrder(form);
        return rs;
    }

}
