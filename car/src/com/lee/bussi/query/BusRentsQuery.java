package com.lee.bussi.query;

import com.lee.common.base.Query;

public class BusRentsQuery extends Query {
    /**
     * 车牌号
     */
    private String carNum;
    /**
     *  身份证
     */
    private String idCard;
    /**
     *  出租单
     */
    private String rentNo;
    /**
     *  起租时间
     */
    private String minRentTime;
    /**
     *  还车时间
     */
    private String maxRentTime;
    /**
     *  还车状态
     */
    private Integer flag;


    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRentNo() {
        return rentNo;
    }

    public void setRentNo(String rentNo) {
        this.rentNo = rentNo;
    }

    public String getMinRentTime() {
        return minRentTime;
    }

    public void setMinRentTime(String minRentTime) {
        this.minRentTime = minRentTime;
    }

    public String getMaxRentTime() {
        return maxRentTime;
    }

    public void setMaxRentTime(String maxRentTime) {
        this.maxRentTime = maxRentTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
