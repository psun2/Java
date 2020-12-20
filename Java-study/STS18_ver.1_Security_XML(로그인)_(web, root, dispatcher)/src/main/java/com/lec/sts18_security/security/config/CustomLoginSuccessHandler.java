package com.lec.sts18_security.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    // 로그인 성공시 처리하는 security 설정 파일

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Authentication : {}", authentication);

        // Authentication 객체를 이용해서 사용자가 가진 모든 권한을 문자열로 확인 가능
        List<String> roleNames = new ArrayList<String>();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        authorities.forEach(authority -> {
            // String authority1 = authority.getAuthority();
            // roleNames.add(authority1);
            roleNames.add(authority.getAuthority());
        });

        log.info("ROLES NAMES : {}, roleNames size: {}", roleNames, roleNames.size());

        // 여기서 놀라웠던 점은 List 에도 contains 가 먹히는걸 볼 수 있습니다.
        // contains() 해당 아규먼트가 포함 되나요 ?

        // 로그인한 사용자가 ROLE_ADMIN 권한을 가졌으면 /sample/admin 으로 이동
        if (roleNames.contains("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/sample/admin");
            return;
        }

        // ROLE_MEMBER 권한을 가졌으면 /sample/member 로 이동
        if (roleNames.contains("ROLE_MEMBER")) {
            response.sendRedirect(request.getContextPath() + "/sample/member");
            return;
        }

        // 둘다 아니면 / 로 이동
        response.sendRedirect("/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        log.info("Authentication : {}", authentication);
        log.info("FilterChain : {}", chain);
    }
}
