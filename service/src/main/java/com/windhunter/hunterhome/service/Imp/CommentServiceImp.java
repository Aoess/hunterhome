package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.Comment;
import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.repository.CommentRepository;
import com.windhunter.hunterhome.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class CommentServiceImp implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public ResultBean addComment(Comment comment) {
        comment.setArticle_id(UUID.randomUUID().toString().replace("-",""));
        comment.setPublic_time(new Timestamp(new Date().getTime()));
        commentRepository.addComment(comment);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean setComment(Comment comment) {
        commentRepository.setComment(comment);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean setCommentProcessById(String comment_id, int comment_process) {
        commentRepository.setCommentProcessById(comment_id,comment_process);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean setCommentProcessByWriterId(String writer_id, int comment_process) {
        commentRepository.setCommentProcessByWriterId(writer_id,comment_process);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean getCommentById(String comment_id) {
        return new ResultBean(666,"SUCCESS",commentRepository.getCommentById(comment_id));
    }

    @Override
    public ResultBean getEnhanceCommentById(String comment_id) {
        return new ResultBean(666,"SUCCESS",commentRepository.getEnhanceCommentById(comment_id));
    }

    @Override
    public ResultBean getComments(Comment comment) {
        Page page = new Page();
        return new ResultBean(666,"SUCCESS",commentRepository.getComments(page));
    }

    @Override
    public ResultBean getEnhanceComments(Comment comment) {
        Page page = new Page();
        return new ResultBean(666,"SUCCESS",commentRepository.getEnhanceComments(page));
    }

    @Override
    public ResultBean getCommentCount(Comment comment) {
        return new ResultBean(666,"SUCCESS",commentRepository.getCommentCount(comment));
    }
}
