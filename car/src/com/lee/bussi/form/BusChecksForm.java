package com.lee.bussi.form;

public class BusChecksForm {
    private Integer id;

    /**
     * 检查单号    后台使用时间生成
     */
    private String checkNo;

    /**
     * 检查时间
     */
    private String checkTime;

    /**
     * 描述
     */
    private String desc;

    /**
     * 存在问题
     */
    private String problem;

    /**
     * 出租金额
     */
    private Integer rentMoney;

    /**
     * 赔付金额
     */
    private Integer payMoney;

    /**
     * 总金额
     */
    private Integer totalMoney;

    /**
     * 操作员
     */
    private String userId;

    /**
     * 	出租单号
     */
    private String rentNo;

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

    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Integer getRentMoney() {
        return rentMoney;
    }

    public void setRentMoney(Integer rentMoney) {
        this.rentMoney = rentMoney;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRentNo() {
        return rentNo;
    }

    public void setRentNo(String rentNo) {
        this.rentNo = rentNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
