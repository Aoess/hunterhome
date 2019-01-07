package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.LikeTag;
import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.ResultBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface LikeTagRepository {

    @Insert("INSERT INTO table_liketag VALUES(#{liketag.liketag_id},#{liketag.user_id},#{liketag.article_id},#{liketag.liketag_public_time})")
    void addLikeTag(@Param("liketag") LikeTag likeTag);

    @Update("UPDATE table_liketag SET user_id = #{liketag.user_id},article_id = #{liketag.article_id},liketag_public_time = #{liketag.liketag_public_time} WHERE BINARY liketag_id = #{liketag.liketag_id}")
    void setLikeTag(@Param("liketag") LikeTag likeTag);

    @Delete("DELETE FROM table_liketag WHERE BINARY liketag_id = #{liketag.liketag_id}")
    void deleteLikeTag(@Param("liketag_id") String liketag_id);

    @Select("SELECT * FROM table_liketag WHERE BINARY liketag_id = #{liketag.liketag_id},")
    LikeTag getLikeTagById(@Param("liketag") String liketag_id);

    //@Select()
    List<LikeTag> getLikeTags(Page page);

    int getLikeTagCount(@Param("liketag") LikeTag likeTag);
}
