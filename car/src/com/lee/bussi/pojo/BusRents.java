package com.lee.bussi.pojo;

public class BusRents {
    private Integer id;

    /**
    * 出租单号
    */
    private String rentNo;

    /**
    * 出租实际价格
    */
    private Integer price;

    /**
    * 开始时间
    */
    private String beginTime;

    /**
    * 还车时间
    */
    private String returnTime;

    /**
    * 出租状态	 1未归还 2已归还
    */
    private Integer flag;

    /**
    * 客户身份证
    */
    private String idCard;

    /**
    * 车牌号
    */
    private String carNum;

    /**
    * 当前登陆人ID
    */
    private Integer userId;

    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRentNo() {
        return rentNo;
    }

    public void setRentNo(String rentNo) {
        this.rentNo = rentNo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}