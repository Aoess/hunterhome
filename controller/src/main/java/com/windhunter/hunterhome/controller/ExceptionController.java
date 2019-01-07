package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @RequestMapping("/token/tokenError.do")
    public ResultBean permission() {
        ResultBean bean = new ResultBean(740,"TOKEN ERROR");
        return bean;
    }
}
