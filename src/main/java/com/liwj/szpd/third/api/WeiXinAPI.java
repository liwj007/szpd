package com.liwj.szpd.third.api;

import com.alibaba.fastjson.JSONObject;
import com.liwj.szpd.utils.HttpClient4;

import java.util.HashMap;
import java.util.Map;

public class WeiXinAPI {
    private static String BASE_URL = "https://api.weixin.qq.com";
    private static String APPID = "wxec555b29cd7996e6";
    private static String SECRET = "cceae16c4be681e5ef19d3273c9225a5";


    public static Map<String, String> getSessionFromCode(String code) {
        String url = BASE_URL + "/sns/jscode2session";
        url += "?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String result = HttpClient4.doGet(url);
        Map<String, Object> itemMap = JSONObject.parseObject(result, Map.class);
        if (!itemMap.containsKey("errcode")) {
            Map<String, String> res = new HashMap();
            res.put("openid", itemMap.get("openid").toString());
            res.put("session_key", itemMap.get("session_key").toString());
            return res;
        } else {
            return null;
        }
    }
}
