package com.ltns.rest_area.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class UserAop {

    @Pointcut("execution(* com.ltns.rest_area.domain.*DAO.*(..))")
    public void domainPc() {
    }

    @Around("domainPc()")
    public Object dbTimeCheck(ProceedingJoinPoint proceedingJoinPoint) {

        long start = System.currentTimeMillis();

        Object proceed = null;

        String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end = System.currentTimeMillis();

        log.info("{} DB동작시간 체크 : {}ms", methodName, end - start);

        return proceed;
    }
}
