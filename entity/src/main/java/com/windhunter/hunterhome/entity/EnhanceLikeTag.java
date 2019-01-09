package com.windhunter.hunterhome.entity;

public class EnhanceLikeTag extends LikeTag{
    private String user_photo;
    private String user_nikename;

    public void inputTagInfo(LikeTag likeTag) {
        this.setUser_id(likeTag.getUser_id());
        this.setArticle_id(likeTag.getArticle_id());
        this.setLiketag_id(likeTag.getLiketag_id());
        this.setLiketag_public_time(likeTag.getLiketag_public_time());
    }


    public EnhanceLikeTag(LikeTag likeTag) {
        inputTagInfo(likeTag);
    }

    public EnhanceLikeTag() {
    }

    @Override
    public String toString() {
        return "EnhanceLikeTag{" +
                "user_photo='" + user_photo + '\'' +
                ", user_nikename='" + user_nikename + '\'' +
                '}';
    }

    public String getUser_nikename() {
        return user_nikename;
    }

    public void setUser_nikename(String user_nikename) {
        this.user_nikename = user_nikename;
    }

    public String getUser_photo() {

        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }
}
