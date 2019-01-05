package com.windhunter.hunterhome.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    /*CREATE TABLE table_comment(
    id VARCHAR(32) PRIMARY KEY,
    writer_id VARCHAR(32) NOT NULL,
    article_id VARCHAR(32) NOT NULL,
    content VARCHAR(100) NOT NULL,
    public_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (writer_id) REFERENCES table_user(user_id)
    )ENGINE=INNODB DEFAULT CHARSET=UTF8;*/
    private String id;
    private String writer_id;
    private String article_id;
    private String content;
    private Timestamp public_time;

    public Comment(){
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", writer_id='" + writer_id + '\'' +
                ", article_id='" + article_id + '\'' +
                ", content='" + content + '\'' +
                ", public_time=" + public_time +
                '}';
    }

    public Timestamp getPublic_time() {
        return public_time;
    }

    public void setPublic_time(Timestamp public_time) {
        this.public_time = public_time;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticle_id() {

        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getWriter_id() {

        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
