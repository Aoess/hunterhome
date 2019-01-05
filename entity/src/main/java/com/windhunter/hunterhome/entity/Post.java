package com.windhunter.hunterhome.entity;

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

    private String post_id;
    private String writer_id;
    private String post_title;
    private int type_id;
    private String post_photo;
    private String post_content;
    private Timestamp post_public_time;
    private String post_process;

    public Post(){
    }

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

    public String getPost_process() {
        return post_process;
    }

    public void setPost_process(String post_process) {
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

    public int getType_id() {

        return type_id;
    }

    public void setType_id(int type_id) {
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
