package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.Post;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PostRepository {

    @Insert("INSERT INTO table_post VALUES(#{post.post_id},#{post.writer_id},#{post.post_title},#{post.type_id},#{post.post_photo},#{post.post_content},#{post.post_public_time},#{post.post_process})")
    void addPost(@Param("post") Post post);

    @Update("UPDATE table_post SET writer_id = #{post.writer_id},post_title = #{post.post_title},type_id = #{post.type_id},post_photo = #{post.post_photo},post_content = #{post.post_content},post_public_time = #{post.post_public_time},post_process = #{post.post_process} WHERE BINARY post_id = #{post.post_id}")
    void setPost(@Param("post") Post post);

    @Update("UPDATE table_post SET post_process = #{post_process} WHERE BINARY post_id = #{post_id}")
    void setPostProcessById(@Param("post_id") String post_id, @Param("post_process") int post_process);

    @Update("UPDATE table_post SET post_process = #{post_process} WHERE BINARY writer_id = #{post.writer_id}")
    void setPostProcessByWriterId(@Param("writer_id") String writer_id, @Param("post_process") int post_process);

    @Select("SELECT * FROM table_post WHERE BINARY post_id = #{post_id}")
    Post getPostById(@Param("post_id") String post_id);

    Post getPosts();

    Post getPostCount();
}
