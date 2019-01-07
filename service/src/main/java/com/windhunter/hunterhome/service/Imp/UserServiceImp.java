package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;
import com.windhunter.hunterhome.repository.UserRepository;
import com.windhunter.hunterhome.service.UserService;
import com.windhunter.hunterhome.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    public ResultBean login(String user_phone, String user_pwd, HttpServletResponse response) {
        User user = userRepository.login(user_phone, user_pwd);
        ResultBean resultBean = new ResultBean();
        int code;
        String message;
        if (null != user) {
            code = 666;
            message = "SUCCESS";
            String token = TokenUtils.getToken(user.getUser_id(),user.getUser_power());
            response.setHeader("token",token);
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
        User user = null;
        ResultBean resultBean = new ResultBean();
        String message = "SUCCESS";
        int codeitem = 666;;

        //查询用户是否存在
        if(null==userRepository.getUserByPhone(user_phone)) {
            //验证码是否正确
            if(code.equals(request.getSession().getAttribute("code"))){
                user = new User();
                user.setUser_pwd(user_pwd);
                user.setUser_power(0);
                user.setUser_photo("default.jpg");
                user.setUser_phone(user_phone);
                user.setUser_nickname("Waitting Setting");
                user.setDepartment_id(1);
                user.setClass_grade("Waitting Setting");
                user.setUser_sex("1");
                user.setUser_id(UUID.randomUUID().toString().replace("-",""));
            }else {//验证码错误
                codeitem = 555;
                message = "VERIFICATION CODE ERROR!!";
            }
        }else{//用户已存在
            codeitem = 555;
            message = "THE USER ALREADY EXISTS!!";
        }
        //保存到数据库
        userRepository.register(user);
        resultBean.setBean(user);
        resultBean.setMessage(message);
        resultBean.setCode(codeitem);

        return resultBean;
    }

    @Override
    public ResultBean supplementInformation(String user_id, String user_nickname, String user_sex, String user_photo, int department_id, String class_grade) {
        return  null;
    }

    @Override
    public ResultBean setNickname(String user_id, String user_nickname) {
        ResultBean resultBean = new ResultBean();
        int code = 666;
        String message = "SUCCESS";
        userRepository.setNickname(user_id,user_nickname);

        resultBean.setMessage(message);
        resultBean.setCode(code);

        return resultBean;
    }

    @Override
    public ResultBean setSex(String user_id, String user_sex) {
        ResultBean resultBean = new ResultBean();
        int code = 666;
        String message = "SUCCESS";
        userRepository.setSex(user_id,user_sex);

        resultBean.setMessage(message);
        resultBean.setCode(code);

        return resultBean;
    }

    @Override
    public ResultBean setPhoto(String user_id, String user_photo) {
        ResultBean resultBean = new ResultBean();
        int code = 666;
        String message = "SUCCESS";
        userRepository.setPhoto(user_id,user_photo);

        resultBean.setMessage(message);
        resultBean.setCode(code);

        return resultBean;
    }

    @Override
    public ResultBean setDepartment_id(String user_id, int department_id) {
        ResultBean resultBean = new ResultBean();
        int code = 666;
        String message = "SUCCESS";
        userRepository.setDepartment_id(user_id,department_id);

        resultBean.setMessage(message);
        resultBean.setCode(code);

        return resultBean;
    }

    @Override
    public ResultBean setClass_Grade(String user_id, String class_grade) {
        ResultBean resultBean = new ResultBean();
        int code = 666;
        String message = "SUCCESS";
        userRepository.setClass_Grade(user_id,class_grade);

        resultBean.setMessage(message);
        resultBean.setCode(code);

        return resultBean;
    }

    @Override
    public ResultBean setPower(String user_id, int Power) {
        ResultBean resultBean = new ResultBean();
        int code = 666;
        String message = "SUCCESS";
        userRepository.setPower(user_id,Power);

        resultBean.setMessage(message);
        resultBean.setCode(code);

        return resultBean;
    }

    @Override
    public ResultBean setPwd(String user_id, String oldPwd, String newPwd) {
        ResultBean resultBean = new ResultBean();
        int code = 666;
        String message = "SUCCESS";

        //修改前先输入原始密码是否正确
        if(userRepository.verficatePwd(user_id,oldPwd) > 0){
            userRepository.setPwd(user_id,newPwd);
        }else {
            code = 555;
            message = "USER OLDPASSWORD IS ERROR!!";
        }

        resultBean.setMessage(message);
        resultBean.setCode(code);

        return resultBean;
    }

    @Override
    public ResultBean setPhoneNumber(String user_id, String user_phone, String code, HttpServletRequest request) {
        ResultBean resultBean = new ResultBean();
        int codeitem = 666;
        String message = "SUCCESS";
        //修改手机号 ，要验证码是否正确
        if(code.equals(request.getSession().getAttribute("code"))){
            userRepository.setPhoneNumber(user_id,user_phone);
        }else {//验证码错误
            codeitem = 555;
            message = "VERIFICATION CODE ERROR!!";
        }
        resultBean.setMessage(message);
        resultBean.setCode(codeitem);

        return resultBean;
    }

    @Override
    public ResultBean getUserById(String user_id) {
        ResultBean resultBean = new ResultBean();
        resultBean.setBean(userRepository.getUserById(user_id));
        resultBean.setMessage("SUCCESS");
        resultBean.setCode(666);

        return resultBean;
    }

    @Override
    public ResultBean getUserByPhone(String user_phone) {
        ResultBean resultBean = new ResultBean();
        resultBean.setBean(userRepository.getUserByPhone(user_phone));
        resultBean.setMessage("SUCCESS");
        resultBean.setCode(666);

        return resultBean;
    }

    @Override
    public ResultBean getUserCount() {
        ResultBean resultBean = new ResultBean();
        resultBean.setBean(userRepository.getUserCount());
        resultBean.setMessage("SUCCESS");
        resultBean.setCode(666);

        return resultBean;
    }


    @Override
    public ResultBean logout(String user_id) {
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage("SUCCESS");
        resultBean.setCode(666);

        return resultBean;
    }
}
