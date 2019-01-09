package com.windhunter.hunterhome.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    /*CREATE TABLE table_comment(
    comment_id VARCHAR(32) PRIMARY KEY,
    writer_id VARCHAR(32) NOT NULL,
    article_id VARCHAR(32) NOT NULL,
    comment_content VARCHAR(100) NOT NULL,
    comment_process VARCHAR(1) NOT NULL,
    comment_public_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (writer_id) REFERENCES table_user(user_id)
            )ENGINE=INNODB DEFAULT CHARSET=UTF8;*/

    @NotBlank(message = "评论id不能为空",groups = {getCommentmessage.class})
    @Length(min = 32, max = 35, message = "评论id的长度必须在32~35位之间",groups = {getCommentmessage.class})
    private String comment_id;
    @NotBlank(message = "评论人id不能为空",groups = {updateWriter.class})
    @Length(min = 32, max = 35, message = "评论人id的长度必须在32~35位之间",groups = {updateWriter.class})
    private String writer_id;
    @NotBlank(message = "评论文章id不能为空",groups = {updateArticleid.class})
    @Length(min = 32, max = 35, message = "评论文章id的长度必须在32~35位之间",groups = {updateArticleid.class})
    private String article_id;
    private String comment_content;
    private Timestamp comment_public_time;
    @NotNull(message = "权限码不能为空",groups = {updateCProcess.class})
    @Range(min = 1, max = 10, message = "权限码格式不对",groups = {updateCProcess.class})
    private Integer comment_process;

    public interface getCommentmessage{};
    public interface updateWriter{};
    public interface updateArticleid{};
    public interface updateCProcess{};

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id='" + comment_id + '\'' +
                ", writer_id='" + writer_id + '\'' +
                ", article_id='" + article_id + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_public_time=" + comment_public_time +
                ", comment_process='" + comment_process + '\'' +
                '}';
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Timestamp getComment_public_time() {
        return comment_public_time;
    }

    public void setComment_public_time(Timestamp comment_public_time) {
        this.comment_public_time = comment_public_time;
    }

    public Integer getComment_process() {
        return comment_process;
    }

    public void setComment_process(Integer comment_process) {
        this.comment_process = comment_process;
    }
}
