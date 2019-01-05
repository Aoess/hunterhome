package com.windhunter.hunterhome.entity;

import java.io.Serializable;

public class ResultBean implements Serializable {

    private int code;
    private String message;
    private Object bean;

    public ResultBean(int code, String message, Object bean) {
        this.code = code;
        this.message = message;
        this.bean = bean;
    }

    public ResultBean(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResultBean() { }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", bean=" + bean +
                '}';
    }
}
