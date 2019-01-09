package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.*;
import com.windhunter.hunterhome.repository.PostRepository;
import com.windhunter.hunterhome.repository.UserRepository;
import com.windhunter.hunterhome.service.CommentService;
import com.windhunter.hunterhome.service.LikeTagService;
import com.windhunter.hunterhome.service.PostService;
import com.windhunter.hunterhome.service.UserService;
import com.windhunter.hunterhome.validation.ManualValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeTagService likeTagService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean addPost(Post post) {
        Timestamp ts = new Timestamp(new Date().getTime());
        post.setPost_public_time(ts);
        post.setPost_process(1);
        post.setPost_id(UUID.randomUUID().toString().replace("-", ""));
        if(post.getPost_photo() == null) {
            post.setPost_photo("Default.png");
        }
        if(post.getPost_content() == null) {
            post.setPost_content("Default.txt");
        }
        postRepository.addPost(post);
        return new ResultBean(666, "SUCCESS", null);
    }

    @Override
    public ResultBean setPost(Post post) {
        if(post.getPost_photo() == null) {
            post.setPost_photo("Default.png");
        }
        if(post.getPost_content() == null) {
            post.setPost_content("Default.txt");
        }
        postRepository.setPost(post);
        return new ResultBean(666, "SUCCESS", null);
    }

    @Override
    public ResultBean setPostProcessById(String post_id, int post_process) {
        postRepository.setPostProcessById(post_id,post_process);
        return new ResultBean(666, "SUCCESS", null);
    }

    @Override
    public ResultBean setPostProcessByWriterId(String writer_id, int post_process) {
        postRepository.setPostProcessByWriterId(writer_id, post_process);
        return new ResultBean(666, "SUCCESS", null);
    }

    @Override
    public ResultBean getPostById(String post_id) {
        return new ResultBean(666, "SUCCESS", postRepository.getPostById(post_id));
    }

    @Override
    public ResultBean getEnhancePostById(String post_id) {
        //获得一个post并加强
        EnhancePost enhancePost = postEnhance(postRepository.getPostById(post_id));
        ResultBean bean = new ResultBean(666,"SUCCESS",enhancePost);
        return bean;
    }

    @Override
    public ResultBean getPosts(Post post, int current_page, int page_number) {
        //校验
        postInfoVerficate(post);
        //获得总数量
        Integer postCount = postRepository.getPostCount(post);
        //算出总页数
        int total_page = (int) Math.ceil(postCount/page_number);
        //封装一个page对象,获取post列表
        List<Post> posts = postRepository.getPosts(post, current_page * page_number, page_number);
        //把结果集封装到page中
        Page page = new Page(current_page+1,total_page,posts,page_number);
        ResultBean resultBean = new ResultBean(666,"SUCCESS",page);
        return resultBean;
    }

    @Override
    public ResultBean getEnhancePosts(Post post, int current_page, int page_number) {
        //调用其他业务方法获取普通的post列表
        List<Post> postList = (List<Post>) getPosts(post, current_page, page_number).getBean();
        List<EnhancePost> enhancePostList = new ArrayList<>(postList.size());
        //循环对列表中的post进行加强操作
        for(int i = 0; i < postList.size(); i++) {
            enhancePostList.add(postEnhance(postList.get(i)));
        }
        ResultBean resultBean = new ResultBean(666,"SUCCESS",enhancePostList);
        return resultBean;
    }

    @Override
    public ResultBean getPostCount(Post post) {
        //校验
        postInfoVerficate(post);
        Integer postcount = postRepository.getPostCount(post);
        ResultBean resultBean = new ResultBean(666,"SUCCESS",post);
        return resultBean;
    }

    //加强post
    private EnhancePost postEnhance(Post post) {
        //先把post封装到enhancepost
        EnhancePost enhancePost = new EnhancePost(post);
        //设置昵称
        enhancePost.setNick_name((String) userService.getUserNickname(enhancePost.getWriter_id()).getBean());
        //设置头像
        enhancePost.setUser_photo((String) userService.getUserPhoto(enhancePost.getWriter_id()).getBean());
        //设置评论数量
        Comment comment = new Comment();
        comment.setArticle_id(enhancePost.getPost_id());
        enhancePost.setComment_count((Long) commentService.getCommentCount(comment).getBean());
        //设置点赞数量
        LikeTag likeTag = new LikeTag();
        likeTag.setArticle_id(enhancePost.getPost_id());
        enhancePost.setLiketag_count((Long) likeTagService.getLikeTagCount(likeTag).getBean());
        return enhancePost;
    }

    //数据校验
    private static void postInfoVerficate(Post post) {
        //校验writerid
        String writer_id = ManualValidation.UUIDtransform(post.getWriter_id(),null);
        post.setWriter_id(writer_id);
        //校验流程码
        int process = ManualValidation.processTransform(post.getPost_process(),'0');
        post.setPost_process(process);
        //校验类型码
        int type_id = ManualValidation.typeIdTransform(post.getType_id(),0);
        post.setType_id(type_id);
        //校验搜索关键字
        String title = ManualValidation.sreachTransform(post.getPost_title(),null);
        post.setPost_title(title);
    }
}
