//package com.gameley.generator.config;
//
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import com.alibaba.druid.pool.DruidDataSource;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// *
// * 配置数据库
// * @author wwmxd
// * @version 1.2.0
// */
//@Slf4j
//@Configuration
//@RefreshScope
//public class DruidConfiguration {
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//
//
//
//    @Bean     //声明其为Bean实例
//    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
//    @RefreshScope
//    public DataSource dataSource(){
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(dbUrl);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        return datasource;
//    }
//}