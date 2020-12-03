package com.lec.securiry.security;

import javax.sql.DataSource;

import com.lec.securiry.handler.CustomDeniedHandler;
import com.lec.securiry.handler.LoginFailHandler;
import com.lec.securiry.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 출처 :
    // https://velog.io/@jayjay28/2019-09-04-1109-%EC%9E%91%EC%84%B1%EB%90%A8
    @Autowired
    DataSource dataSource;


    // <security:http> 와 대응합니다. </security:http>
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.addFilter(new DelegatingFilterProxy("springSecurityFilterChain")).antMatcher("/*");
        // 특정 url pattern 요청이 들어올때 인터셉터를 이용해 접근 제한 설정
        // http.authorizeRequests() //
        http.authorizeRequests() //
                .antMatchers("/*").permitAll() //
                .antMatchers("/sample/all").permitAll() //
                .antMatchers("/sample/member").hasRole("USER") //
                .antMatchers("/sample/admin").hasRole("ADMIN, USER");

// <!-- 접근권한 에러 발생시 동작시킬 핸들러 : 방법1: url 지정 -->
        // http.exceptionHandling().accessDeniedPage("/accessError");

        // <!-- 접근권한 에러 발생시 동작시킬 핸들러 : 방법2: handler 지정 -->
        http.exceptionHandling().accessDeniedHandler(new CustomDeniedHandler());

        // <!-- security 만약 권한이 허용 되지 않는다면 form-login 으로 설정된 페이지로 redirect 합니다. -->
        // <!-- <security:form-login /> security 가 기본 제공하는 로그인 폼 -->
        http.formLogin().loginPage("/customLogin").successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailHandler()).permitAll();
        ;

        // .formLogin() 아래를 보면,
        // .loginPage("로그인 페이지 경로")  -> 앞으로 로그인은 이 경로에서 수행하게 된다는 뜻이다.
        // .loginProcessingUrl("로그인 처리 경로") -> 로그인 form 의 action 과 일치시켜주어야 한다.
        // 여기엔 없지만 .usernameParamater, .passwordParameter 를 통해 내 폼의 아이디, 패스워드 네임과 매핑할 수 있는데
        // 나는 폼 자체를 디폴트 값인 username, password로 맞추어주었기 때문에 필요가 없었다.

        // <!-- 로그아웃 -->
        // <!-- invalidate-session="true" : 모든 세션 정보를 삭제 -->
        http.logout().logoutUrl("/customLogout").invalidateHttpSession(true).permitAll();
        ;

        super.configure(http);
    }

    // 3. 로그인 입증
//    로그인을 위해서는 SecurityConfig 클래스에 AuthenticationManagerBuilder를 주입해서 인증에 대한 처리를 해야 합니다.
//    AuthenticationManagerBuilder는 인증에 대한 다양한 설정을 생성할 수 있습니다. 예를 들어, 메모리상 정보를 이용하거나, JDBC, LDAP등의 정보를 이용해서 인증 처리가 가능합니다.
//    다음 예제는 메모리를 이용하는 예제입니다.
//    참고로 AuthenticationManagerBuilder는 인증 매니저를 생성하는 빌더입니다.
//    위의 SecurityConfig.java 클래스에 아래 메소드를 추가합니다.
    // <security:authentication-manager> 와 대응 합니다.
    // </security:authentication-manager>
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//          <security:jdbc-user-service data-source-ref="dataSource"
//                 users-by-username-query=
//                "SELECT userid username, userpw password, enabled FROM tbl_member WHERE userid = ?"
//                 authorities-by-username-query=
//                "SELECT userid username, auth authority FROM tbl_member_auth WHERE userid = ?"
//                 />

        final String userByUsernameQuert = "SELECT mid, memberId as username, memberPassword as password, springSecurityEnable as enabled"
                + " FROM member" + " WHERE memberId = ? ";

        final String authoritiesByUsernameQuery = "SELECT memberId as username, auth as authority"
                + " FROM authentication" + " WHERE memberId = ? ";
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(userByUsernameQuert)
                .authoritiesByUsernameQuery(authoritiesByUsernameQuery).passwordEncoder(new BCryptPasswordEncoder());

        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN, MEMBER").and()
                .withUser("member").password("{noop}member").roles("MEMBER");

        super.configure(auth);
    }
}
