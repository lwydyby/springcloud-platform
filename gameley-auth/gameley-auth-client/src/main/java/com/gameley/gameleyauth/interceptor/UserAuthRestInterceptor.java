package com.gameley.gameleyauth.interceptor;

import com.gameley.common.context.BaseContextHandler;
import com.gameley.common.utils.vo.UserInfo;
import com.gameley.gameleyauth.annotation.IgnoreUserToken;
import com.gameley.gameleyauth.config.UserAuthConfig;
import com.gameley.gameleyauth.utils.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 *  由于其他包和auth-server一起引用时，feign会导致报错，将client和sever分离
 * 拦截解析token，并将解析后的用户信息存入信息类中
 * Created by wwmxd on 2018/01/03.
 */
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);
    @Autowired
    UserAuthConfig userAuthConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }
        String uri=request.getRequestURI();
        if("/swagger-resources".equals(uri)||uri.contains("configuration")){
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader("token");
        JwtHelper jwt=new JwtHelper();
        UserInfo userInfo=jwt.getUserInfo(token, userAuthConfig.getSecret());
        BaseContextHandler.setName(userInfo.getName());
        BaseContextHandler.setUserID(userInfo.getId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
