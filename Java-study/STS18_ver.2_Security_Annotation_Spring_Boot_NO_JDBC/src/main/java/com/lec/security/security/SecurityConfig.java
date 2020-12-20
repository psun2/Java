package com.lec.security.security;

import com.lec.security.handler.DeniedHandler;
import com.lec.security.handler.FailuerHandler;
import com.lec.security.handler.PasswordEncoding;
import com.lec.security.handler.SuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

@Configuration
@EnableWebSecurity // springSecurityFilterChain 자동 연결
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin00")
                .password("{noop}1234") // {noop} : 비밀번호 암호화를 하지 않습니다.
                .roles("ADMIN") // ROLE_ 을 spring boot 가 붙여 줍니다. 만약 ROLE_ 을적을시 에러가 발생합니다.
                .and()
                .withUser("member").password("{noop}1234").roles("MEMBER");
                // .passwordEncoder(new PasswordEncoding()) Inmemory 방식이므로 필요 없음 ({noop} 로 인하여 비밀번호 오류발생)

        // super.configure(auth); // 부모에게 설정을 넘기면 이 메소드의 설정이 다른 무엇인가로 덮어 씌워저 버립니다.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**", "/api/**", "/")
                .permitAll()
                .antMatchers("/admin")
                .hasRole("ADMIN")
                .antMatchers("/member")
                .hasRole("MEMBER")
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .successHandler(new SuccessHandler())
                .failureHandler(new FailuerHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new DeniedHandler()) // 로그인한 사용자의 접근 권한 에러 (즉: member가 어드민에 접근 하려 할때)
                .and()
                .httpBasic();

        // super.configure(http);
    }


}
