package com.windhunter.hunterhome.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Department implements Serializable {

    @NotNull(message = "专业码不能为空", groups = getDepartmentInfo.class)
    @Range(min = 1,max = 100,message = "专业码格式错误")
    private Integer department_id;
    @NotBlank(message = "专业名称不能为空", groups = updateDepartmentName.class)
    @Length(min = 1, max = 30, message = "专业名称长度必须在1~30位之间", groups = updateDepartmentName.class)
    private String department_name;

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public interface getDepartmentInfo{};
    public interface updateDepartmentName{};

    @Override
    public String toString() {
        return "Department{" +
                "department_id='" + department_id + '\'' +
                ", department_name='" + department_name + '\'' +
                '}';
    }
}
