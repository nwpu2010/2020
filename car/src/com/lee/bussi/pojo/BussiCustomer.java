package com.lee.bussi.pojo;

public class BussiCustomer {
    /**
    * 客户id
    */
    private Integer id;

    /**
    * 客户身份证
    */
    private String idCard;

    /**
    * 客户姓名
    */
    private String custName;

    /**
    * 性别  1男 2 女
    */
    private Integer sex;

    /**
    * 客户住址
    */
    private String address;

    /**
    * 电话号码
    */
    private String phone;

    /**
    * 创建时间
    */
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}