package com.lec.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> authList = new ArrayList<String>();
        authorities.forEach(auth -> {
            String authority = auth.getAuthority();
            authList.add(authority);
        });

        if (authList.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
            return;
        }

        if (authList.contains("ROLE_MEMBER")) {
            response.sendRedirect("/member");
            return;
        }

        response.sendRedirect("/logout");
    }
}
