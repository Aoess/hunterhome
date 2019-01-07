package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.Post;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.repository.PostRepository;

public interface PostService{

    ResultBean addPost(Post post);

    ResultBean setPost(Post post);

    ResultBean setPostProcessById(String post_id, int post_process);

    ResultBean setPostProcessByWriterId(String writer_id, int post_process);

    ResultBean getPostById(String post_id);

    ResultBean getEnhancePostById(String post_id);

    ResultBean getPosts(Post post);

    ResultBean getEnhancePosts(Post post);

    ResultBean getPostCount(Post post);
}
