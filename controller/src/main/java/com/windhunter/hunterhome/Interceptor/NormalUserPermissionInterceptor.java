package com.windhunter.hunterhome.Interceptor;

import com.windhunter.hunterhome.entity.Power;
import com.windhunter.hunterhome.utils.LoginManager;
import com.windhunter.hunterhome.utils.TokenUtils;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class NormalUserPermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;
    /*
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    /*
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        System.out.println("进入登录拦截器");
        Cookie[] cookies = request.getCookies();
        String token = null;
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if("token".equals(cookies[i].getName())) {
                    token = cookies[i].getValue();
                }
            }
            if(token == null || "undefined".equals(token)) {
                request.setAttribute("cause","login permission error");
            }
            else {
                Map<String, Object> valid = TokenUtils.valid(token);
                switch ((int)valid.get("Result")) {
                    case 0 : {
                        JSONObject jsonObject = (JSONObject) valid.get("data");
                        String user_id = (String) jsonObject.get("user_id");
                        //必须通过登录管理器才能证明此用户处于安全的登录状态
                        if(user_id != null && user_id.equals(redisTemplate.opsForValue().get(token))) {
                            //权限判断 ,本人操作或者有管理员权限就放行
                            Power power = new Power((long) jsonObject.get("user_power"));
                            if(power.isHasNUpermission()) {
                                request.setAttribute("user_id",user_id);
                                request.setAttribute("power",power);
                                return true;
                            }
                        }
                        request.setAttribute("cause","token error please login again!");
                        break;
                    }case 1 : {
                        //即使token过期,只要设置了自动登录.就可以登录
                        JSONObject jsonObject = (JSONObject) valid.get("data");
                        //权限判断 ,本人操作或者有管理员权限就放行
                        Power power = new Power((long) jsonObject.get("user_power"));
                        String user_id = (String) jsonObject.get("user_id");
                        if(token.equals(LoginManager.getLoginflag(user_id))) {
                            request.setAttribute("user_id",jsonObject.get("user_id"));
                            request.setAttribute("power",power);
                            return true;
                        }
                        request.setAttribute("cause","token expire!");
                        break;
                    }default:{
                        request.setAttribute("cause","token error please login again!");
                        break;
                    }
                }
            }
        }
        request.getRequestDispatcher("/token/tokenError.do").forward(request,response);
        return false;
    }

}

