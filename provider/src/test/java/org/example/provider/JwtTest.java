package org.example.provider;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    public static void main(String[] args) {


        Map<String, Object> header = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("header", "fdsafdsafdsafsda");
            }
        };

        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("uid", Integer.parseInt("123"));
                put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
                put("userId", 9999);
            }
        };

        String token = JWTUtil.createToken(header,map, "999".getBytes());

        JWT jwt = JWTUtil.parseToken(token);
        boolean verify = JWTUtil.verify(token, "999".getBytes());
        int i = 1;


    }

}
