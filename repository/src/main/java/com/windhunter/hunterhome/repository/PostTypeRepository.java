package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.Department;
import com.windhunter.hunterhome.entity.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostTypeRepository {

    @Select("SELECT * FROM table_post_type WHERE BINARY type_id = #{type_id}")
    Type getPostTypeById(@Param("type_id") Integer type_id);

    @Select("SELECT * FROM table_post_type WHERE BINARY type_name = #{type_name}")
    Type getPostTypeByName(@Param("type_name") String type_name);

    @Select("SELECT * FROM table_post_type")
    List<Type> getPostTypes();

    @Select("SELECT COUNT(*) FROM table_post_type")
    int getPostTypeCount();

    @Insert("INSERT INTO table_post_type VALUES(null,#{type_name})")
    void addPostType(@Param("type_name") String type_name);

    @Update("UPDATE table_post_type SET type_name = #{type_name} WHERE type_id = #{type_id}")
    void setPostTypeById(@Param("type_id") Integer type_id, @Param("type_name") String type_name);

}
