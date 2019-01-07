package com.windhunter.hunterhome.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private static final byte[] secret = "mygirlfriendisxujingilovehervery".getBytes();

    //生成一个token
    private static String creatToken(Map<String,Object> payloadMap) throws JOSEException {

        //3.先建立一个头部Header,用hs256算法加密
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(secret);

        //签名
        jwsObject.sign(jwsSigner);

        //生成token
        return jwsObject.serialize();
    }

    public static String getToken(String user_id, int user_power) {
        //获取生成token

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("user_power", user_power);
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime()+6);
        try {
            String token = TokenUtils.creatToken(map);
            System.out.println("token="+token);
            return token;
        } catch (JOSEException e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
        return null;

    }

    public static Map<String,Object> valid(String token) throws ParseException, JOSEException {

//        解析token

        JWSObject jwsObject = JWSObject.parse(token);

        //获取到载荷
        Payload payload=jwsObject.getPayload();

        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);

        Map<String, Object> resultMap = new HashMap<>();
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            resultMap.put("Result", 0);
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put("data", jsonObject);

            //判断token是否过期
            if (jsonObject.containsKey("exp")) {
                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
                Long nowTime = new Date().getTime();
                //判断是否过期
                if (nowTime > expTime) {
                    //已经过期
                    resultMap.clear();
                    resultMap.put("Result", 1);

                }
            }
        }else {
            resultMap.put("Result", 2);
        }
        return resultMap;
    }
}
