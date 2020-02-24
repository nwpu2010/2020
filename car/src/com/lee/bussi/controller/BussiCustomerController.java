package com.lee.bussi.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.lee.bussi.form.BussiCustomerForm;
import com.lee.bussi.query.BussiCustomerQuery;
import com.lee.bussi.service.BussiCustomerService;
import com.lee.bussi.vo.BussiCustVO;
import com.lee.common.Result;
import com.lee.common.validator.ValidatorUtil;
import com.lee.sys.pojo.SysUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bussi/customer")
public class BussiCustomerController {

    @Autowired
    private BussiCustomerService  bussiCustomerService;

    /**
     * 筛选符合条件的数据，把它到处为 excel文件
     * @param query
     * @param resp
     */
    @RequestMapping("/export.do")
    public  void   exportCustList(BussiCustomerQuery query, HttpServletResponse resp) throws IOException {

        // 获取数据 格式为list
       List <BussiCustVO> list  =   bussiCustomerService.selectExportData(query);
        //通过工具类 创建 wrter ,默认为xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义列名
        writer.addHeaderAlias("idCard","身份证");
        writer.addHeaderAlias("sex","性别");
        //合并单元格后的标题栏
        Map<String, SysUsers> map = new HashMap<>();
        map.put("ddd",new SysUsers());
        writer.merge(3,map);//map的数据将以键值对的方式写在合并的单元格中
        //一次性写出内容
        writer.write(list,true);
//        resp.setContentType("application/vnd.ms-excel;charset=utf-8");
        /*设置为流文件，防止被浏览器直接打开，浏览器能开打能直接预览的文件*/
        resp.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        //文件名防止乱码，先编码
        String fileName = URLEncoder.encode("客户信息.xls","UTF-8");
        System.out.println(fileName);
        //设置响应头，告诉浏览器接收的数据类型
        resp.setHeader("Content-Disposition","attachment; filename=" + fileName);

        //将数据输入流中
        writer.flush(resp.getOutputStream());

    }

    /**
     * 获取客户列表
     * @param query
     * @return
     */
    @ResponseBody
    @RequestMapping("list.do")
    public Result queryPage(BussiCustomerQuery query){

        return bussiCustomerService.selectPage(query);

    }

    /**
     * 添加客户信息
     * @param form
     * @return
     */
    @RequestMapping("add.do")
    @ResponseBody
    public Result addCustomer(BussiCustomerForm form){

        ValidatorUtil.setValidator(form);
        Result rs =  bussiCustomerService.add(form);
        return  rs;

    }

    /**
     * 更新用户
     * @param form
     * @return
     */
    @ResponseBody
    @RequestMapping("update.do")
    public  Result  updateCustomer(BussiCustomerForm  form){

        ValidatorUtil.setValidator(form);
         bussiCustomerService.update(form);

         return new Result();
    }

}
