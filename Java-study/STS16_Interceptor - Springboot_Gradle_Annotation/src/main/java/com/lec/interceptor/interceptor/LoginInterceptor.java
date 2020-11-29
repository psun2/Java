package com.lec.interceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 이 스프링 버전에서는 extends HandlerInterceptorAdapter 는 더이상 사용이 안되고,
    // implements HandlerInterceptor 인터페이스를 사용 할 수 있도록 합니다.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("[preHandle] : 컨트롤러 (핸들러) 실행전");

        HttpSession session = request.getSession();

        // 로그인이 되어 있지 않다면...
        if (session.getAttribute("userId") == null) {
            // 직전 요청 url 을 세션에 기록
            String prevUrl = request.getRequestURL().toString() + "?" + request.getQueryString();

            session.setAttribute("prevUrl", prevUrl);
            log.info("prevUrl: {}", prevUrl);
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false; // ★ 더이상 진행하지 않도록 false 를 리턴
        }

        return true; // ★ 로그인이 되었다면 컨트롤러로 핸들러 진행!
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("[postHandle] : 컨트롤러 실행후, 뷰 직전");
        // super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("[afterCompletion] : 뷰 실행후 (response 후)");
        // super.afterCompletion(request, response, handler, ex);
    }

//    @Override
//    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("[afterConcurrentHandlingStarted] : 동시 처리 시작 후");
//        System.out.println("[afterConcurrentHandlingStarted] : 동시 처리 시작 후");
//        // super.afterConcurrentHandlingStarted(request, response, handler);
//    }
}
