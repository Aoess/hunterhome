package com.windhunter.hunterhome.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;
import com.windhunter.hunterhome.service.Imp.ResourceServiceImp;
import com.windhunter.hunterhome.service.ResourceService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class ResouceController {

    @Autowired
    private ResourceService resouceService;

    @RequestMapping("/getPhoneCode.do")
    public ResultBean getPhoneCode(@RequestBody @Validated(User.phoneUpdate.class) User u, BindingResult br, HttpServletRequest request) {
        ResultBean bean = null;
        System.out.println(u.getUser_phone());
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = resouceService.getPhoneCode(u.getUser_phone(),request);
        }
        return bean;

    }


}
