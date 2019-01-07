package com.windhunter.hunterhome.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Type implements Serializable {

    @NotNull(message = "类型码不能为空", groups = Type.getTypeInfo.class)
    @Range(min = 1,max = 100,message = "类型码格式错误", groups = Type.getTypeInfo.class)
    private Integer type_id;
    @NotBlank(message = "类型名称不能为空", groups = Type.updateTypeName.class)
    @Length(min = 1, max = 30, message = "类型名称长度必须在1~30位之间", groups = Type.updateTypeName.class)
    private String type_name;

    public interface getTypeInfo{};
    public interface updateTypeName{};

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "Post{" +
                "type_id=" + type_id +
                ", type_name='" + type_name + '\'' +
                '}';
    }
}
