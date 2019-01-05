package com.windhunter.hunterhome.controllerAdvice;

import com.windhunter.hunterhome.entity.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultBean processException(Exception e){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.getMessage());
        stringBuilder.append("\n");
        stringBuilder.append("**********************************************************************************");
        stringBuilder.append("\n");
        stringBuilder.append(e.getClass());
        stringBuilder.append(" : ");
        stringBuilder.append(e.getMessage());
        stringBuilder.append("\n");
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            stringBuilder.append(stackTraceElement.toString());
            stringBuilder.append("\n");
        }
        stringBuilder.append("**********************************************************************************");
        log.error(stringBuilder.toString());
        //友好提示异常
        ResultBean bean = new ResultBean(500,"Exception",e.getMessage());
        return bean;
    }
}

