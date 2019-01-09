package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.Department;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.repository.DepartmentRepository;
import com.windhunter.hunterhome.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    public ResultBean getDepartmentById(int department_id) {
        List<Department> departments = (List<Department>) redisTemplate.opsForValue().get("departments");
        Department department = null;
        if(departments == null) {
            department = departmentRepository.getDepartmentById(department_id);
        }else {
            for(int i = 0; i < departments.size(); i++) {
                if(department_id == departments.get(i).getDepartment_id()) {
                    department = departments.get(i);
                    break;
                }
            }
        }
        ResultBean rb = new ResultBean(520,"SUCCESS",department);
        return rb;
    }

    @Override
    public ResultBean getDepartmentByName(String department_name) {
        List<Department> departments = (List<Department>) redisTemplate.opsForValue().get("departments");
        Department department = null;
        if(departments == null) {
            department = departmentRepository.getDepartmentByName(department_name);
        }else {
            for(int i = 0; i < departments.size(); i++) {
                if(department_name.equals(departments.get(i).getDepartment_name())) {
                    department = departments.get(i);
                    break;
                }
            }
        }
        ResultBean rb = new ResultBean(520,"SUCCESS",department);
        return rb;
    }

    @Override
    public ResultBean getDepartments() {
        //解决缓存穿透问题
        List<Department> departments = (List<Department>) redisTemplate.opsForValue().get("departments");
        ResultBean resultBean = null;
        if(departments == null) {
            synchronized (this) {
                if((List<Department>) redisTemplate.opsForValue().get("departments") == null) {
                    departments = departmentRepository.getDepartments();
                    redisTemplate.opsForValue().set("departments", departments);
                    System.out.println("mysql");
                }
                else {
                    System.out.println("redis");
                }
            }
        }
        else {
            System.out.println("redis");
        }
        resultBean = new ResultBean(520,"SUCCESS",departments);
        return resultBean;
    }

    @Override
    public ResultBean getDepartmentCount() {
        List<Department> departments = (List<Department>) redisTemplate.opsForValue().get("departments");
        int count;
        if(departments == null) {
            count = departmentRepository.getDepartmentCount();
        }else {
            count =departments.size();
        }
        ResultBean rb = new ResultBean(520,"SUCCESS",count);
        return rb;
    }

    @Override
    public ResultBean addDepartment(String department_name) {
        departmentRepository.addDepartment(department_name);
        redisTemplate.delete("departments");
        ResultBean rb = new ResultBean(520,"SUCCESS");
        return rb;
    }

    @Override
    public ResultBean setDepartmentById(int department_id, String department_name) {
        departmentRepository.setDepartmentById(department_id,department_name);
        redisTemplate.delete("departments");
        ResultBean rb = new ResultBean(520,"SUCCESS",null);
        return rb;
    }


}
