package com.lec.jdbc_refactoring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Aspect  // @Aspect 어노테이션 만으로는 Bean 등록이 안됨!!!
public class DAOAspect {

    @Autowired
    @Qualifier(value = "loggerDAO")
    Logger logger;

    @Pointcut("execution(* com.lec.jdbc_refactoring.domain.*.*(..))")
    public void timePc() {
    }

    @Around("timePc()")
    public Object controllerBeforeAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        long start = System.currentTimeMillis();

        String message = proceedingJoinPoint.getSignature().toLongString();

        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end = System.currentTimeMillis();

        logger.info("현재진입: {}, DB경과시간: {}ms", message, (end - start));

        return proceed;
    }
}
