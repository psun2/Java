package com.ltns.rest_area.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SecurityDeniedHandler implements AccessDeniedHandler {
    // 권한  접근 거부시 취할 해동 handler
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        accessDeniedException.printStackTrace();
        log.error("에러 메시지: {}", accessDeniedException.getMessage());

        String contextPath = request.getContextPath();
        StringBuffer path = new StringBuffer();
        path.append(contextPath);
        path.append("/member/autherror");

        response.sendRedirect(path.toString());
    }
}
