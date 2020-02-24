package com.lee.bussi.query;

import com.lee.common.base.Query;

public class BusCarsQuery extends Query {
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 车型
     */
    private String type;
    /**
     * 颜色
     */
    private String color;
    /**
     * 最低出租价格
     */
    private Integer minRentPrice;
    /**
     * 最高出租价格
     */
    private Integer maxRentPrice;
    /**
     * 出租状态
     */
    private Integer isRent;


    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMinRentPrice() {
        return minRentPrice;
    }

    public void setMinRentPrice(Integer minRentPrice) {
        this.minRentPrice = minRentPrice;
    }

    public Integer getMaxRentPrice() {
        return maxRentPrice;
    }

    public void setMaxRentPrice(Integer maxRentPrice) {
        this.maxRentPrice = maxRentPrice;
    }

    public Integer getIsRent() {
        return isRent;
    }

    public void setIsRent(Integer isRent) {
        this.isRent = isRent;

    }

}
