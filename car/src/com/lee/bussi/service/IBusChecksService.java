package com.lee.bussi.service;

import com.lee.common.Result;
import com.lee.bussi.form.BusChecksForm;
import com.lee.common.base.service.IBaseService;

public interface IBusChecksService extends IBaseService<BusChecksForm> {
    /**
     *  创建检查单  创建一个还车记录
     *
     *  1. 创建一个还车记录
     *      对比出租单状态
     *
     *
     *
     *  2. 修改对应的出租单号状态
     *  3. 修改车辆状态
     *
     *
     * @param form
     * @return
     */
    Result createCheckOrder(BusChecksForm form);
}
