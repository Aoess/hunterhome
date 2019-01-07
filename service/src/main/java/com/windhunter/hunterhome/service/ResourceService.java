package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.ResultBean;

import javax.servlet.http.HttpServletRequest;

public interface ResourceService {

    ResultBean getPhoneCode(String user_phone, HttpServletRequest request);
}
