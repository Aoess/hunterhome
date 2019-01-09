package com.windhunter.hunterhome.configuration;

import com.windhunter.hunterhome.Interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private UserAdminPermissionInterceptor userAdminPermissionInterceptor;
    @Autowired
    private StudioMumberPermissionInterceptor studioMumberPermissionInterceptor;
    @Autowired
    private PostAdminPermissionInterceptor postAdminPermissionInterceptor;
    @Autowired
    private SystemAdminPermissionInterceptor systemAdminPermissionInterceptor;
    @Autowired
    private MissionAdminPermissionInterceptor missionAdminPermissionInterceptor;
    @Autowired
    private NormalUserPermissionInterceptor normalUserPermissionInterceptor;


    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(normalUserPermissionInterceptor).addPathPatterns("/**/NormalUser/**");
        //用户管理权限拦截器
        registry.addInterceptor(userAdminPermissionInterceptor).addPathPatterns("/**/UserAdmin/**");
        //工作室成权限拦截器
        registry.addInterceptor(studioMumberPermissionInterceptor).addPathPatterns("/**/StudioMumber/**");
        //论坛管理权限拦截器
        registry.addInterceptor(postAdminPermissionInterceptor).addPathPatterns("/**/PostAdmin/**");
        //作业操作权限拦截器
        registry.addInterceptor(missionAdminPermissionInterceptor).addPathPatterns("/**/MissionAdmin/**");
        //系统管理权限拦截器
        registry.addInterceptor(systemAdminPermissionInterceptor).addPathPatterns("/**/SystemAdmin/**");
    }
}
