package com.ltns.rest_area.config.security;

import com.ltns.rest_area.config.security.handler.LoginFailureHandler;
import com.ltns.rest_area.config.security.handler.LoginSuccessHandler;
import com.ltns.rest_area.config.security.handler.SecurityDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private SecurityDeniedHandler securityDeniedHandler;

    private static final String usersByUsernameQuery = "SELECT username, password, enabled FROM userView WHERE username = ?";
    private static final String authoritiesByUsernameQuery = "SELECT username, authority FROM userView WHERE username = ?";

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder())
                .usersByUsernameQuery(usersByUsernameQuery)
                .authoritiesByUsernameQuery(authoritiesByUsernameQuery);
        // super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // 개발단계에서 토큰 비활성화
                .authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/member/member")
                .hasAnyRole("ADMIN", "MEMBER")
                .and()
                .formLogin()
                .loginPage("/auth/user/login")
                .loginProcessingUrl("/loginProcess")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true) // 세션파괴
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(securityDeniedHandler)
                .and()
                .httpBasic();

        // super.configure(http);
    }
}
