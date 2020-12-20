package com.lec.security.config.security.handler;

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
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> list = new ArrayList<String>();
        authorities.forEach(auth -> {
            String authority = auth.getAuthority();
            list.add(authority);
        });

        log.info("auth List : {}", list);

        // 권한이 유저일시 member 페이지로
        if (list.contains("ROLE_USER")) {
            response.sendRedirect(request.getContextPath() + "/sample/member");
            return;
        }

        // 권한이 admin 일시 관리자 페이지로
        if (list.contains("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/sample/admin");
            return;
        }

        // 그 외 모든 권한은 / 루트 페이지로
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        log.info("FilterChain chain", chain.toString());
        log.info("Authentication authentication", authentication.toString());
    }
}
