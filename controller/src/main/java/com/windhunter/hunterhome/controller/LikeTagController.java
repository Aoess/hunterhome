package com.windhunter.hunterhome.controller;

import com.windhunter.hunterhome.entity.LikeTag;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.service.LikeTagService;
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
@RequestMapping("liketag")
public class LikeTagController {

    @Autowired
    private LikeTagService likeTagService;

    @RequestMapping("/addLikeTag.do")
    public ResultBean addLikeTag(@RequestBody @Validated({LikeTag.updateArticleId.class,LikeTag.updateUseId.class}) LikeTag likeTag, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            //设置了快速校验失败,每次只返回第一个错误信息
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = likeTagService.addLikeTag(likeTag.getUser_id(),likeTag.getArticle_id());
        }
        return bean;
    }

    @RequestMapping("/PostAdmin/setLikeTag.do")
    public ResultBean setLikeTag(@RequestBody @Validated({LikeTag.updateArticleId.class,LikeTag.updateUseId.class,LikeTag.updateUseId.class}) LikeTag likeTag, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = likeTagService.setLikeTag(likeTag);
        }
        return bean;
    }

    @RequestMapping("/deleteLikeTag.do")
    public ResultBean deleteLikeTag(@RequestBody @Validated(LikeTag.getLikeTagmessage.class) LikeTag likeTag, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = likeTagService.deleteLikeTag(likeTag.getLiketag_id());
        }
        return bean;
    }

    @RequestMapping("/NormalUser/PostAdmin/getLikeTagById.do")
    public ResultBean getLikeTagById(@RequestBody @Validated(LikeTag.getLikeTagmessage.class) LikeTag likeTag, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = likeTagService.getLikeTagById(likeTag.getLiketag_id());
        }
        return bean;
    }

    @RequestMapping("/getLikeTagByUidAndPid.do")
    public ResultBean getLikeTagByUidAndPid(@RequestBody @Validated({LikeTag.updateUseId.class,LikeTag.updateArticleId.class}) LikeTag likeTag, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = likeTagService.getLikeTagByUidAndAid(likeTag.getUser_id(),likeTag.getArticle_id());
        }
        return bean;
    }

    @RequestMapping("/getLikeTags.do")
    public ResultBean getLikeTags(@RequestBody LikeTag likeTag, HttpServletRequest request) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        try {
            JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
            bean = likeTagService.getLikeTags(likeTag, (int) parameterMap.get("current_page"), (int) parameterMap.get("page_number"));
        } catch (IOException e) {
            bean.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return bean;
    }

    @RequestMapping("/getLikeTagCount.do")
    public ResultBean getLikeTagCount(@RequestBody LikeTag likeTag) {
        return likeTagService.getLikeTagCount(likeTag);
    }

    @RequestMapping("/getEnhanceLikeTags.do")
    public ResultBean getEnhanceLikeTags(@RequestBody LikeTag likeTag, HttpServletRequest request) {
        ResultBean bean = new ResultBean(230, "Parameter error!!");
        try {
            JSONObject parameterMap = (JSONObject) JSONValue.parse(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));
            bean = likeTagService.getEnhanceLikeTags(likeTag, (int) parameterMap.get("current_page"), (int) parameterMap.get("page_number"));
        } catch (IOException e) {
            bean.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return bean;
    }

    @RequestMapping("/NormalUser/PostAdmin/getEnhanceLikeTagById.do")
    public ResultBean getEnhanceLikeTagById(@RequestBody @Validated(LikeTag.getLikeTagmessage.class) LikeTag likeTag, BindingResult br) {
        ResultBean bean = null;
        if(br.getErrorCount() > 0) {
            bean = new ResultBean(230,br.getAllErrors().get(0).getDefaultMessage());
        }else {
            bean = likeTagService.getEnhanceLikeTagById(likeTag.getLiketag_id());
        }
        return bean;
    }

}
