package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.Post;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.service.PostService;
import com.windhunter.hunterhome.utils.BodyReaderHttpServletRequestWrapper;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/addPost.do")
    public ResultBean addPost(@RequestBody @Validated({Post.updatePostTitle.class,Post.updatePostWriter.class,Post.updateTypeId.class}) Post post, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = postService.addPost(post);
        }
        return bean;
    }

    @RequestMapping("/setPost.do")
    public ResultBean setPost(@RequestBody @Validated({Post.getPostmessage.class,Post.updatePostTitle.class,Post.updatePostWriter.class,Post.updateTypeId.class}) Post post, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = postService.setPost(post);
        }
        return bean;
    }

    @RequestMapping("/NormalUser/PostAdmin/setPostProcessById.do")
    public ResultBean setPostProcessById(@RequestBody @Validated({Post.getPostmessage.class,Post.updateProcess.class}) Post post, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = postService.setPostProcessById(post.getPost_id(),post.getPost_process());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/PostAdmin/setPostProcessByWriterId.do")
    public ResultBean setPostProcessByWriterId(@RequestBody @Validated({Post.updatePostWriter.class,Post.updateProcess.class}) Post post, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = postService.setPostProcessByWriterId(post.getWriter_id(),post.getPost_process());
        }
        return bean;
    }

    @RequestMapping("/getPostById.do")
    public ResultBean getPostById(@RequestBody @Validated(Post.getPostmessage.class) Post post, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = postService.getPostById(post.getPost_id());
        }
        return bean;
    }

    @RequestMapping("/getEnhancePostById.do")
    public ResultBean getEnhancePostById(@RequestBody @Validated(Post.getPostmessage.class) Post post, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = postService.getEnhancePostById(post.getPost_id());
        }
        return bean;
    }

    @RequestMapping("/getPosts.do")
    public ResultBean getPosts(@RequestBody Post post, HttpServletRequest request) {
        ResultBean bean = new ResultBean(230,"Parameter error!!");
        try {
            JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
            bean = postService.getPosts(post,(int) parameterMap.get("current_page"),(int) parameterMap.get("page_number"));
        } catch (IOException e) {
            bean.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return bean;
    }

    @RequestMapping("/getEnhancePosts.do")
    public ResultBean getEnhancePosts(@RequestBody Post post, HttpServletRequest request) {
        ResultBean bean = new ResultBean(230,"Parameter error!!");
        try {
            JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
            bean = postService.getEnhancePosts(post,(int) parameterMap.get("current_page"),(int) parameterMap.get("page_number"));
        } catch (IOException e) {
            bean.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return bean;
    }

    @RequestMapping("/NormalUser/PostAdmin/getPostCount.do")
    public ResultBean getPostCount(Post post) {
        return postService.getPostCount(post);
    }
}
