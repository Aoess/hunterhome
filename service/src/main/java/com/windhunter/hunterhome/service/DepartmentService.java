package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.ResultBean;

public interface DepartmentService {

    ResultBean getDepartmentById(String department_id);

    ResultBean getDepartmentByName(String department_name);

    ResultBean getDepartments();

    ResultBean getDepartmentCount();

    ResultBean addDepartment(String department_name);

    ResultBean setDepartmentById(String department_id, String department_name);

}
