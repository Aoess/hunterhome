package com.windhunter.hunterhome.entity;

import java.io.Serializable;

public class EnhancePost extends Post {
    private String nick_name;
    private Long liketag_count;
    private Long comment_count;
    private String user_photo;

    @Override
    public String toString() {
        return "EnhancePost{" +
                "nick_name='" + nick_name + '\'' +
                ", liketag_count=" + liketag_count +
                ", comment_total=" + comment_count +
                ", user_photo='" + user_photo + '\'' +
                '}';
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public Long getComment_count() {

        return comment_count;
    }

    public void setComment_count(Long comment_count) {
        this.comment_count = comment_count;
    }

    public Long getLiketag_count() {

        return liketag_count;
    }

    public void setLiketag_count(Long liketag_count) {
        this.liketag_count = liketag_count;
    }

    public String getNick_name() {

        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
