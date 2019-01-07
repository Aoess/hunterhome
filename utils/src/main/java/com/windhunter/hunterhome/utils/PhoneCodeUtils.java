package com.windhunter.hunterhome.utils;

import javax.servlet.http.HttpServletRequest;

public class PhoneCodeUtils {

    public static void SendPhoneCode(String user_phone, HttpServletRequest request) {
        String code = "520111";
        request.getSession().setAttribute("PHONECODE"+user_phone.substring(7,11),code);
    }
}
