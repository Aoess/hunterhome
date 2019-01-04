package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {

    @Select("SELECT user_id,user_phone,user_nickname,user_sex,user_photo,user_power,department_id,class_grade FROM table_user WHERE BINARY user_phone = #{user_phone} AND user_pwd = #{user_pwd}")
    public User Login(@Param("user_phone") String user_phone, @Param("user_pwd") String user_pwd);

    @Insert("INSERT INTO table_user values(#{user.user_id},#{user.user_phone},#{user.user_pwd},#{user.user_nickname},#{user.user_sex},#{user.user_photo},#{user.user_power},#{user.department_id},#{user.class_grade})")
    public void register(@Param("user") User user);


}
