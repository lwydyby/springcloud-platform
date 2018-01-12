package com.gameley.filter;

import com.alibaba.fastjson.JSON;
import com.gameley.bean.Audience;
import com.gameley.bean.JwtInfo;

import com.gameley.common.constant.RestCodeConstants;
import com.gameley.common.msg.auth.TokenErrorResponse;

import com.gameley.feign.ElementService;
import com.gameley.utils.JwtHelper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@EnableConfigurationProperties(Audience.class)
@Slf4j
@Component
public class loginfiler extends ZuulFilter {

    @Autowired
    ElementService iUserService;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
    @Value("${zuul.prefix}")
    private String zuulPrefix;
    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Autowired
    private  Audience audience;
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requesturi=request.getRequestURI().substring(zuulPrefix.length());
        String token=request.getHeader("token");
        String client=request.getHeader("client");
        //不进行拦截的地址
        if(isStartWith(requesturi)){
            return null;
        }
        /**
         * 未登录，踢出
         */
        if(token==null||client==null){
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(RestCodeConstants.TOKEN_ERROR_CODE);// 返回错误码
            ctx.setResponseBody("fail token");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }

        try {
            JwtInfo jwtInfo=new JwtInfo();
            jwtInfo.setToken(token);
            jwtInfo.setClientId(audience.getClientId());
            jwtInfo.setExpiresSecond(audience.getExpiresSecond());
            jwtInfo.setName(audience.getName());
            JwtHelper jwtHelper=new JwtHelper();
            jwtHelper.setJwtInfo(jwtInfo);
            Claims claims = jwtHelper.parseJWT(token, audience.getBase64Secret());
            Claims clientClaims = jwtHelper.parseJWT(client, audience.getClient64Secret());


            if(claims==null||clientClaims==null){
                /**
                 * 暂不使用后台自动刷新，改为前端页面定时自动拉取刷新
                 */
                setFailedRequest("token过期", RestCodeConstants.TOKEN_ERROR_CODE);
                return null;
//                String refreshToken=jwtInfo.getToken();
//                ctx.addZuulRequestHeader("token",refreshToken);
//                ctx.addZuulResponseHeader("token",refreshToken);
            }else {

                ctx.addZuulRequestHeader("token",token);
            }


        } catch (Exception e) {
            setFailedRequest(JSON.toJSONString(new TokenErrorResponse(e.getMessage())), RestCodeConstants.TOKEN_ERROR_CODE);
            return null;
        }


        return null;
    }


    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {

        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }
}
