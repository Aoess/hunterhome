package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    ResultBean login(String user_phone, String user_pwd, String autoLogin, HttpServletResponse response);

    ResultBean register(String user_phone, String user_pwd, String code, HttpServletRequest request);

    ResultBean supplementInformation(User user);

    ResultBean setNickname(String user_id, String user_nickname);

    ResultBean setSex(String user_id, String user_sex);

    ResultBean setPhoto(String user_id, String user_photo);

    ResultBean setDepartment_id(String user_id, int department_id);

    ResultBean setClass_Grade(String user_id, String class_grade);

    ResultBean setPower(String user_id, int Power);

    ResultBean setPwd(String user_id, String oldPwd, String newPwd);

    ResultBean setPhoneNumber(String user_id, String user_phone, String code, HttpServletRequest request);

    ResultBean getUserById(String user_id);

    ResultBean getUserByPhone(String user_phone);

    ResultBean getUserCount();

    ResultBean logout(HttpServletRequest request);

    ResultBean getUserPhoto(String user_id);

    ResultBean getUserNickname(String user_id);

}
