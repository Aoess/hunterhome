package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Autowired
    private UserService user1;

    @RequestMapping("/test")
    public @ResponseBody String test() {

        return user1.test();
    }
}
