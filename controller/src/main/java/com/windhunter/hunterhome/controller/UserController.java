package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;
import com.windhunter.hunterhome.service.Imp.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @RequestMapping("/login.do")
    public ResultBean login(@RequestBody @Validated({User.phoneUpdate.class,User.passwordUpdate.class})User user, BindingResult br, HttpServletResponse response) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.login(user.getUser_phone(), user.getUser_pwd(), response);
        }
        log.info("<--" + bean.toString() + "-->");
        return bean;
    }

    @RequestMapping("/register.do")
    public ResultBean register(@RequestBody @Validated({User.phoneUpdate.class,User.passwordUpdate.class})User user, @RequestBody String code, HttpServletRequest request, BindingResult br) {
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
    @RequestMapping("/UserAdmin/supplementInformation.do")
    public ResultBean supplementInformation(@RequestBody @Validated({User.phoneUpdate.class,User.passwordUpdate.class})User user, BindingResult br) {

        ResultBean bean = userService.supplementInformation(user.getUser_id(),user.getUser_nickname(),user.getUser_sex(),user.getUser_photo(),user.getDepartment_id(),user.getClass_grade());
        return bean;
    }

    @RequestMapping("/UserAdmin/setNickname.do")
    public ResultBean setNickname(@RequestBody @Validated(User.nicknameUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setNickname(user.getUser_id(),user.getUser_nickname());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/setSex.do")
    public ResultBean setSex(@RequestBody @Validated(User.sexUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setSex(user.getUser_id(),user.getUser_sex());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/setPhoto.do")
    public ResultBean setPhoto(@RequestBody @Validated(User.photoUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPhoto(user.getUser_id(),user.getUser_photo());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/setDepartment_id.do")
    public ResultBean setDepartment_id(@RequestBody @Validated(User.departmentUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setDepartment_id(user.getUser_id(),user.getDepartment_id());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/user/setClass_Grade.do")
    public ResultBean setClass_Grade(@RequestBody @Validated(User.classGradeUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setClass_Grade(user.getUser_id(),user.getClass_grade());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/setPower.do")
    public ResultBean setPower(@RequestBody @Validated(User.powerUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPower(user.getUser_id(),user.getUser_power());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/setPwd.do")
    public ResultBean setPwd(@RequestBody @Validated(User.passwordUpdate.class)User user, @RequestBody String oldPwd, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPwd(user.getUser_id(),oldPwd,user.getUser_pwd());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/setPhoneNumber.do")
    public ResultBean setPhoneNumber(@RequestBody @Validated(User.phoneUpdate.class)User user, @RequestBody String code, HttpServletRequest request, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPhoneNumber(user.getUser_id(),user.getUser_phone(),code,request);
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/getUserById.do")
    public ResultBean getUserById(@RequestBody @Validated(User.getUsermessage.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.getUserById(user.getUser_id());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/getUserByPhone.do")
    public ResultBean getUserByPhone(@RequestBody @Validated(User.phoneUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.getUserByPhone(user.getUser_phone());
        }
        return bean;
    }

    @RequestMapping("/UserAdmin/getUserCount.do")
    public ResultBean getUserCount() {
        return userService.getUserCount();
    }

    @RequestMapping("/UserAdmin/logout.do")
    public  ResultBean logout(@RequestBody @Validated(User.phoneUpdate.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.logout(user.getUser_id());
        }
        return bean;
    }
}
