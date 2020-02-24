package com.lee.common.base;

import java.util.List;

public class PageInfo {
    /*
    页码
     */
    private Integer page;
    /**
     * 总条数
     */
    private long total;
    /*
    每页条数
     */
    private Integer limit;
    /*
    符合条件的数据
     */

    private List<Object> list;

    public PageInfo() {
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
