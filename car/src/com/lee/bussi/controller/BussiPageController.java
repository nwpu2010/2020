package com.lee.bussi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class BussiPageController {


    @RequestMapping("customer/list.do")
    public  String  customerList(){
        return  "bussi/customer/list.jsp";
    }

    @RequestMapping("car/list.do")
    public String carList(){
        return "bussi/car/list.jsp";
    }


    @RequestMapping("/rent/list.do")
    public String rentList(){
        return "bussi/rent/list.jsp";
    }

    @RequestMapping("/checks/list.do")
    public String checksList(){
        return "bussi/check/list.jsp";
    }

}
