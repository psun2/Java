package com.ltns.rest_area.config.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // 설정 파일임을 명시
@EnableAspectJAutoProxy // 오토프록시 설정
public class UserAopConfig {

    @Bean
    public UserAop userAop() {
        return new UserAop();
    }
}
