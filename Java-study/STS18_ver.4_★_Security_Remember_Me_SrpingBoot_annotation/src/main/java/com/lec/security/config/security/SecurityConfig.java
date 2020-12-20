package com.lec.security.config.security;

import com.lec.security.config.security.handler.CustomLoginFailureHandler;
import com.lec.security.config.security.handler.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService") // userDetails Service 빈
    private UserDetailsService userDetailsService;

    @Autowired // 로그인 성공 핸들러 빈
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired // 로그인 실패 핸들러 빈
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired // token 관련 빈
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired // 커스텀없이 remember-me 를 사용하기위한 주입 빈
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 기본적인 테이블을 생성하여 토큰 값을 관리 하는 tokenRepository 입니다.
    private PersistentTokenRepository getJdbcTokenRepositoryImpl() {
        // <security:remember-me
        //     data-source-ref="dataSource"/>
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());

        // super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // .exceptionHandling()
                // .accessDeniedHandler(new DeniedHandler()) // 로그인한 사용자의 접근 권한 에러 (즉: member가 어드민에 접근 하려 할때)
                .authorizeRequests()// -- url 맵핑
                .antMatchers("/login", "logout")
                .permitAll()
                .antMatchers("/member/**")
                .hasRole("MEMBER")
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .and()
                .formLogin() // -- 로그인
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()// -- 로그아웃
                .invalidateHttpSession(true) // 로그아웃시 모든 세션 파괴
                .deleteCookies("JSESSIONID", "remember-me") // 로그아웃시 삭제될 쿠키
                .and()
                .rememberMe()// -- 리맴버 미
                .key("uniqueAndSecret") // key="uniqueAndSecret" : 리맴버미 인증을 위해 발행되는 토큰을 구별하는 키 이름을 지정
                .tokenValiditySeconds(86400) // 로그인이 지속 될 시간
                // <!-- 1,209,600 : 60 * 60 * 24 * 14 = 1,209,600(2주) : 아무런 설정이 없다면 default 2주 입니다. -->
                // <!-- 86400 : 60 * 60 * 24  = 86,400(하루) -->
                .userDetailsService(userDetailsService)
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .rememberMeParameter("rememberMe")
                // .rememberMeCookieName("My-remember-cookie") // 쿠키로 저장될 이름을 지정
                // .useSecureCookie(true) // https 요청만 쿠키사용
                .tokenRepository(persistentTokenRepository); // token repository 를 적용시 레포지토리 빈
                // .tokenRepository(getJdbcTokenRepositoryImpl()); // <security:remember-me data-source-ref="dataSource"/> 와 동일 한 역할을 합니다.

        // super.configure(http);
    }
}
