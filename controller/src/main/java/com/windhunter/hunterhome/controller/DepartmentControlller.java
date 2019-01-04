package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.service.Imp.DepartmentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("department")
@ResponseBody
public class DepartmentControlller {

    @Autowired
    private DepartmentServiceImp departmentService;

    @RequestMapping("/{department_id}")
    public ResultBean getDepartmentById(@PathVariable("department_id")String department_id) {
        System.out.println(department_id);
        return departmentService.getDepartmentById(department_id);
    }
}
