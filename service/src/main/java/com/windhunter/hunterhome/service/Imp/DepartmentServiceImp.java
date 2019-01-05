package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.Department;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.repository.DepartmentRepository;
import com.windhunter.hunterhome.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public ResultBean getDepartmentById(int department_id) {
        Department department = departmentRepository.getDepartmentById(department_id);
        ResultBean rb = new ResultBean(520,"SUCCESS",department);
        return rb;
    }

    @Override
    public ResultBean getDepartmentByName(String department_name) {
        ResultBean rb = new ResultBean(520,"SUCCESS",departmentRepository.getDepartmentByName(department_name));
        return rb;
    }

    @Override
    public ResultBean getDepartments() {
        ResultBean rb = new ResultBean(520,"SUCCESS",departmentRepository.getDepartments());
        return rb;
    }

    @Override
    public ResultBean getDepartmentCount() {
        ResultBean rb = new ResultBean(520,"SUCCESS",departmentRepository.getDepartmentCount());
        return rb;
    }

    @Override
    public ResultBean addDepartment(String department_name) {
        departmentRepository.addDepartment(department_name);
        ResultBean rb = new ResultBean(520,"SUCCESS");
        return rb;
    }

    @Override
    public ResultBean setDepartmentById(int department_id, String department_name) {
        departmentRepository.setDepartmentById(department_id,department_name);
        ResultBean rb = new ResultBean(520,"SUCCESS",null);
        return rb;
    }


}
