package com.gameley.utils;

import com.gameley.common.utils.constatns.CommonConstants;
import com.gameley.common.utils.vo.UserInfo;
import com.gameley.entity.Element;
import com.gameley.entity.Menu;
import com.gameley.vo.Model;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * Created by m2m on 2017/3/27.
 *
 */


public class JwtHelper {


    public static Claims parseJWT(String jsonWebToken,String base64Security){
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String createJWT(List<Model> menus, List<Integer> element
            , String audience, String issuer, long TTLMills, String base64Security){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        //生成签名密匙
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("type","JWT")
                .claim(CommonConstants.JWT_KEY_MENU,menus)
                .claim(CommonConstants.JWT_KEY_ELEMENT,element)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm,signingKey);
        //添加token过期时间
        if (TTLMills >=0 ){
            long expMills = nowMills + TTLMills;
            Date exp = new Date(expMills);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }
    /**
     * 获取jwt的payload部分
     */
    public  Claims getClaimFromToken(String token,String secrete) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secrete))
                .parseClaimsJws(token)
                .getBody();
    }

    public UserInfo getUserInfo(String token,String secrete){

        Claims cl=getClaimFromToken(token,secrete);
        UserInfo userInfo=new UserInfo();
        userInfo.setName((String) cl.get("unique_name"));
        userInfo.setId((String) cl.get("userid"));

        return userInfo;
    }

}
