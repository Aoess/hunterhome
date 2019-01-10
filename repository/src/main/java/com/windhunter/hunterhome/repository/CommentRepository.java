package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.Comment;
import com.windhunter.hunterhome.entity.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentRepository {

    @Insert("INSERT INTO table_comment VALUES(#{comment.comment_id},#{comment.writer_id},#{comment.article_id},#{comment.comment_content},#{comment.comment_public_time},#{comment.comment_process})")
    void addComment(@Param("comment") Comment comment);

    @Update("UPDATE table_comment SET comment_content = #{comment_content} WHERE BINARY comment_id = #{comment_id}")
    void setCommentContent(@Param("comment_id") String comment_id, @Param("comment_content") String comment_content);

    @Update("UPDATE table_comment SET comment_process = #{comment.comment_process} WHERE BINARY comment_id = #{comment.comment_id}")
    void setCommentProcessById(@Param("comment_id") String comment_id, @Param("comment_process") Integer comment_process);

    @Update("UPDATE table_comment SET comment_process = #{comment.comment_process} WHERE BINARY writer_id = #{comment.writer_id}")
    void setCommentProcessByWriterId(@Param("writer_id") String writer_id, @Param("comment_process") Integer comment_process);

    @Select("SELECT * FROM table_comment WHERE BINARY comment_id = #{comment.comment_id}")
    Comment getCommentById(@Param("comment_id") String comment_id);

    @Select("<script>"
                + "SELECT * FROM table_comment"
                + "<where>"
                    + "<if test='comment.writer_id != null'>"
                        + "AND BINARY writer_id = #{comment.writer_id} "
                    + "</if>"
                    + "<if test='comment.article_id != null'>"
                        + "AND BINARY article_id = #{comment.article_id} "
                    + "</if>"
                    + "<if test='comment.comment_process != 0'>"
                        + "AND comment_process = #{comment.comment_process} "
                    + "</if>"
                + "</where>"
                + "ORDER BY comment_public_time "
                + "<if test='comment.comment_public_time != null'>"
                +    " DESC "
                + "</if>"
                + "LIMIT #{startTag}, #{number}"
            + "</script>")
    List<Comment> getComments(@Param("comment") Comment comment, @Param("startTag") Integer startTag, @Param("number") Integer number);

    @Select("<script>"
            + "SELECT * FROM table_comment"
                + "<where>"
                    + "<if test='comment.writer_id != null'>"
                        + "AND BINARY writer_id = #{comment.writer_id} "
                    + "</if>"
                    + "<if test='comment.article_id != null'>"
                        + "AND BINARY article_id = #{comment.article_id} "
                    + "</if>"
                    + "<if test='comment.comment_process != 0'>"
                        + "AND comment_process = #{comment.comment_process} "
                    + "</if>"
                + "</where>"
            + "</script>")
    Long getCommentCount(@Param("comment") Comment comment);
}
