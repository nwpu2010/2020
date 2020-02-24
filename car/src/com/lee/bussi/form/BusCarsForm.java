package com.lee.bussi.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BusCarsForm  {


    /**
     * 主键
     */
    private Integer id;

    /**
     * 车牌号
     */
    @NotBlank( message = "车牌号不能为空")
    @Length(min = 7,max = 8,message = "车牌号最少7位 最多8位")
    private String carNum;

    /**
     * 车型
     */
    @NotBlank( message = "车型不能为空")
    private String type;

    /**
     * 颜色
     */
    @NotBlank( message = "颜色不能为空")
    private String color;

    /**
     * 购买价格
     */
    @NotNull(message = "汽车价格不能为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "汽车最少1元  最多99999999")
    private Integer price;

    /**
     * 出租价格
     */
    @NotNull(message = "汽车出租价格不能为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "汽车出租价格最少1元  最多99999999")
    private Integer rentPrice;

    /**
     * 押金
     */
    @NotNull(message = "汽车押金不能为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "汽车押金最少1元  最多99999999")
    private Integer deposit;

    /**
     * 是否租出
     */
    private Integer isRent;

    /**
     * 	  描述
     */
    private String desc;

    /**
     * 车辆图片
     */
    private String img;

    /**
     * 数据版本号  每次更新加1
     */
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getIsRent() {
        return isRent;
    }

    public void setIsRent(Integer isRent) {
        this.isRent = isRent;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
