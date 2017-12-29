package com.gameley.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class MonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
	}

	@Configuration
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			// 不做拦截的地址
			http.authorizeRequests()
					.antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
					.permitAll();

			// 其余的任何地址都必须得到认证
			http.authorizeRequests().antMatchers("/**").authenticated();

			// 让客户端能够通过http方式进行身份认证
			http.httpBasic();
		}
	}


}
