package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.Type;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("posttype")
@ResponseBody
public class PostTypeController {

    @Autowired
    private PostTypeService postTypeService;

    @RequestMapping("/getTypeById.do")
    public ResultBean getTypeById(@RequestBody @Validated(Type.getTypeInfo.class) Type type, BindingResult br) {
        ResultBean bean = null;
        if (br.getErrorCount() > 0) {
            bean = new ResultBean(230, br.getAllErrors().get(0).getDefaultMessage());
        } else {
            bean = postTypeService.getTypeById(type.getType_id());
        }
        return bean;
    }

    @RequestMapping("/getTypeByName.do")
    public ResultBean getTypeByName(@RequestBody @Validated(Type.updateTypeName.class) Type type, BindingResult br) {
        ResultBean bean = null;
        if (br.getErrorCount() > 0) {
            bean = new ResultBean(230, br.getAllErrors().get(0).getDefaultMessage());
        } else {
            bean = postTypeService.getTypeByName(type.getType_name());
        }
        return bean;
    }

    @RequestMapping("/PostTypeServiceImp.do")
    public ResultBean getTypes() {
        return postTypeService.getTypes();
    }

    @RequestMapping("/getTypeCount.do")
    public ResultBean getTypeCount() {
        return postTypeService.getTypeCount();
    };

    @RequestMapping("/SystemAdmin/addType.do")
    ResultBean addType(@RequestBody @Validated(Type.updateTypeName.class) Type type, BindingResult br) {
        ResultBean bean = null;
        if (br.getErrorCount() > 0) {
            bean = new ResultBean(230, br.getAllErrors().get(0).getDefaultMessage());
        } else {
            bean = postTypeService.addType(type.getType_name());
        }
        return bean;
    }

    @RequestMapping("/SystemAdmin/setTypeById.do")
    ResultBean setTypeById(@RequestBody @Validated({Type.updateTypeName.class,Type.getTypeInfo.class}) Type type, BindingResult br) {
        ResultBean bean = null;
        if (br.getErrorCount() > 0) {
            bean = new ResultBean(230, br.getAllErrors().get(0).getDefaultMessage());
        } else {
            bean = postTypeService.setTypeById(type.getType_id(),type.getType_name());
        }
        return bean;
    }

}
