package com.windhunter.hunterhome.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {
    /*post(
    post_id VARCHAR(32) PRIMARY KEY,
    writer_id VARCHAR(32) NOT NULL,
    post_title VARCHAR(50) NOT NULL,
    type_id SMALLINT NOT NULL,
    post_photo VARCHAR(100) NOT NULL,
    post_content VARCHAR(100) NOT NULL,
    post_public_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    post_process VARCHAR(1) NOT NULL,
    FOREIGN KEY (type_id) REFERENCES table_post_type(type_id),
    FOREIGN KEY (writer_id) REFERENCES table_user(user_id)
    )ENGINE=INNODB DEFAULT CHARSET=UTF8;*/

    @NotBlank(message = "帖子id不能为空",groups = {getPostmessage.class})
    @Length(min = 32, max = 35, message = "帖子id的长度必须在32~35位之间",groups = {getPostmessage.class})
    private String post_id;
    @NotBlank(message = "发帖人id不能为空",groups = {updatePostWriter.class})
    @Length(min = 32, max = 35, message = "发帖人id的长度必须在32~35位之间",groups = {updatePostWriter.class})
    private String writer_id;
    @NotBlank(message = "帖子名不能为空",groups = {updatePostTitle.class})
    @Length(min = 3, max = 30, message = "帖子id的长度必须在3~30位之间",groups = {updatePostTitle.class})
    private String post_title;
    @NotNull(message = "类型码不能为空",groups = {updateTypeId.class})
    @Range(min = 0, max = 20, message = "类型码格式不对",groups = {updateTypeId.class})
    private Integer type_id;
    private String post_photo;
    private String post_content;
    private Timestamp post_public_time;
    @NotNull(message = "权限码不能为空",groups = {updateProcess.class})
    @Range(min = 1, max = 10, message = "权限码格式不对",groups = {updateProcess.class})
    private Integer post_process;

    public Post(){
    }

    public interface getPostmessage{};
    public interface updatePostWriter{};
    public interface updatePostTitle{};
    public interface updateTypeId{};
    public interface updateProcess{};

    @Override
    public String toString() {
        return "Post{" +
                "post_id='" + post_id + '\'' +
                ", writer_id='" + writer_id + '\'' +
                ", post_title='" + post_title + '\'' +
                ", type_id=" + type_id +
                ", post_photo='" + post_photo + '\'' +
                ", post_content='" + post_content + '\'' +
                ", post_public_time=" + post_public_time +
                ", post_process='" + post_process + '\'' +
                '}';
    }

    public Integer getPost_process() {
        return post_process;
    }

    public void setPost_process(Integer post_process) {
        this.post_process = post_process;
    }

    public Timestamp getPost_public_time() {

        return post_public_time;
    }

    public void setPost_public_time(Timestamp post_public_time) {
        this.post_public_time = post_public_time;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_photo() {

        return post_photo;
    }

    public void setPost_photo(String post_photo) {
        this.post_photo = post_photo;
    }

    public Integer getType_id() {

        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getPost_id() {

        return post_id;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
}
