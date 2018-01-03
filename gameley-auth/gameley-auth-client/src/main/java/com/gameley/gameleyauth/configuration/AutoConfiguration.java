package com.gameley.gameleyauth.configuration;



import com.gameley.gameleyauth.config.UserAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RemoteApplicationEventScan 需要被spring cloud 内置的BusJacksonMessageConverter转换器扫描到才可以实现转换，在App启动类中增加注解
 *
 * Created by ace on 2017/9/15.
 */
@Configuration
public class AutoConfiguration {

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }
}
