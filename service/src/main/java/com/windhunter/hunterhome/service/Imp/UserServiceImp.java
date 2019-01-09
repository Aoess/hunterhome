package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.User;
import com.windhunter.hunterhome.repository.UserRepository;
import com.windhunter.hunterhome.service.UserService;
import com.windhunter.hunterhome.utils.LoginManager;
import com.windhunter.hunterhome.utils.TokenUtils;
import com.windhunter.hunterhome.validation.ManualValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    public ResultBean login(String user_phone, String user_pwd, String autoLogin, HttpServletResponse response) {
        User user = userRepository.login(user_phone, user_pwd);
        ResultBean resultBean = new ResultBean();
        int code;
        String message;
        if (null != user) {
            code = 666;
            message = "SUCCESS";
            String token = TokenUtils.getToken(user.getUser_id(),user.getUser_power(), (long) (1000*60*60*5));
            //设置自动登录
            if("on".equals(autoLogin)) {
                LoginManager.AddLoginflag(user.getUser_id(),token);
            }
            //把用户信息存到缓存
            redisTemplate.opsForValue().set(user.getUser_id(),user,30,TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(token,user.getUser_id(),5,TimeUnit.HOURS);
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
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
            //if(code.equals(request.getSession().getAttribute("520111"))){
            if(code.equals("520111")){
                user = new User();
                user.setUser_pwd(user_pwd);
                user.setUser_power(1);
                user.setUser_photo("default.png");
                user.setUser_phone(user_phone);
                user.setUser_nickname("User"+user_phone.substring(7,11));
                user.setDepartment_id(1);
                user.setClass_grade("000");
                user.setUser_sex("1");
                user.setUser_id(UUID.randomUUID().toString().replace("-",""));
                //保存到数据库
                userRepository.register(user);
            }else {
                //验证码错误
                codeitem = 555;
                message = "VERIFICATION CODE ERROR!!";
            }
        }else{//用户已存在
            codeitem = 555;
            message = "THE USER ALREADY EXISTS!!";
        }
        resultBean.setMessage(message);
        resultBean.setCode(codeitem);

        return resultBean;
    }

    @Override
    public ResultBean supplementInformation(User user) {
        user.setUser_nickname(ManualValidation.strTransform(user.getUser_nickname(),"defaultUser",1,15));
        user.setUser_sex(ManualValidation.strTransform(user.getUser_sex(),"1",1,1));
        user.setUser_photo(ManualValidation.strTransform(user.getUser_photo(),"Default.png",1,100));
        user.setClass_grade(ManualValidation.strTransform(user.getUser_photo(),"162",1,3));
        user.setDepartment_id(ManualValidation.typeIdTransform(user.getDepartment_id(),1));
        userRepository.supplementInformation(user.getUser_id(), user.getUser_nickname(),user.getUser_sex(),user.getUser_photo(),user.getDepartment_id(),user.getClass_grade());
        //删除缓存
        redisTemplate.delete(user.getUser_id());
        ResultBean resultBean = new ResultBean(666,"SUCCESS");
        return  resultBean;
    }

    @Override
    public ResultBean setNickname(String user_id, String user_nickname) {
        ResultBean resultBean = new ResultBean();
        int code = 666;
        String message = "SUCCESS";
        userRepository.setNickname(user_id,user_nickname);
        //删除缓存
        redisTemplate.delete(user_id);
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
        //删除缓存
        redisTemplate.delete(user_id);
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
        //删除缓存
        redisTemplate.delete(user_id);
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
        //删除缓存
        redisTemplate.delete(user_id);
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
        //删除缓存
        redisTemplate.delete(user_id);
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
        //删除缓存
        redisTemplate.delete(user_id);
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
            //删除缓存
            redisTemplate.delete(user_id);
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
            //删除缓存
            redisTemplate.delete(user_id);
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
        User user = (User) redisTemplate.opsForValue().get(user_id);
        if(user == null) {
            user = userRepository.getUserById(user_id);
            redisTemplate.opsForValue().set(user.getUser_id(),user,30,TimeUnit.MINUTES);
        }
        ResultBean resultBean = new ResultBean();
        resultBean.setBean(user);
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
    public ResultBean logout(String user_id, HttpServletResponse response) {
        Cookie cookie = new Cookie("token","logout");
        response.addCookie(cookie);
        response.setHeader("token","logout");
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage("SUCCESS");
        resultBean.setCode(666);
        return resultBean;
    }

    @Override
    public ResultBean getUserPhoto(String user_id) {
        User user = (User) redisTemplate.opsForValue().get(user_id);
        String photoUser;
        if(user == null) {
            photoUser = userRepository.getUserPhoto(user_id);
        }else {
            photoUser = user.getUser_photo();
        }
        return new ResultBean(666,"SUCCESS",photoUser);
    }

    @Override
    public ResultBean getUserNickname(String user_id) {
        User user = (User) redisTemplate.opsForValue().get(user_id);
        String userNickName;
        if(user == null) {
            userNickName = userRepository.getUserNickname(user_id);
        }else {
            userNickName = user.getUser_nickname();
        }
        return new ResultBean(666,"SUCCESS",userNickName);
    }
}
