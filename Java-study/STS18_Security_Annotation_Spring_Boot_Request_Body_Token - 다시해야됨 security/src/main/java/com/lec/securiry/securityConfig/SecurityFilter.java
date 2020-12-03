package com.lec.securiry.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityFilter extends AbstractSecurityWebApplicationInitializer {

    // security Filter의 역할을 한다고 설명이 나와있엇지만,
    // 없어도 잘 동작 합니다. spring boot stater 에서 자동적으로 /* 로 필터를 잡아주는듯합니다.
    // .antMatchers("/*").permitAll() 이런 방식으로 필터를 잡아주는 편이 좋을 듯 합니다.
    // extends AbstractSecurityWebApplicationInitializer
}
