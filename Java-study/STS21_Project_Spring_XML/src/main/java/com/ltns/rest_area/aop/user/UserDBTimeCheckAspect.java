package com.ltns.rest_area.aop.user;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public class UserDBTimeCheckAspect {

    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {

        long start = System.currentTimeMillis();

        Object proceed = null;

        String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            // 핵심 기능 실행
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end = System.currentTimeMillis();

        log.info("{} DB동작시간 체크 : {}ms", methodName, end - start);

        return proceed;
    }
}
