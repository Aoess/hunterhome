package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.Comment;
import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.ResultBean;

public interface CommentService {

    ResultBean addComment(Comment comment);
    
    ResultBean setCommentContent(String comment_id, String comment_content);

    ResultBean setCommentProcessById(String comment_id, int Comment_process);

    ResultBean setCommentProcessByWriterId(String writer_id, int Comment_process);

    ResultBean getCommentById(String Comment_id);

    ResultBean getEnhanceCommentById(String Comment_id);

    ResultBean getComments(Comment comment, int current_page,int page_number);

    ResultBean getEnhanceComments(Comment comment, int current_page,int page_number);

    ResultBean getCommentCount(Comment comment);
}
