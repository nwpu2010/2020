package com.lee.controller;


import com.lee.domain.Bills;
import com.lee.service.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/bills")
@RestController
public class BillsController {

    @Autowired
    private BillsService billsServicel;

        @RequestMapping("/list.do")
        public Map<String,Object> getBillsList(@RequestParam Map<String,Object> param){
            Map<String, Object> result = billsServicel.selectBillsList(param);
            String msg ="success";
            String code = "200";
            result.put("msg",msg);
            result.put("code",code);
            return result ;
        }

        @RequestMapping("/add.do")
        public Map<String,Object> addBills(Bills bills){


            billsServicel.insert(bills);
            Map<String ,Object> map =  new HashMap<>();
            map.put("code","200");
            map.put("msg","success");
            return map;

        }
}
