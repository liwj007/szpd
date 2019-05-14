package com.liwj.szpd.utils;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PostHTTP {
    public static JsonResult sendPostRequest(String url, MultiValueMap<String, Object> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // 以表单的方式提交
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<JsonResult> response = client.exchange(url, HttpMethod.POST, requestEntity, JsonResult.class);

        return response.getBody();
    }
}
