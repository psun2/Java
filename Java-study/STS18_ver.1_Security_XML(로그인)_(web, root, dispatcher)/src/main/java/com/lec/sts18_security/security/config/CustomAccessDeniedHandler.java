package com.lec.sts18_security.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    // 권한이 없는데 접근 하려는 경우 핸들링 하는 security 설정 파일

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("accessDeniedException: {}", accessDeniedException.getMessage());

        log.info("Access Denied Handler");
        log.info("redirect 합니다...");
        response.sendRedirect(request.getContextPath() + "/accessError");
    }
}
