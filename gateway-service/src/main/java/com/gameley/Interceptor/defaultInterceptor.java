package com.gameley.Interceptor;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 拦截器：记录用户操作日志（未使用）
 * @author WWMXD
 */
//@Aspect
//@Component
@Slf4j
public class defaultInterceptor {
    /**
     * 定义拦截规则：拦截包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut(value = "execution(* com.gameley.feign.*) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut(){}

    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
    public void Interceptor(ProceedingJoinPoint pjp){

    }

}
