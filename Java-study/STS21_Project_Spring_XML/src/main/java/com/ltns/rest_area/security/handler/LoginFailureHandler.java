package com.ltns.rest_area.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    // 로그인 실패시 취할 해동 handler

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        exception.printStackTrace();
        log.error("에러 메시지 : {}", exception.getMessage());

        String contextPath = request.getContextPath();
        StringBuffer path = new StringBuffer();
        path.append(contextPath);
        path.append("/auth/user/login");

        HttpSession session = request.getSession();
        session.setAttribute("messageType", "error");
        session.setAttribute("messageContent", "로그인 정보가 일치 하지 않습니다.");

        response.sendRedirect(path.toString());
    }
}
