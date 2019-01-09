package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.LikeTag;
import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.ResultBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeTagRepository {

    @Insert("INSERT INTO table_liketag VALUES(#{liketag.liketag_id},#{liketag.user_id},#{liketag.article_id},#{liketag.liketag_public_time})")
    void addLikeTag(@Param("liketag") LikeTag likeTag);

    @Update("UPDATE table_liketag SET user_id = #{liketag.user_id},article_id = #{liketag.article_id},liketag_public_time = #{liketag.liketag_public_time} WHERE BINARY liketag_id = #{liketag.liketag_id}")
    void setLikeTag(@Param("liketag") LikeTag likeTag);

    @Delete("DELETE FROM table_liketag WHERE BINARY liketag_id = #{liketag.liketag_id}")
    void deleteLikeTag(@Param("liketag_id") String liketag_id);

    @Select("SELECT * FROM table_liketag WHERE BINARY liketag_id = #{liketag.liketag_id},")
    LikeTag getLikeTagById(@Param("liketag") String liketag_id);

    @Select("SELECT * FROM table_liketag WHERE BINARY user_id = #{user_id} AND BINARY article_id = #{user_id}")
    LikeTag getLikeTagByUidAndAid(@Param("user_id") String user_id, @Param("article_id") String article_id);

    @Select("<script>"
                + "SELECT * FROM table_liketag"
                + "<where>"
                    + "<if test='likeTag.user_id != null'>"
                        + "AND BINARY user_id = #{likeTag.user_id} "
                    + "</if>"
                    + "<if test='likeTag.article_id != null'>"
                        + "AND article_id = #{likeTag.article_id} "
                    + "</if>"
                + "</where>"
                + "ORDER BY liketag_public_time "
                + "<if test='likeTag.liketag_public_time != null'>"
                    + " DESC "
                + "</if>"
                + "LIMIT #{startTag}, #{number}"
            + "</script>")
    List<LikeTag> getLikeTags(@Param("likeTag")LikeTag likeTag, @Param("startTag")Integer startTag, @Param("number")Integer number);

    @Select("<script>"
                + "SELECT COUNT(*) FROM table_liketag"
                + "<where>"
                    + "<if test='liketag.user_id != null'>"
                        + "AND BINARY user_id = #{liketag.user_id} "
                    + "</if>"
                    + "<if test='liketag.article_id != null'>"
                        + "AND article_id = #{liketag.article_id} "
                    + "</if>"
                + "</where>"
            + "</script>")
    Integer getLikeTagCount(@Param("liketag") LikeTag likeTag);
}
