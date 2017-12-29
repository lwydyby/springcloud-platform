package com.gameley.utils;

import com.gameley.bean.JwtInfo;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lw on 2017/12/29.
 * jwt token生成类
 *
 */
@SuppressWarnings("ALL")
public class JwtHelper {

    @Autowired
    private JwtProperties jwtProperties;

    private JwtInfo jwtInfo;

    public JwtInfo getJwtInfo() {
        return jwtInfo;
    }

    public void setJwtInfo(JwtInfo jwtInfo) {
        this.jwtInfo = jwtInfo;
    }
    //在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
    //在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效

    public  Claims parseJWT(String jsonWebToken,String base64Security){
        Claims claims=null;
        try{
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        }catch (SignatureException e){
            throw new RuntimeException("秘钥不正确");
        }catch (ExpiredJwtException e){
            JwtHelper jwt=new JwtHelper();
            Date expirdate=jwt.getExpirationDateFromToken(jsonWebToken);
            LocalTime localTime=LocalTime.now();
            Instant instant = expirdate.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            LocalTime expirTime = localDateTime.toLocalTime();
            LocalTime time=expirTime.plusMinutes(10);
            /**
             * 如果在过期时间10min内再次访问，自动刷新token
             */
            if(time.isAfter(localTime)){
                String token=createJWT(getUsernameFromToken(jsonWebToken),getUserIdFromToken(jsonWebToken),
                        jwtInfo.getClientId(),jwtInfo.getName(),jwtInfo.getExpiresSecond()*1000,jwtProperties.getSecret());
                jwtInfo.setToken(token);
            }

        }
        return claims;


    }

    public  String createJWT(Object name, Object userId
            , String audience, String issuer, long TTLMills, String base64Security){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        //生成签名密匙
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ","JWT")
                .claim("unique_name",name)
                .claim("userid",userId)
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
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return (String) getClaimFromToken(token).get("unique_name");
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public String getUserIdFromToken(String token) {
        return (String) getClaimFromToken(token).get("userid");
    }
    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt接收者
     */
    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取md5 key从token中
     */
    public String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, jwtProperties.getMd5Key());
    }

    /**
     * 获取jwt的payload部分
     */
    public  Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary((jwtProperties.getSecret())))
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 解析token是否正确,不正确会报异常<br>
     */
    public void parseToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.getSecret())).parseClaimsJws(token).getBody();
    }

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 生成token(通过用户名和签名时候用的随机数)
     */
    public String generateToken(String userName, String randomKey) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(jwtProperties.getMd5Key(), randomKey);
        return doGenerateToken(claims, userName);
    }

    /**
     * 生成token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + jwtProperties.getExpiration() * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public static void main(String[] args) {
        LocalTime localTime=LocalTime.now();
        LocalTime time=localTime.plusMinutes(10);
        System.out.println(time);
    }

}
