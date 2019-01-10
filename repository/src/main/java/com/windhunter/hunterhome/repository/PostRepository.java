package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {

    @Insert("INSERT INTO table_post VALUES(#{post.post_id},#{post.writer_id},#{post.post_title},#{post.type_id},#{post.post_photo},#{post.post_content},#{post.post_public_time},#{post.post_process})")
    void addPost(@Param("post") Post post);

    @Update("UPDATE table_post SET post_title = #{post.post_title},type_id = #{post.type_id},post_photo = #{post.post_photo},post_content = #{post.post_content} WHERE BINARY post_id = #{post.post_id}")
    void setPost(@Param("post") Post post);

    @Update("UPDATE table_post SET post_process = #{post_process} WHERE BINARY post_id = #{post_id}")
    void setPostProcessById(@Param("post_id") String post_id, @Param("post_process") Integer post_process);

    @Update("UPDATE table_post SET post_process = #{post_process} WHERE BINARY writer_id = #{post.writer_id}")
    void setPostProcessByWriterId(@Param("writer_id") String writer_id, @Param("post_process") Integer post_process);

    @Select("SELECT * FROM table_post WHERE BINARY post_id = #{post_id}")
    Post getPostById(@Param("post_id") String post_id);

    @Select("<script>"
            + "SELECT * FROM table_post"
                + "<where>"
                    + "<if test='post.writer_id != null'>"
                        + "AND BINARY writer_id = #{post.writer_id} "
                    + "</if>"
                    + "<if test='post.type_id != 0'>"
                        + "AND type_id = #{post.type_id} "
                    + "</if>"
                    + "<if test='post.post_process != 0'>"
                        + "AND post_process = #{post.post_process} "
                    + "</if>"
                    + "<if test='post.post_title != null'>"
                        + "AND binary post_title LIKE '%${post.post_title}%' "
                    + "</if>"
                + "</where>"
                + "ORDER BY post_public_time "
                + "<if test='post.post_public_time != null'>"
                    + " DESC "
                + "</if>"
                + "LIMIT #{startTag}, #{number}"
            + "</script>")
    List<Post> getPosts(@Param("post") Post post, @Param("startTag") Integer startTag, @Param("number") Integer number);

    @Select("<script>"
            + "SELECT COUNT(*) FROM table_post "
                + "<where>"
                    + "<if test='post.writer_id != null'>"
                        + "AND BINARY writer_id = #{post.writer_id} "
                    + "</if>"
                    + "<if test='post.type_id != 0'>"
                        + "AND type_id = #{post.type_id} "
                    + "</if>"
                    + "<if test='post.post_process != 0'>"
                        + "AND post_process = #{post.post_process} "
                    + "</if>"
                    + "<if test='post.post_title != null'>"
                        + "AND binary post_title LIKE '%${post.post_title}%' "
                    + "</if>"
                + "</where>"
            + "</script>")
    Long getPostCount(@Param("post") Post post);
}
