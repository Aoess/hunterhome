package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.ResultBean;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ResultBean login(String user_phone, String user_pwd);

    ResultBean register(String user_phone, String user_pwd, String code, HttpServletRequest request);

    ResultBean supplementInformation(String user_id, String user_nickname, String user_sex, String user_photo, int department_id, String class_grade);

    ResultBean setNickname(String user_id, String user_nickname);

    ResultBean setSex(String user_id, String user_sex);

    ResultBean setPhoto(String user_id, String user_photo);

    ResultBean setDepartment_id(String user_id, String department_id);

    ResultBean setClass_Grade(String user_id, String class_grade);

    ResultBean setPower(String user_id, int Power);

    ResultBean setPwd(String user_id, String oldPwd, String newPwd);

    ResultBean setPhoneNumber(String user_id, String user_phone, String code, HttpServletRequest request);

    ResultBean getUserById(String user_id);

    ResultBean getUserByPhone(String user_phone);

    ResultBean getUserCount();

    ResultBean getUserPhoto(String user_id);

    ResultBean getUserNickname(String user_id);

}
