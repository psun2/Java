package com.lec.interceptor.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@EnableWebMvc // 반환된 설정값을 자동적으로 등록 해 줍니다.
@Configuration
@ComponentScan
public class InterceptorConfig2 { // 동작안됨 ㅠㅠ - spring boot 에서는 동작 가능

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Bean
    public InterceptorRegistry addInterceptors() {
        InterceptorRegistry interceptorRegistry = new InterceptorRegistry();
        interceptorRegistry.addInterceptor(this.loginInterceptor) // interceptor mapping, binding, 등록
                .addPathPatterns("/**"); //
        // .excludePathPatterns("/") // interceptor 예외
        // .excludePathPatterns("/user/**") //
        // .excludePathPatterns("/board/list");
        return interceptorRegistry;
    }

}
