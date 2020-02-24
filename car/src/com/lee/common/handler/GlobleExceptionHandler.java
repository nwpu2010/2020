package com.lee.common.handler;

import com.lee.common.CodeMsg;
import com.lee.common.Result;
import com.lee.common.exception.BussinessException;
import org.springframework.scheduling.SchedulingAwareRunnable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler
    /*ResponseBody把结果转化为json数据*/
    @ResponseBody
    public Result globleExceptionHandler(Exception ex){

        System.out.println(ex.getMessage());
        if(ex instanceof BussinessException){

            Result rs= new Result();
            rs.setCode(((BussinessException) ex).getErrorCode());
            rs.setMsg(((BussinessException) ex).getErrorMsg());

            return rs;
        }

        Result result = new Result();
        result.setMsg(CodeMsg.FAILED.msg);
        result.setCode(CodeMsg.FAILED.code);

        return result;

    }
}
