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
public class UserAdminPermissionInterceptor implements HandlerInterceptor {

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
        Map<String, Object> valid = TokenUtils.valid(request.getHeader("token"));
        switch ((int)valid.get("Result")) {
            case 0 : {
                //权限判断 ,本人操作或者有管理员权限就放行
                if(valid.get("user_id").equals(request.getParameter("user_id")) || new Power((int)valid.get("user_power")).isHasNUpermission()) {
                    return true;
                }
                request.setAttribute("cause","token error please login again!");
                break;
            }case 1 : {
                request.setAttribute("cause","token expire!");
                break;
            }default:{
                request.setAttribute("cause","token error please login again!");
                break;
            }
        }
        request.getRequestDispatcher("/token/tokenError.do").forward(request,response);

        return false;
    }

}

