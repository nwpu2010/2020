package com.lee.sys.query;

import com.alibaba.fastjson.parser.deserializer.IntegerFieldDeserializer;
import com.lee.common.base.Query;

public class SysUsersQuery extends Query {

    /**
     * 用户姓名
     */
        private String realName;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 每页条数
     */
    private  Integer limit;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 登录名
     */
    private String loginName;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
