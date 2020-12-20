package com.ltns.rest_area.config.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    // 로그인 성공시 취할 해동 handler
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("로그인 성공");

        String contextPath = request.getContextPath();
        StringBuffer path = new StringBuffer();
        path.append(contextPath);
        // path.append("/auth/user/loginfail");

        // 애들을 위한 세션(나는 필요없음)
        String name = authentication.getName();
        HttpSession session = request.getSession();
        session.setAttribute("user", name);
        session.setAttribute("messageType", "성공");
        session.setAttribute("messageContent", "로그인에 성공 하였습니다.");

        // login 성공시 메인페이지로 넘어가기
         response.sendRedirect(path.toString());
    }
}
