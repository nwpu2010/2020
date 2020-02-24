package com.lee.controller;

import com.lee.domain.BillType;
import com.lee.service.BillTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/billType")
public class BillTypeController {

        @Autowired
        private BillTypeService billTypeService;

        @RequestMapping("/list.do")
        public  String  getBillsName(Model model){

            List<BillType> billTypes = billTypeService.selectBillTypeList();
            model.addAttribute("typeList",billTypes);
            return "list";
        }

}
