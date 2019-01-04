package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.ResultBean;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    public ResultBean login(String user_phone, String user_pwd);

    public ResultBean register(String user_phone, String user_pwd, String code, HttpServletRequest request);

    public ResultBean supplement();

}
