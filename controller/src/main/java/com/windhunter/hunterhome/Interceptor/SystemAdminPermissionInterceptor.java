package com.windhunter.hunterhome.Interceptor;

import com.windhunter.hunterhome.entity.Power;
import com.windhunter.hunterhome.utils.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class SystemAdminPermissionInterceptor implements HandlerInterceptor {

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
        System.out.println("进入系统管理员拦截器");
        Power user_power = (Power) request.getAttribute("power");
        if(user_power.isHasSApermission()){
            return true;
        }
        request.setAttribute("cause","Permission lack");
        request.getRequestDispatcher("/token/tokenError.do").forward(request,response);
        return false;
    }

}

