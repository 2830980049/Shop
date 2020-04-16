package com.edu.domain;

import java.util.List;

public class PageBean<T> {
    private Integer page; //当前页面
    private Integer allpage; //总页数
    private Integer limit; //每页显示记录数
    private Integer allnum; //总记录数
    private List<T> list;

    public PageBean() {
    }

    public PageBean(Integer page, Integer allpage, Integer limit, Integer allnum, List<T> list) {
        this.page = page;
        this.allpage = allpage;
        this.limit = limit;
        this.allnum = allnum;
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", allpage=" + allpage +
                ", limit=" + limit +
                ", allnum=" + allnum +
                ", list=" + list +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getAllpage() {
        return allpage;
    }

    public void setAllpage(Integer allpage) {
        this.allpage = allpage;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getAllnum() {
        return allnum;
    }

    public void setAllnum(Integer allnum) {
        this.allnum = allnum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
