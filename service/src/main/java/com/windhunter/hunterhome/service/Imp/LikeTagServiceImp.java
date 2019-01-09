package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.*;
import com.windhunter.hunterhome.repository.LikeTagRepository;
import com.windhunter.hunterhome.repository.UserRepository;
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
public class LikeTagServiceImp implements LikeTagService {
    @Autowired
    private LikeTagRepository likeTagRepository;
    @Autowired
    private UserService userService;

    @Override
    public ResultBean addLikeTag(String user_id, String article_id) {
        LikeTag likeTag = new LikeTag();
        likeTag.setArticle_id(article_id);
        likeTag.setUser_id(user_id);
        likeTag.setLiketag_id(UUID.randomUUID().toString().replace("-",""));
        likeTag.setLiketag_public_time(new Timestamp(new Date().getTime()));
        likeTagRepository.addLikeTag(likeTag);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean setLikeTag(LikeTag likeTag) {
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
        LikeTag likeTagById = likeTagRepository.getLikeTagById(liketag_id);
        return new ResultBean(666,"SUCCESS",likeTagById);
    }

    @Override
    public ResultBean getLikeTagByUidAndAid(String user_id, String article_id) {
        likeTagRepository.getLikeTagByUidAndAid(user_id,article_id);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean getLikeTags(LikeTag likeTag, int current_page,int page_number) {
        tagInfoVerficate(likeTag);
        Integer tagCount = likeTagRepository.getLikeTagCount(likeTag);
        int total_page = (int) Math.ceil(tagCount / page_number);
        List<LikeTag> likeTags =likeTagRepository.getLikeTags(likeTag,current_page * page_number, page_number);
        Page page = new Page(current_page+1, total_page,likeTags, page_number);
        ResultBean resultBean = new ResultBean(666, "SUCCESS", page);
        return resultBean;
    }

    @Override
    public ResultBean getLikeTagCount(LikeTag likeTag) {
        tagInfoVerficate(likeTag);
        Integer liketagCount = likeTagRepository.getLikeTagCount(likeTag);
        ResultBean resultBean = new ResultBean(666,"SUCCESS",liketagCount);
        return resultBean;
    }

    //这个先不写
    @Override
    public ResultBean getEnhanceLikeTags(LikeTag likeTag, int current_page,int page_number) {
        List<LikeTag> tagLists = ( List<LikeTag>)getLikeTags(likeTag,current_page,page_number).getBean();
        List<EnhanceLikeTag> enhanceTagList = new ArrayList<>(tagLists.size());
        //循环对列表中的post进行加强操作
        for (int i = 0; i < tagLists.size(); i++) {
            enhanceTagList.add(likeTagEnhance(tagLists.get(i)));
        }
        ResultBean resultBean = new ResultBean(666, "SUCCESS", enhanceTagList);
        return resultBean;
    }

    //这个先不写
    @Override
    public ResultBean getEnhanceLikeTagById(String likeTag_id) {
        EnhanceLikeTag enhanceLikeTag = likeTagEnhance(likeTagRepository.getLikeTagById(likeTag_id));
        return new ResultBean(666,"SUCCESS",enhanceLikeTag);
    }

    private EnhanceLikeTag likeTagEnhance(LikeTag likeTag) {
        EnhanceLikeTag enhanceLikeTag = new EnhanceLikeTag(likeTag);
        enhanceLikeTag.setUser_nikename((String) userService.getUserNickname(enhanceLikeTag.getUser_id()).getBean());
        enhanceLikeTag.setUser_photo((String) userService.getUserPhoto(enhanceLikeTag.getUser_id()).getBean());
        return enhanceLikeTag;
    }

    private static void tagInfoVerficate(LikeTag likeTag){
        String user_id = ManualValidation.UUIDtransform(likeTag.getUser_id(),null);
        likeTag.setUser_id(user_id);
        String article_id = ManualValidation.UUIDtransform(likeTag.getArticle_id(),null);
        likeTag.setArticle_id(article_id);
        Timestamp ts = ManualValidation.dataTransform(likeTag.getLiketag_public_time(),null);
        likeTag.setLiketag_public_time(ts);
    }
}
