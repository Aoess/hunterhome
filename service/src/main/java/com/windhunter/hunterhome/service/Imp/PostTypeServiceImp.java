package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.repository.PostTypeRepository;
import com.windhunter.hunterhome.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostTypeServiceImp implements PostTypeService {

    @Autowired
    PostTypeRepository postTypeRepository;

    @Override
    public ResultBean getTypeById(int type_id) {
        return new ResultBean(666,"SUCCESS",postTypeRepository.getPostTypeById(type_id));
    }

    @Override
    public ResultBean getTypeByName(String type_name) {
        return new ResultBean(666,"SUCCESS",postTypeRepository.getPostTypeByName(type_name));
    }

    @Override
    public ResultBean getTypes() {
        return new ResultBean(666,"SUCCESS",postTypeRepository.getPostTypes());
    }

    @Override
    public ResultBean getTypeCount() {
        return new ResultBean(666,"SUCCESS",postTypeRepository.getPostTypeCount());
    }

    @Override
    public ResultBean addType(String type_name) {
        postTypeRepository.addPostType(type_name);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean setTypeById(int type_id, String type_name) {
        postTypeRepository.setPostTypeById(type_id,type_name);
        return new ResultBean(666,"SUCCESS",null);
    }
}
