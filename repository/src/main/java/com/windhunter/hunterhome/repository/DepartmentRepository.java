package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentRepository {

    @Select("SELECT * FROM table_department WHERE BINARY department_id = #{department_id}")
    Department getDepartmentById(@Param("department_id") int department_id);

    @Select("SELECT * FROM table_department WHERE BINARY department_name = #{department_name}")
    Department getDepartmentByName(@Param("department_name") String department_name);

    @Select("SELECT * FROM table_department")
    List<Department> getDepartments();

    @Select("SELECT COUNT(*) FROM table_department")
    int getDepartmentCount();

    @Insert("INSERT INTO table_department VALUES(null,#{department_name})")
    void addDepartment(@Param("department_name") String department_name);

    @Update("UPDATE table_department SET department_name = #{department_name} WHERE department_id = #{department_id}")
    void setDepartmentById(@Param("department_id") int department_id, @Param("department_name") String department_name);

}
