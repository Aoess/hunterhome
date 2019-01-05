package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.validation.annotation.Validated;

@Mapper
public interface UserRepository {

    @Select("SELECT user_id,user_phone,user_nickname,user_sex,user_photo,user_power,department_id,class_grade FROM table_user WHERE BINARY user_phone = #{user_phone} AND user_pwd = #{user_pwd}")
    User Login(@Param("user_phone") String user_phone, @Param("user_pwd") String user_pwd);

    @Insert("INSERT INTO table_user values(#{user.user_id},#{user.user_phone},#{user.user_pwd},#{user.user_nickname},#{user.user_sex},#{user.user_photo},#{user.user_power},#{user.department_id},#{user.class_grade})")
    void register(@Param("user") User user);

    void supplementInformation(@Param("user_id") String user_id, @Param("user_nickname") String user_nickname, @Param("user_sex") String user_sex, @Param("user_photo") String user_photo, @Param("department_id") int department_id, @Param("class_grade") String class_grade);

    @Update("UPDATE table_user SET user_nickname = #{user_nickname} WHERE BINARY user_id = #{user_id}")
    void setNickname(@Param("user_id") String user_id, @Param("user_nickname") String user_nickname);

    @Update("UPDATE table_user SET user_sex = #{user_sex} WHERE BINARY user_id = #{user_id}")
    void setSex(@Param("user_id") String user_id, @Param("user_sex") String user_sex);

    @Update("UPDATE table_user SET user_photo = #{user_photo} WHERE BINARY user_id = #{user_id}")
    void setPhoto(@Param("user_id") String user_id, @Param("user_photo") String user_photo);

    @Update("UPDATE table_user SET department_id = #{department_id} WHERE BINARY user_id = #{user_id}")
    void setDepartment_id(@Param("user_id") String user_id, @Param("department_id") String department_id);

    @Update("UPDATE table_user SET class_grade = #{class_grade} WHERE BINARY user_id = #{user_id}")
    void setClass_Grade(@Param("user_id") String user_id, @Param("class_grade")String class_grade);

    @Update("UPDATE table_user SET user_power = #{user_power} WHERE BINARY user_id = #{user_id}")
    void setPower(@Param("user_id") String user_id, @Param("user_power")int user_power);

    @Update("UPDATE table_user SET user_pwd = #{user_pwd} WHERE BINARY user_id = #{user_id}")
    void setPwd(@Param("user_id") String user_id, @Param("user_pwd") String user_pwd);

    @Update("UPDATE table_user SET user_phone = #{user_phone} WHERE BINARY user_id = #{user_id}")
    void setPhoneNumber(@Param("user_id") String user_id, @Param("user_phone") String user_phone);

    @Select("SELECT user_id,user_phone,user_nickname,user_sex,user_photo,user_power,department_id,class_grade FROM table_user WHERE BINARY user_id = #{user_id}")
    User getUserById(@Param("user_id") String user_id);

    @Select("SELECT user_id,user_phone,user_nickname,user_sex,user_photo,user_power,department_id,class_grade FROM table_user WHERE BINARY user_phone = #{user_phone}")
    User getUserByPhone(@Param("user_phone") String user_phone);

    @Select("SELECT COUNT(*) FROM table_user")
    int getUserCount();

    @Select("SELECT user_photo FROM table_user WHERE BINARY user_id = #{user_id}")
    String getUserPhoto(@Param("user_id") String user_id);

    @Select("SELECT user_nickname FROM table_user WHERE BINARY user_id = #{user_id}")
    String getUserNickname(@Param("user_id") String user_id);
}
