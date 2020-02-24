package com.lee.bussi.form;

import com.sun.javafx.image.IntPixelGetter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BussiCustomerForm {

    /**
     * 客户id
     */

    private Integer id;
    /**
     * 身份证
     */
    @Length(min = 18, max = 19, message = "身份证号格式错误 ")
    private String idCard;
    /**
     * 客户姓名
     */
    @NotBlank(message = "客户姓名不能为空")
    @Length(min = 5, max = 16, message = "姓名在5-16个字符之间")
    private String custName;

    /**
     * 性别
     */
    @NotNull(message = "客户性别不能为空")
    @Range(min = 1, max = 2, message = "性别不是男或者女")
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
