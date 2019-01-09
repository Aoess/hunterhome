package com.windhunter.hunterhome.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

public class LikeTag implements Serializable {

    @NotBlank(message = "点赞id不能为空",groups = {getLikeTagmessage.class})
    @Length(min = 32, max = 35, message = "点赞id的长度必须在32~35位之间",groups = {getLikeTagmessage.class})
    private String liketag_id;
    @NotBlank(message = "点赞人id不能为空",groups = {updateUseId.class})
    @Length(min = 32, max = 35, message = "点赞人id的长度必须在32~35位之间",groups = {updateUseId.class})
    private String user_id;
    @NotBlank(message = "点赞文章id不能为空",groups = {updateArticleId.class})
    @Length(min = 32, max = 35, message = "点赞id的长度必须在32~35位之间",groups = {updateArticleId.class})
    private String article_id;
    private Timestamp liketag_public_time;

    public interface getLikeTagmessage{};
    public interface updateUseId{};
    public interface updateArticleId{};

    public LikeTag(){
    }

    @Override
    public String toString() {
        return "LikeTag{" +
                "liketag_id='" + liketag_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", article_id='" + article_id + '\'' +
                ", liketag_public_time=" + liketag_public_time +
                '}';
    }

    public Timestamp getLiketag_public_time() {
        return liketag_public_time;
    }

    public void setLiketag_public_time(Timestamp liketag_public_time) {
        this.liketag_public_time = liketag_public_time;
    }

    public String getArticle_id() {

        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getUser_id() {

        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLiketag_id() {

        return liketag_id;
    }

    public void setLiketag_id(String liketag_id) {
        this.liketag_id = liketag_id;
    }
}
