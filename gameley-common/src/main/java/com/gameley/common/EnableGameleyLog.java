package com.gameley.common;


import com.gameley.common.Interceptor.HttpAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(HttpAspect.class)
@Documented
@Inherited
public @interface EnableGameleyLog {
}
