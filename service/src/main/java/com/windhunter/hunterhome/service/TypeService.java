package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.ResultBean;

public interface TypeService {

    ResultBean getTypeById(int type_id);

    ResultBean getTypeByName(String type_name);

    ResultBean getTypes();

    ResultBean getTypeCount();

    ResultBean addType(String type_name);

    ResultBean setTypeById(int type_id,String type_name);


}
