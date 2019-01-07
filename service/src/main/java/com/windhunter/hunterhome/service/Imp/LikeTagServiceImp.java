package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.LikeTag;
import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.repository.LikeTagRepository;
import com.windhunter.hunterhome.service.LikeTagService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class LikeTagServiceImp implements LikeTagService {
    @Autowired
    LikeTagRepository likeTagRepository;
    @Override
    public ResultBean setLikeTag(String user_id, String article_id) {
        LikeTag likeTag = new LikeTag();
        likeTag.setArticle_id(article_id);
        likeTag.setUser_id(user_id);
        likeTag.setLiketag_id(UUID.randomUUID().toString().replace("-",""));
        likeTag.setLiketag_public_time(new Timestamp(new Date().getTime()));
        likeTagRepository.setLikeTag(likeTag);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean deleteLikeTag(String liketag_id) {
        likeTagRepository.deleteLikeTag(liketag_id);
        return new ResultBean(666,"SUCCESS",null);
    }
    @Override
    public ResultBean getLikeTagById(String liketag_id) {
        likeTagRepository.getLikeTagById(liketag_id);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    //信息未完封住
    public ResultBean getLikeTags(LikeTag likeTag) {
        likeTag.setLiketag_public_time(new Timestamp(new Date().getTime()));
        Page page = new Page();
        page.setEntity(likeTag);
        likeTagRepository.getLikeTags(page);
        return null;
    }

    @Override
    public ResultBean getLikeTagCount(LikeTag likeTag) {
        likeTag.setLiketag_public_time(new Timestamp(new Date().getTime()));
        //信息封装未完全
        return new ResultBean(666,"SUCCESS",likeTagRepository.getLikeTagCount(likeTag));
    }

    //这个先不写
    @Override
    public ResultBean getEnhanceLikeTags(LikeTag likeTag) {
        return null;
    }

    //这个先不写
    @Override
    public ResultBean getEnhanceLikeTagById(String LikeTag_id) {
        return null;
    }
}
