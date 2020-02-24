package com.lee.common.base;

/**
 * 所有查询参数的基类
 */
public class Query {
    /*
    默认查询第一页
     */
    private  Integer page =1;
    /*
    默认每页10条数据
     */
    private Integer limit =10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
