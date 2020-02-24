package com.lee.bussi.service;

import com.lee.bussi.form.BusRentsForm;
import com.lee.common.Result;
import com.lee.common.base.service.IBaseService;

public interface IBusRentsService extends IBaseService<BusRentsForm> {
    /**
     *  汽车出租  创建出租单记录
     * @param form
     * @return
     */
    Result rent(BusRentsForm form);
}
