package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.LikeTag;
import com.windhunter.hunterhome.entity.ResultBean;

public interface LikeTagService {

    ResultBean setLikeTag(String user_id, String article_id);

    ResultBean deleteLikeTag(String liketag_id);

    ResultBean getLikeTagById(String liketag_id);

    ResultBean getLikeTags(LikeTag likeTag);

    ResultBean getLikeTagCount(LikeTag likeTag);

    ResultBean getEnhanceLikeTags(LikeTag likeTag);

    ResultBean getEnhanceLikeTagById(String LikeTag_id);

}
