package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.Department;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("department")
@ResponseBody
public class DepartmentControlller {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/getDepartmentById.do")
    public ResultBean getDepartmentById(@RequestBody @Validated(Department.getDepartmentInfo.class) Department department, BindingResult br) {
        ResultBean bean = null;
        if (br.getErrorCount() > 0) {
            bean = new ResultBean(230, br.getAllErrors().get(0).getDefaultMessage());
        } else {
            bean = departmentService.getDepartmentById(department.getDepartment_id());
        }
        return bean;
    }

    @RequestMapping("/getDepartmentByName.do")
    public ResultBean getDepartmentByName(@RequestBody @Validated(Department.updateDepartmentName.class) Department department, BindingResult br) {
        ResultBean bean = null;
        if (br.getErrorCount() > 0) {
            bean = new ResultBean(230, br.getAllErrors().get(0).getDefaultMessage());
        } else {
            bean = departmentService.getDepartmentByName(department.getDepartment_name());
        }
        return bean;
    }

    @RequestMapping("/getDepartments.do")
    public ResultBean getDepartments() {
        return departmentService.getDepartments();
    }

    @RequestMapping("/getDepartmentCount.do")
    public ResultBean getDepartmentCount() {
        return departmentService.getDepartmentCount();
    }

    @RequestMapping("/SystemAdmin/addDepartment.do")
    public ResultBean addDepartment(@RequestBody @Validated(Department.updateDepartmentName.class) Department department, BindingResult br) {
        ResultBean bean = null;
        if (br.getErrorCount() > 0) {
            bean = new ResultBean(230, br.getAllErrors().get(0).getDefaultMessage());
        } else {
            bean = departmentService.getDepartmentByName(department.getDepartment_name());
        }
        return bean;
    }

    @RequestMapping("/SystemAdmin/setDepartmentById.do")
    public ResultBean setDepartmentById(@RequestBody @Validated({Department.getDepartmentInfo.class,Department.getDepartmentInfo.class}) Department department, BindingResult br) {
        ResultBean bean = null;
        if (br.getErrorCount() > 0) {
            bean = new ResultBean(230, br.getAllErrors().get(0).getDefaultMessage());
        } else {
            bean = departmentService.setDepartmentById(department.getDepartment_id(),department.getDepartment_name());
        }
        return bean;
    }
}
