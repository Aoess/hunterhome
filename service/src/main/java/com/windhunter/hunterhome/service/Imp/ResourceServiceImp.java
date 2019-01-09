package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.service.ResourceService;
import com.windhunter.hunterhome.utils.PhoneCodeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class ResourceServiceImp implements ResourceService {

    @Override
    public ResultBean getPhoneCode(String user_phone, HttpServletRequest request) {
        PhoneCodeUtils.SendPhoneCode(user_phone,request);
        ResultBean resultBean = new ResultBean(666,"SUCCESS");
        return resultBean;
    }
}
