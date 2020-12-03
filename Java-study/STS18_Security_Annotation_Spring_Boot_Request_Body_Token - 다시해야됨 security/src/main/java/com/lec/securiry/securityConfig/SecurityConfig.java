package com.lec.securiry.securityConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

@EnableWebSecurity
// @Configuration // 스프링 부트는 @Configuration 설정을 안해도
// @EnableWebSecurity 있고, Bean 생성 또한 없기때문에
// 지금 이 예제에서는 굳이 사용 하지 않아도 무방합니다.
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable(); // 토큰 생성을 하지 않습니다.
        // http.formLogin().disable().authorizeRequests().antMatchers("/").permitAll().and()// rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
        // http.httpBasic().disable().authorizeRequests().antMatchers("/").permitAll();// rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
        // http.authorizeRequests().antMatchers("/testBody").permitAll();

        // http.httpBasic().disable().authorizeRequests().antMatchers("/").permitAll();

       // http.csrf().disable().authorizeRequests().antMatchers("/testBody").permitAll();
        // http.csrf().ignoringAntMatchers("/testBody").disable().authorizeRequests().antMatchers("/testBody").permitAll();
       http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll();

       http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");

//       http.formLogin().loginPage("/login").loginProcessingUrl("/login").failureHandler((request, response, exception) -> {
//           log.error(exception.getMessage());
//           response.sendRedirect(request.getContextPath() + "/admin");
//       }).successHandler((request, response, authentication) -> {
//           response.sendRedirect(request.getContextPath());
//       });

        super.configure(http);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new PasswordEncoder(

        ) {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                log.info("{} : {}", rawPassword.toString(), encodedPassword);
                return rawPassword.toString().equals(encodedPassword);
            }
        };

        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("admin").password("admin").roles("ADMIN");
        super.configure(auth);
    }
}
