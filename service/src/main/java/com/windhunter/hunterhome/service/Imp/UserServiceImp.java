package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;
import com.windhunter.hunterhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    public ResultBean login(String user_phone, String user_pwd) {

        User user = userRepository.Login(user_phone, user_pwd);
        ResultBean resultBean = new ResultBean();
        int code;
        String message;

        if (null != user) {
            code = 666;
            message = "Success";
        } else {
            code = 555;
            message = "SORRY,YOUR USER_PHONE OR USER_PWD IS INCORRECT!!";
        }
        resultBean.setBean(user);
        resultBean.setCode(code);
        resultBean.setMessage(message);
        return resultBean;
    }

    public ResultBean register(String user_phone, String user_pwd, String code, HttpServletRequest request) {
        return null;
    }
}
