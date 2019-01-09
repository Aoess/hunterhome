package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.*;
import com.windhunter.hunterhome.repository.CommentRepository;
import com.windhunter.hunterhome.service.CommentService;
import com.windhunter.hunterhome.service.LikeTagService;
import com.windhunter.hunterhome.service.UserService;
import com.windhunter.hunterhome.validation.ManualValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeTagService likeTagService;

    @Override
    public ResultBean addComment(Comment comment) {
        comment.setComment_id(UUID.randomUUID().toString().replace("-",""));
        comment.setComment_public_time(new Timestamp(new Date().getTime()));
        comment.setComment_process(1);
        if(comment.getComment_content() == null) {
            comment.setComment_content("default.png");
        }
        commentRepository.addComment(comment);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean setCommentContent(String comment_id, String comment_content) {
        if(comment_content == null) {
            comment_content = "default.png";
        }
        commentRepository.setCommentContent(comment_id,comment_content);
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
        EnhanceComment enhanceComment = commentEnhance(commentRepository.getCommentById(comment_id));
        return new ResultBean(666,"SUCCESS",enhanceComment);
    }

    @Override
    public ResultBean getComments(Comment comment, int current_page,int page_number) {
        commentInfoVerficate(comment);
        Integer commentCount = commentRepository.getCommentCount(comment);
        Integer total_page = (int)Math.ceil(commentCount/page_number);
        List<Comment> commentList =  (List<Comment>)commentRepository.getComments(comment,current_page * page_number ,page_number);
        return new ResultBean(666,"SUCCESS",commentList);
    }

    @Override
    public ResultBean getEnhanceComments(Comment comment, int current_page,int page_number) {
        List<Comment> commentList = (List<Comment>)getComments(comment,current_page,page_number).getBean();
        List<EnhanceComment> enhanceComments = new ArrayList<>(commentList.size());
        for(int i = 0; i<commentList.size(); i++){
            enhanceComments.add(commentEnhance(commentList.get(i)));
        }
        return new ResultBean(666,"SUCCESS",enhanceComments);
    }

    @Override
    public ResultBean getCommentCount(Comment comment) {
        commentInfoVerficate(comment);
        Integer commentCount = commentRepository.getCommentCount(comment);
        ResultBean resultBean = new ResultBean(666,"SUCCESS",commentCount);
        return resultBean;
    }

    private EnhanceComment commentEnhance(Comment comment){
        EnhanceComment enhanceComment = new EnhanceComment(comment);
        enhanceComment.setWriter_nikename((String)userService.getUserById(comment.getComment_id()).getBean());
        enhanceComment.setWriter_photo((String) userService.getUserPhoto(comment.getComment_id()).getBean());
        LikeTag likeTag = new LikeTag();
        likeTag.setArticle_id(enhanceComment.getArticle_id());
        enhanceComment.setLiketag_count((Long)likeTagService.getLikeTagCount(likeTag).getBean());
        return enhanceComment;
    }
    private static void commentInfoVerficate(Comment comment){
        String writer_id = ManualValidation.UUIDtransform(comment.getWriter_id(),null);
        comment.setWriter_id(writer_id);
        String article_id = ManualValidation.UUIDtransform(comment.getArticle_id(),null);
        comment.setArticle_id(article_id);
        int process = ManualValidation.processTransform(comment.getComment_process(),0);
        comment.setComment_process(process);
        Timestamp ts = ManualValidation.dataTransform(comment.getComment_public_time(),null);
        comment.setComment_public_time(ts);
    }
}
