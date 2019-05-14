package com.liwj.szpd.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TokenProcessor {
    private TokenProcessor() {
    }

    private static final TokenProcessor instance = new TokenProcessor();

    public static TokenProcessor getInstance() {
        return instance;
    }

    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "3jhfksf83432jb8y832flefi9328r3hf";

    /**
     * 生成Token
     *
     * @return
     */
    public String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String sign(String username, String userId) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create().withHeader(header).withClaim("loginName", username).withClaim("userId", userId)
                .withExpiresAt(date).sign(algorithm);
    }
}
