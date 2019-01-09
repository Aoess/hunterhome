package com.windhunter.hunterhome.entity;

public class EnhanceComment extends Comment {
    private String writer_photo;
    private String writer_nikename;
    private Long liketag_count;

    public EnhanceComment(Comment comment) {
        inputCommentInfo(comment);
    }
    public void inputCommentInfo(Comment comment){
        this.setArticle_id(comment.getArticle_id());
        this.setComment_content(comment.getComment_content());
        this.setComment_id(comment.getComment_id());
        this.setComment_process(comment.getComment_process());
        this.setComment_public_time(comment.getComment_public_time());
        this.setWriter_id(comment.getWriter_id());
    }
    public Long getLiketag_total() {
        return liketag_count;
    }

    public void setLiketag_count(Long liketag_count) {
        this.liketag_count = liketag_count;
    }

    @Override
    public String toString() {
        return "EnhanceComment{" +
                "writer_photo='" + writer_photo + '\'' +
                ", writer_nikename='" + writer_nikename + '\'' +
                ", liketag_count='" + liketag_count + '\'' +
                '}';
    }

    public String getWriter_nikename() {
        return writer_nikename;
    }

    public void setWriter_nikename(String writer_nikename) {
        this.writer_nikename = writer_nikename;
    }

    public String getWriter_photo() {

        return writer_photo;
    }

    public void setWriter_photo(String writer_photo) {
        this.writer_photo = writer_photo;
    }
}
