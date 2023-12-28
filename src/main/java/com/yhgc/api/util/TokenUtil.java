package com.yhgc.api.util;

import com.yhgc.api.entity.UserInfo;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenUtil {
    private static String key = "asdfsdfhjhiqehshdjasznxbczgajoisaud";
    private static SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName()); //设置JWT密钥

    public static Map createToken(UserInfo userInfo) {
        //生成token
        long currentTimeMillis = System.currentTimeMillis() + 60* 60 * 8000;//八小时有效时间;
        HashMap<String, Object> map = new HashMap<>();
        Date end = new Date(currentTimeMillis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(end);
        map.put("Canonical", UUID.randomUUID().toString());
        String token = Jwts.builder()     //创建JWT对象
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(end)
                .setClaims(map)
                .signWith(secretKey)
                .compact();//生成token
        map.put("expire_in",currentTimeMillis);
        map.put("expire_time",dateString);
        map.put("token",token);
        return map;
    }

    public static int verifyToken(String token) {
        //验证token
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return 0;
        } catch (ExpiredJwtException e) {  //过期JWT异常
            System.out.println(1);
            return 1;
        } catch (UnsupportedJwtException e) {  //不支持JST异常
            return 2;
        } catch (MalformedJwtException e) {  //格式不正确的JWT
            return 3;
        } catch (IllegalArgumentException e) { // 非法数据异常
            return 4;
        }
    }

    public static Map<String, Object> parseToken(String token) {
        Map<String, Object> body = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(secretKey)  //设置签名密钥
                .parseClaimsJws(token)
                .getBody();
        return body;
    }
}