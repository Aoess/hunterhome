package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.Department;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DepartmentServiceImp {

    @Autowired
    private DepartmentRepository departmentRepository;

    public ResultBean getDepartmentById(String department_id) {
        Department department = departmentRepository.getDepartmentById(department_id);
        ResultBean rb = new ResultBean(520,"SUCCESS",department);
        return rb;
    }
}
