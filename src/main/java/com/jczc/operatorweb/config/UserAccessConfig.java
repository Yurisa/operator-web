package com.jczc.operatorweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jczc.operatorweb.interceptor.UserAccessInterceptor;
@Configuration
public class UserAccessConfig extends WebMvcConfigurerAdapter{
	@Autowired
	UserAccessInterceptor interceptor;
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(interceptor).addPathPatterns("/user/**","/charging/**","/account/**").excludePathPatterns("/user/login","/user/register","/user/search/wx/*");
		 super.addInterceptors(registry);
	}
}
