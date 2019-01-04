package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentRepository {

    @Select("SELECT * FROM table_department WHERE BINARY department_id = #{department_id}")
    public Department getDepartmentById(@Param("department_id") String department_id);

}
