package com.lee.bussi.service;

import com.lee.bussi.form.BusCarsForm;
import com.lee.bussi.pojo.BusCars;
import com.lee.common.Result;
import com.lee.common.base.service.IBaseService;
import org.springframework.web.multipart.MultipartFile;

public interface BusCarsService extends IBaseService<BusCarsForm> {

    int insert(BusCars record);

    int insertSelective(BusCars record);

    Result addCar(MultipartFile img , BusCarsForm form);

}
