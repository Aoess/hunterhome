package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.service.UserService;
import com.windhunter.hunterhome.utils.BodyReaderHttpServletRequestWrapper;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public ResultBean login(@RequestBody @Validated({User.phoneUpdate.class,User.passwordUpdate.class}) User user, HttpServletRequest request, HttpServletResponse response, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            try {
                JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
                bean = userService.login(user.getUser_phone(),user.getUser_pwd(), (String) parameterMap.get("autoLogin"),response);
            } catch (IOException e) {
                bean.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        log.info("<--" + bean.toString() + "-->");
        return bean;
    }

    @RequestMapping("/register.do")
    public ResultBean register(@RequestBody @Validated({User.phoneUpdate.class,User.passwordUpdate.class}) User user, BindingResult br, HttpServletRequest request) {
        ResultBean bean = new ResultBean(230,"Parameter error!!");
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        }else {
            try {
                JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
                bean = userService.register(user.getUser_phone(),user.getUser_pwd(), (String) parameterMap.get("code"),request);
            } catch (IOException e) {
                bean.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return bean;
    }

    @RequestMapping("/NormalUser/UserAdmin/supplementInformation.do")
    public ResultBean supplementInformation(@RequestBody @Validated({User.phoneUpdate.class,User.passwordUpdate.class})User user, BindingResult br, HttpServletRequest request) {
        user.setUser_id((String) request.getAttribute("user_id"));
        ResultBean bean = userService.supplementInformation(user);
        return bean;
    }

    @RequestMapping("/NormalUser/setNickname.do")
    public ResultBean setNickname(@RequestBody @Validated(User.nicknameUpdate.class)User user, BindingResult br, HttpServletRequest request) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setNickname((String) request.getAttribute("user_id"),user.getUser_nickname());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/setSex.do")
    public ResultBean setSex(@RequestBody @Validated(User.sexUpdate.class)User user, BindingResult br, HttpServletRequest request) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setSex((String) request.getAttribute("user_id"),user.getUser_sex());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/setPhoto.do")
    public ResultBean setPhoto(@RequestBody @Validated(User.photoUpdate.class)User user, BindingResult br, HttpServletRequest request) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setPhoto((String) request.getAttribute("user_id"),user.getUser_photo());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/setDepartment_id.do")
    public ResultBean setDepartment_id(@RequestBody @Validated(User.departmentUpdate.class)User user, BindingResult br, HttpServletRequest request) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setDepartment_id((String) request.getAttribute("user_id"),user.getDepartment_id());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/setClass_Grade.do")
    public ResultBean setClass_Grade(@RequestBody @Validated(User.classGradeUpdate.class)User user, BindingResult br, HttpServletRequest request) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = userService.setClass_Grade((String) request.getAttribute("user_id"),user.getClass_grade());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/UserAdmin/setPower.do")
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

    @RequestMapping("/NormalUser/setPwd.do")
    public ResultBean setPwd(@RequestBody @Validated(User.passwordUpdate.class)User user, BindingResult br, HttpServletRequest request) {
        ResultBean bean = new ResultBean(230,"Parameter error!!");
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        }else {
            try {
                JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
                bean = userService.setPwd((String) request.getAttribute("user_id"),(String) parameterMap.get("oldPwd"),user.getUser_pwd());
            } catch (IOException e) {
                bean.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return bean;
    }

    @RequestMapping("/NormalUser/setPhoneNumber.do")
    public ResultBean setPhoneNumber(@RequestBody @Validated(User.phoneUpdate.class)User user,HttpServletRequest request, BindingResult br) {
        ResultBean bean = new ResultBean(230,"Parameter error!!");
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        }else {
            try {
                JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
                bean = userService.setPhoneNumber((String) request.getAttribute("user_id"),user.getUser_phone(), (String) parameterMap.get("code"),request);
            } catch (IOException e) {
                bean.setMessage(e.getMessage());
                e.printStackTrace();
            }
        }
        return bean;
    }

    @RequestMapping("/NormalUser/UserAdmin/getUserById.do")
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

    @RequestMapping("/NormalUser/getUserByToken.do")
    public ResultBean getUserByToken(HttpServletRequest request) {
        ResultBean  bean = userService.getUserById((String) request.getAttribute("user_id"));
        return bean;
    }

    @RequestMapping("/NormalUser/UserAdmin/getUserByPhone.do")
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

    @RequestMapping("/NormalUser/UserAdmin/getUserCount.do")
    public ResultBean getUserCount() {
        return userService.getUserCount();
    }

    @RequestMapping("/NormalUser/logout.do")
    public  ResultBean logout(HttpServletRequest request, HttpServletResponse response) {
        ResultBean bean = userService.logout((String) request.getAttribute("user_id"),response);
        return bean;
    }
}
