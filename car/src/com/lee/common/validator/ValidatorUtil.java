package com.lee.common.validator;

import com.lee.common.Constant;
import com.lee.common.exception.BussinessException;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 数据校验请求的数据
 *
 * 请求的参数，有各种类型，各种类别，但是一样存在公共点，
 * 请求的参数不能为空
 * 请求参数最大最小值
 * 请求的数据字符串长度
 * 用户名 不能为空 长度  if username =!null && !="" username.length<15
 * 密码  if  username != null && !="" username.length<15
 */
public class ValidatorUtil {


    private static final Validator  validator;

    static {
        /*获取一个数据校验器*/
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public  static  void setValidator(Object form){

        Set<ConstraintViolation<Object>> validate = validator.validate(form);
        for (ConstraintViolation<Object> constraintViolation : validate){
            //校验不通过的信息
            String message = constraintViolation.getMessage();
            //校验不通过 抛出异常
            throw  new BussinessException(Constant.FORM_CHECK_ERROR_CODE,message);
        }
    }

}
