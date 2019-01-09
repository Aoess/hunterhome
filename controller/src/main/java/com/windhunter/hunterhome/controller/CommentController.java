package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.Comment;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.service.CommentService;
import com.windhunter.hunterhome.utils.BodyReaderHttpServletRequestWrapper;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/addComment.do")
    public ResultBean addComment(@RequestBody @Validated({Comment.updateArticleid.class, Comment.updateWriter.class}) Comment comment, BindingResult br) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        if (br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        } else {
            commentService.addComment(comment);
        }
        return bean;
    }

    @RequestMapping("/setCommentContent.do")
    public ResultBean setCommentContent(@RequestBody @Validated({Comment.getCommentmessage.class}) Comment comment, BindingResult br) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        if (br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        } else {
            commentService.setCommentContent(comment.getComment_id(), comment.getComment_content());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/PostAdmin/setCommentProcessById.do")
    public ResultBean setCommentProcessById(@RequestBody @Validated({Comment.getCommentmessage.class, Comment.updateCProcess.class}) Comment comment, BindingResult br) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        if (br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        } else {
            commentService.setCommentProcessById(comment.getComment_id(), comment.getComment_process());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/PostAdmin/setCommentProcessByWriterId.do")
    public ResultBean setCommentProcessByWriterId(@RequestBody @Validated({Comment.updateWriter.class, Comment.updateCProcess.class}) Comment comment, BindingResult br) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        if (br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        } else {
            commentService.setCommentProcessByWriterId(comment.getWriter_id(), comment.getComment_process());
        }
        return bean;
    }

    @RequestMapping("/getCommentById.do")
    public ResultBean getCommentById(@RequestBody @Validated({Comment.getCommentmessage.class}) Comment comment, BindingResult br) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        if (br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        } else {
            commentService.getCommentById(comment.getComment_id());
        }
        return bean;
    }

    @RequestMapping("/getEnhanceCommentById.do")
    public ResultBean getEnhanceCommentById(@RequestBody @Validated({Comment.getCommentmessage.class}) Comment comment, BindingResult br) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        if (br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean.setMessage(br.getAllErrors().get(0).getDefaultMessage());
        } else {
            commentService.getEnhanceCommentById(comment.getComment_id());
        }
        return bean;
    }

    @RequestMapping("/getComments.do")
    public ResultBean getComments(@RequestBody Comment comment, HttpServletRequest request) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        try {
            JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
            bean = commentService.getComments(comment, (int) parameterMap.get("current_page"), (int) parameterMap.get("page_number"));
        } catch (IOException e) {
            bean.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return bean;
    }

    @RequestMapping("/getEnhanceComments.do")
    public ResultBean getEnhanceComments(@RequestBody Comment comment, HttpServletRequest request) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        try {
            JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
            bean = commentService.getEnhanceComments(comment, (int) parameterMap.get("current_page"), (int) parameterMap.get("page_number"));
        } catch (IOException e) {
            bean.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return bean;
    }

    @RequestMapping("/NormalUser/PostAdmin/getCommentCount.do")
    public ResultBean getCommentCount(@RequestBody Comment comment) {
        return commentService.getCommentCount(comment);
    }
}
