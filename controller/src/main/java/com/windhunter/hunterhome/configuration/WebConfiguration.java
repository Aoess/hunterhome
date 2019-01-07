package com.windhunter.hunterhome.configuration;

import com.windhunter.hunterhome.Interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private UserAdminPermissionInterceptor userAdminPermissionInterceptor;
    @Resource
    private StudioMumberPermissionInterceptor studioMumberPermissionInterceptor;
    @Resource
    private PostAdminPermissionInterceptor postAdminPermissionInterceptor;
    @Resource
    private SystemAdminPermissionInterceptor systemAdminPermissionInterceptor;
    @Resource
    private MissionAdminPermissionInterceptor missionAdminPermissionInterceptor;


    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        //用户管理权限拦截器
        registry.addInterceptor(userAdminPermissionInterceptor).addPathPatterns("**/userAdmin/**");
        //工作室成权限拦截器
        registry.addInterceptor(studioMumberPermissionInterceptor).addPathPatterns("**/studioMumber/**");
        //论坛管理权限拦截器
        registry.addInterceptor(postAdminPermissionInterceptor).addPathPatterns("**/postAdmin/**");
        //作业操作权限拦截器
        registry.addInterceptor(missionAdminPermissionInterceptor).addPathPatterns("**/missionAdmin/**");
        //系统管理权限拦截器
        registry.addInterceptor(systemAdminPermissionInterceptor).addPathPatterns("**/systemAdmin/**");
    }
}
