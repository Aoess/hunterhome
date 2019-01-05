package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;
import com.windhunter.hunterhome.service.Imp.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @RequestMapping("/login.do")
    public @ResponseBody ResultBean login(@Validated({User.phoneUpdate.class,User.passwordUpdate.class})User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.login(user.getUser_phone(), user.getUser_pwd());
        }
        log.info("<--" + bean.toString() + "-->");
        return bean;
    }

    @RequestMapping("/register.do")
    public @ResponseBody ResultBean register(@Validated({User.phoneUpdate.class,User.passwordUpdate.class})User user, String code, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.register(user.getUser_phone(),user.getUser_pwd(),code,request);
        }
        return bean;
    }

    //待补全
    @RequestMapping("/user/supplementInformation.do")
    public @ResponseBody ResultBean supplementInformation(@Validated({User.phoneUpdate.class,User.passwordUpdate.class})User user, BindingResult br) {

        ResultBean bean = userService.supplementInformation(user.getUser_id(),user.getUser_nickname(),user.getUser_sex(),user.getUser_photo(),user.getDepartment_id(),user.getClass_grade());
        return bean;
    }

    @RequestMapping("/user/setNickname.do")
    public @ResponseBody ResultBean setNickname(@Validated(User.nicknameUpdate.class)User user, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setNickname((String) request.getAttribute("user_id"),user.getUser_nickname());
        }
        return bean;
    }

    @RequestMapping("/user/setSex.do")
    public @ResponseBody ResultBean setSex(@Validated(User.sexUpdate.class)User user, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setSex((String) request.getAttribute("user_id"),user.getUser_sex());
        }
        return bean;
    }

    @RequestMapping("/user/setPhoto.do")
    public @ResponseBody ResultBean setPhoto(@Validated(User.photoUpdate.class)User user, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPhoto((String) request.getAttribute("user_id"),user.getUser_photo());
        }
        return bean;
    }

    @RequestMapping("/user/setDepartment_id.do")
    public @ResponseBody ResultBean setDepartment_id(@Validated(User.departmentUpdate.class)User user, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setDepartment_id((String) request.getAttribute("user_id"),user.getDepartment_id());
        }
        return bean;
    }

    @RequestMapping("/user/setClass_Grade.do")
    public @ResponseBody ResultBean setClass_Grade(@Validated(User.classGradeUpdate.class)User user, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setClass_Grade((String) request.getAttribute("user_id"),user.getClass_grade());
        }
        return bean;
    }

    @RequestMapping("/user/setPower.do")
    public @ResponseBody ResultBean setPower(@Validated(User.powerUpdate.class)User user, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPower((String) request.getAttribute("user_id"),user.getUser_power());
        }
        return bean;
    }

    @RequestMapping("/user/setPwd.do")
    public @ResponseBody ResultBean setPwd(@Validated(User.passwordUpdate.class)User user, String oldPwd, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPwd((String) request.getAttribute("user_id"),oldPwd,user.getUser_pwd());
        }
        return bean;
    }

    @RequestMapping("/user/setPhoneNumber.do")
    public @ResponseBody ResultBean setPhoneNumber(@Validated(User.phoneUpdate.class)User user, String code, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPhoneNumber((String) request.getAttribute("user_id"),user.getUser_phone(),code,request);
        }
        return bean;
    }

    @RequestMapping("/user/getUserById.do")
    public @ResponseBody ResultBean getUserById(@Validated(User.getUsermessage.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.getUserById(user.getUser_id());
        }
        return bean;
    }

    @RequestMapping("/user/getUserByPhone.do")
    public @ResponseBody ResultBean getUserByPhone(@Validated(User.phoneUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.getUserByPhone(user.getUser_phone());
        }
        return bean;
    }

    @RequestMapping("/user/getUserCount.do")
    public @ResponseBody ResultBean getUserCount() {
        return userService.getUserCount();
    }

    @RequestMapping("/user/logout.do")
    public @ResponseBody ResultBean logout(HttpServletRequest request, BindingResult br) {
        return userService.logout((String) request.getAttribute("us er_id"));
    }
}
