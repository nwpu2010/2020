package com.lee.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date>{

    @Override
    public Date convert(String str){
        try{
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date= simpleDateFormat.parse(str);
            return date;
        }catch (Exception e) {
            new Throwable("Date转换异常");
            return null;
        }
    }

}
