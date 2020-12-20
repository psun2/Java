package com.lec.security.security;

import com.lec.security.detailsevise.CustomUserDetailService;
import com.lec.security.domain.AuthenticationDAO;
import com.lec.security.domain.UserDAO;
import com.lec.security.handler.DeniedHandler;
import com.lec.security.handler.FailuerHandler;
import com.lec.security.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // springSecurityFilterChain 자동 연결
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthenticationDAO authenticationDAO;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // userDetailsService 방식
        auth.userDetailsService(new CustomUserDetailService(userDAO, authenticationDAO));

        // JDBC 방식
//        auth.jdbcAuthentication()
//                .dataSource(dataSource) // 데이터베이스 연결
//                .passwordEncoder(bCryptPasswordEncoder()) // 패스워드 인코더 설정
//                // user 테이블 접근 쿼리
//                .usersByUsernameQuery("SELECT username, password, enabled FROM userMember WHERE username = ?")
//                // authority 테이블 접근 쿼리
//                .authoritiesByUsernameQuery("SELECT username, authority FROM auth WHERE username = ?");
//        // 유저와 권한이 1 : 1 인 테이블에서 사용
//        // 즉 한 테이블에 유저의 이름과 권한 목록이 들어 있습니다.
//        // .groupAuthoritiesByUsername()


        // inMemoryAuthentication 방식
//        auth.inMemoryAuthentication()
//                .withUser("admin00")
//                .password("{noop}1234") // {noop} : 비밀번호 암호화를 하지 않습니다.
//                .roles("ADMIN") // ROLE_ 을 spring boot 가 붙여 줍니다. 만약 ROLE_ 을적을시 에러가 발생합니다.
//                .and()
//                .withUser("member").password("{noop}1234").roles("MEMBER");
//        // .passwordEncoder(new PasswordEncoding()) Inmemory 방식이므로 필요 없음 ({noop} 로 인하여 비밀번호 오류발생)
//
//        // super.configure(auth); // 부모에게 설정을 넘기면 이 메소드의 설정이 다른 무엇인가로 덮어 씌워저 버립니다.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**", "/api/**", "/")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/member/**")
                .hasAnyRole("ADMIN", "MEMBER")
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
