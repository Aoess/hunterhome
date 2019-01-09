package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.Department;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.Type;
import com.windhunter.hunterhome.repository.PostTypeRepository;
import com.windhunter.hunterhome.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostTypeServiceImp implements PostTypeService {

    @Autowired
    private PostTypeRepository postTypeRepository;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public ResultBean getTypeById(int type_id) {
        List<Type> postTypes = (List<Type>) redisTemplate.opsForValue().get("postTypes");
        ResultBean resultBean = null;
        Type postType = null;
        if(postTypes == null) {
            postType = postTypeRepository.getPostTypeById(type_id);
        }
        else {
            for(int i = 0; i < postTypes.size(); i++) {
                if(postTypes.get(i).getType_id() == type_id) {
                    postType = postTypes.get(i);
                }
            }
        }
        resultBean = new ResultBean(520,"SUCCESS",postType);
        return resultBean;
    }

    @Override
    public ResultBean getTypeByName(String type_name) {
        List<Type> postTypes = (List<Type>) redisTemplate.opsForValue().get("postTypes");
        ResultBean resultBean = null;
        Type postType = null;
        if(postTypes == null) {
            postType = postTypeRepository.getPostTypeByName(type_name);
        }
        else {
            for(int i = 0; i < postTypes.size(); i++) {
                if(type_name.equals(postTypes.get(i).getType_name())) {
                    postType = postTypes.get(i);
                }
            }
        }
        resultBean = new ResultBean(520,"SUCCESS",postType);
        return resultBean;
    }

    @Override
    public ResultBean getTypes() {
        //解决缓存穿透问题
        List<Type> postTypes = (List<Type>) redisTemplate.opsForValue().get("postTypes");
        ResultBean resultBean = null;
        if(postTypes == null) {
            synchronized (this) {
                if((List<Type>) redisTemplate.opsForValue().get("postTypes") == null) {
                    postTypes = postTypeRepository.getPostTypes();
                    redisTemplate.opsForValue().set("postTypes", postTypes);
                    System.out.println("mysql-postTypes");
                }
                else {
                    System.out.println("redis-postTypes");
                }
            }
        }
        else {
            System.out.println("redis-postTypes");
        }
        resultBean = new ResultBean(520,"SUCCESS",postTypes);
        return resultBean;
    }

    @Override
    public ResultBean getTypeCount() {
        List<Type> postTypes = (List<Type>) redisTemplate.opsForValue().get("postTypes");
        ResultBean resultBean = null;
        int count;
        if(postTypes == null) {
            count = postTypeRepository.getPostTypeCount();
        }
        else {
            count = postTypes.size();
        }
        resultBean = new ResultBean(520,"SUCCESS",count);
        return resultBean;
    }

    @Override
    public ResultBean addType(String type_name) {
        postTypeRepository.addPostType(type_name);
        redisTemplate.delete("postTypes");
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean setTypeById(int type_id, String type_name) {
        postTypeRepository.setPostTypeById(type_id,type_name);
        redisTemplate.delete("postTypes");
        return new ResultBean(666,"SUCCESS",null);
    }
}
