package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;
import com.windhunter.hunterhome.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @RequestMapping("/login.do")
    public @ResponseBody ResultBean login(@Validated(User.userlogin.class)User user, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            String errorMsg = null;
            FieldError userphoneError = br.getFieldError("user_phone");
            FieldError passwordError = br.getFieldError("user_pwd");
            if(userphoneError != null) {
                errorMsg = userphoneError.getDefaultMessage();
            }
            else if(passwordError != null) {
                errorMsg = passwordError.getDefaultMessage() ;
            }
            bean = new ResultBean(230,errorMsg);
        }else {
            //bean = userService.login(user.getUser_phone(), user.getUser_pwd());
        }
        return bean;
    }

    @RequestMapping("/test")
    public @ResponseBody String test() {
        return userService.test();
    }
}
