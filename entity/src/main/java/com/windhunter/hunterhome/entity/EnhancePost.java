package com.windhunter.hunterhome.entity;

import java.io.Serializable;

public class EnhancePost extends Post {
    private String nick_name;
    private long liketag_count;
    private long comment_count;
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

    public void inputPostInfo(Post post) {
        this.setPost_id(post.getPost_id());
        this.setPost_content(post.getPost_content());
        this.setPost_photo(post.getPost_photo());
        this.setPost_process(post.getPost_process());
        this.setPost_public_time(post.getPost_public_time());
        this.setPost_title(post.getPost_title());
        this.setType_id(post.getType_id());
        this.setWriter_id(post.getWriter_id());
    }

    public EnhancePost() {

    }

    public EnhancePost(Post post) {
        inputPostInfo(post);
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

    public void setComment_count(long comment_count) {
        this.comment_count = comment_count;
    }

    public long getLiketag_count() {

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
