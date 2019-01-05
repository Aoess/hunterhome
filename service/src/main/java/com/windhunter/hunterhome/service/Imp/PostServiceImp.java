package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.Post;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.service.PostService;

public class PostServiceImp implements PostService {
    @Override
    public ResultBean addPost(Post post) {
        return null;
    }

    @Override
    public ResultBean setPost(Post post) {
        return null;
    }

    @Override
    public ResultBean setPostProcessById(String post_id, int post_process) {
        return null;
    }

    @Override
    public ResultBean setPostProcessByWriterId(String writer_id, int post_process) {
        return null;
    }

    @Override
    public ResultBean getPostById(String post_id) {
        return null;
    }

    @Override
    public ResultBean getEnhancePostById(String post_id) {
        return null;
    }

    @Override
    public ResultBean getPosts() {
        return null;
    }

    @Override
    public ResultBean getEnhancePosts() {
        return null;
    }

    @Override
    public ResultBean getPostCount() {
        return null;
    }
}
