package com.windhunter.hunterhome.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Page implements Serializable {

    private Integer current_page;//查询第几页
    private Integer pages_total;//页面的总数量
    private Object entity;//查询内容的实体类的父类
    private Integer page_number;//查询的条数

    public Page(){
    }

    public Page(Integer current_page, Integer pages_total, Object entity, Integer page_number) {
        this.current_page = current_page;
        this.pages_total = pages_total;
        this.entity = entity;
        this.page_number = page_number;
    }

    @Override
    public String toString() {
        return "Page{" +
                "current_page=" + current_page +
                ", pages_total=" + pages_total +
                ", entity=" + entity +
                ", page_number=" + page_number +
                '}';
    }

    public Integer getPage_number() {
        return page_number;
    }

    public void setPage_number(Integer page_number) {
        this.page_number = page_number;
    }

    public Object getEntity() {

        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public Integer getPages_total() {

        return pages_total;
    }

    public void setPages_total(Integer pages_total) {
        this.pages_total = pages_total;
    }

    public Integer getCurrent_page() {

        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }
}
