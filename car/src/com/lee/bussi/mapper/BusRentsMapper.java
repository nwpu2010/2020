package com.lee.bussi.mapper;

import com.lee.bussi.form.BusRentsForm;
import com.lee.bussi.vo.BusRentsVO;
import com.lee.common.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BusRentsMapper extends BaseMapper<BusRentsForm, BusRentsVO> {
    /**
     * 根据ID 修改出租单状态
     * @param id
     * @param flag
     * @return
     */
    int updateFlag(@Param("id") Integer id, @Param("flag") Integer flag);

}