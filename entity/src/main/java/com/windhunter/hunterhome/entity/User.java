package com.windhunter.hunterhome.entity;

import com.windhunter.hunterhome.validation.CannotHaveBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class User implements Serializable {

    @NotBlank(message = "用户id不能为空",groups = {getUsermessage.class})
    @Length(min = 32, max = 35, message = "用户id的长度必须在32~35位之间",groups = {getUsermessage.class})
    private String user_id;
    @NotBlank(message = "电话不能为空",groups = {phoneUpdate.class})
    @Pattern(regexp = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$", message = "请输入正确格式的手机号",groups = {phoneUpdate.class})
    private String user_phone;
    @NotBlank(message = "密码不能为空",groups = {passwordUpdate.class})
    @Length(min = 5, max = 15, message = "密码的长度必须在6~15位之间",groups = {passwordUpdate.class})
    @CannotHaveBlank(groups= {passwordupdate.class})
    private String user_pwd;
    @NotBlank(message = "昵称不能为空",groups = {nicknameUpdate.class})
    @Length(min = 1, max = 30, message = "昵称的长度必须在1~30位之间",groups = {nicknameUpdate.class})
    private String user_nickname;
    @NotNull(message = "性别不能为空",groups = {sexUpdate.class})
    @Length(min = 0, max = 1, message = "性别必须为男1或女0",groups = {sexUpdate.class})
    private String user_sex;
    @NotBlank(message = "头像不能为空",groups = {photoUpdate.class})
    @Length(min = 1, max = 100, message = "头像的长度必须在1~100位之间",groups = {photoUpdate.class})
    private String user_photo;
    @NotBlank(message = "权限码不能为空",groups = {powerUpdate.class})
    @Range(min = 1, max = 100, message = "权限码必须在1-100之间",groups = {powerUpdate.class})
    private int user_power;
    @NotBlank(message = "专业代号不能为空",groups = {departmentUpdate.class})
    @Range(min = 1, max = 50, message = "专业代号必须在1-50之间",groups = {departmentUpdate.class})
    private int department_id;
    @NotBlank(message = "班级不能为空",groups = {ClassGradeUpdate.class})
    @Length(min = 0, max = 5, message = "班级必须在1-50之间",groups = {ClassGradeUpdate.class})
    private String class_grade;


    public interface getUsermessage {};
    public interface phoneUpdate {};
    public interface passwordUpdate {};
    public interface passwordupdate {};
    public interface nicknameUpdate {};
    public interface sexUpdate {};
    public interface photoUpdate {};
    public interface powerUpdate {};
    public interface departmentUpdate {};
    public interface ClassGradeUpdate {};

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_photo='" + user_photo + '\'' +
                ", user_power=" + user_power +
                ", department_id=" + department_id +
                ", class_grade='" + class_grade + '\'' +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public int getUser_power() {
        return user_power;
    }

    public void setUser_power(int user_power) {
        this.user_power = user_power;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getClass_grade() {
        return class_grade;
    }

    public void setClass_grade(String class_grade) {
        this.class_grade = class_grade;
    }
}
