package com.aspect.user;

import com.aspect.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

@org.aspectj.lang.annotation.Aspect // @Aspect : aop 사용을 명시
public class UserAspect implements Aspect {

    @Pointcut("within(com.repository.user.*)")
    public void servicePointcut() {
    }

    @Around("servicePointcut()")
    public Object serviceAroundAdvice(ProceedingJoinPoint joinPoint) {

        System.out.println(joinPoint.getSignature().toShortString() + " 시작");

        long start = System.currentTimeMillis();

        Object proceed = null;

        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end = System.currentTimeMillis();
     
        System.out.println(joinPoint.getSignature().toShortString() + " 종료");

        System.out.println("[Around] 경과 시간 : " + (end - start) + " ms");

        return proceed;
    }

}
