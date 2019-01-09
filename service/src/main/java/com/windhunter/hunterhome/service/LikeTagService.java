package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.LikeTag;
import com.windhunter.hunterhome.entity.ResultBean;

public interface LikeTagService {

    ResultBean addLikeTag(String user_id, String article_id);

    ResultBean setLikeTag(LikeTag likeTag);

    ResultBean deleteLikeTag(String liketag_id);

    ResultBean getLikeTagById(String liketag_id);

    ResultBean getLikeTagByUidAndAid(String user_id, String article_id);

    ResultBean getLikeTags(LikeTag likeTag, int current_page,int page_number);

    ResultBean getLikeTagCount(LikeTag likeTag);

    ResultBean getEnhanceLikeTags(LikeTag likeTag, int current_page,int page_number);

    ResultBean getEnhanceLikeTagById(String likeTag_id);

}
