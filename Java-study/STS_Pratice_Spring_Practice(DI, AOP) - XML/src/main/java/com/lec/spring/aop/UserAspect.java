package com.lec.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class UserAspect {

    public void pointcut1() {
    }

    public Object aroundAdviceTimeChek(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String mathodName = joinPoint.getSignature().toShortString();

        System.out.println("[Around] " + mathodName + " 시작");

        Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("[Around] " + mathodName + " 끝");

        System.out.println("로직 종료, 경과 시간: " + (end - start) + "ms");

        return proceed;
    }

}
