package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExceptionController {

    @RequestMapping("/token/tokenError.do")
    public ResultBean permission(HttpServletRequest request) {
        ResultBean bean = new ResultBean(740, (String) request.getAttribute("cause"));
        return bean;
    }
}
