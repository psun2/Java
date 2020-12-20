package com.lec.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("로그인 성공 : {}", authentication);

        log.info("getPrincipal : {}", authentication.getPrincipal());

        UserDetails principal = (UserDetails) authentication.getPrincipal();

        // 여기서 request 를 이용한 session에 접근하여 sesison에 직접 등록하여도 되지만,
        log.info("UserDetails principal.getUsername() : {}", principal.getUsername()); // 현재 접속 중인 사용자 아이디 뽑아내기

        // 프론트 딴에서도 namespace를 이용하여 알아낼 수 있습니다.
        // xmlns:sec="http://www.thymeleaf.org/extras/spring-security"
        // <p>
        // <span sec:authentication="principal.username">demo</span>님이 로그인 상태
        // </p>

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> authList = new ArrayList<String>();
        authorities.forEach(auth -> {
            String authority = auth.getAuthority();
            log.info(authority);
            authList.add(authority);
        });

        if (authList.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/admin");
            return;
        }

        if (authList.contains("ROLE_MEMBER")) {
            response.sendRedirect("/member/member");
            return;
        }

        response.sendRedirect("/logout");
    }
}
