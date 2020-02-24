package com.lee.sys.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 表单数据验证,接收user表单数据
 */
public class SysUsersForm {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 登录名
     */
    @NotBlank(message = "用户登录名不能为空")
    @Length(max =15,min = 6,message ="用户登录名只能为6-15个字符")
    private String loginName;
    /**
     *密码
     */

    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 10,message="密码长度为6-10个字符")
    private String password;
    /**
     * 用户id
     */
    @Length(min = 18,max = 19,message = "身份证号格式错误 ")
    private String idCard;
    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    /**
     * 性别
     * 1 女  2 男
     */
    @Range(min = 1,max = 2,message = "请选择性别")
    private Integer sex;
    /**
     * 地址
     */
    @Length(max = 100,message = "地址长度超过100个字符")
    private String address;
    /**
     * 电话
     */
    @NotBlank(message = "电话号码不能为空")
    private String phone;
    /**
     * 头像
     */
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
