package com.gameley.common.Interceptor;

import com.gameley.common.exception.BaseException;
import com.gameley.common.util.ClientUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    @SuppressWarnings("unchecked")
    @Pointcut("execution(public * com.gameley.controller.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("请求地址 : " + request.getRequestURL().toString());
        logger.info("HTTP METHOD : " + request.getMethod());
        // 获取真实的ip地址
        logger.info("IP : " + ClientUtil.getClientIp(request));
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            Object ob= proceedingJoinPoint.proceed();
            logger.info("耗时 : " + (System.currentTimeMillis() - startTime));
            return ob;
        } catch (Exception e) {
            logger.error("error:",e);
            return new BaseException("后台操作异常",500);

        }


    }

    @AfterReturning(pointcut = "log()",returning = "object")//打印输出结果
    public void doAfterReturing(Object object){
        logger.info("response={}",object.toString());
    }
}
