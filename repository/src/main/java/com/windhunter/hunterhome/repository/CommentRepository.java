package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.Comment;
import com.windhunter.hunterhome.entity.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentRepository {

    @Insert("INSERT INTO table_comment VALUES(#{comment.comment_id},#{comment.writer_id},#{comment.article_id},#{comment.comment_content},#{comment.comment_public_time},#{comment.comment_process})")
    void addComment(Comment comment);

    @Update("UPDATE table_comment SET writer_id = #{comment.writer_id},article_id = #{comment.article_id},comment_content = #{comment.comment_content},comment_public_time = #{comment.comment_public_time},comment_process = #{comment.comment_process} WHERE BINARY comment_id = #{comment.comment_id}")
    void setComment(Comment comment);

    @Update("UPDATE table_comment SET comment_process = #{comment.comment_process} WHERE BINARY comment_id = #{comment.comment_id}")
    void setCommentProcessById(String comment_id, int comment_process);

    @Update("UPDATE table_comment SET comment_process = #{comment.comment_process} WHERE BINARY writer_id = #{comment.writer_id}")
    void setCommentProcessByWriterId(String writer_id, int comment_process);

    @Select("SELECT * FROM table_comment WHERE BINARY comment_id = #{comment.comment_id}")
    Comment getCommentById(String comment_id);

    List<Comment> getComments(Page page);

    Integer getCommentCount(Comment comment);
}
