package com.windhunter.hunterhome.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class LikeTag implements Serializable {

    private String liketag_id;
    private String user_id;
    private String article_id;
    private Timestamp liketag_public_time;

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
