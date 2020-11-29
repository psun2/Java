package com.lec.interceptor.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
// WebMvcConfgiurer의 구현으로 인해 DispatcherServlet을 간편하게 커스텀할 수 있으므로, 스프링 부트 없이 스프링 부트를 사용하는 방법이라고 불리는 것이다.

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginInterceptor) // interceptor mapping, binding, 등록
                .addPathPatterns("/**") //
                .excludePathPatterns("/") // interceptor 예외
                .excludePathPatterns("/user/**") //
                .excludePathPatterns("/board/list");
    }

}
