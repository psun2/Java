package com.lec.jdbc_refactoring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect  // @Aspect 어노테이션 만으로는 Bean 등록이 안됨!!!
public class ControllerAspect {

    @Autowired
    @Qualifier(value = "loggerController")
    Logger logger;

    @Pointcut("execution(* com.lec.jdbc_refactoring.controller.*.*(..))")
    public void paramChkPc() {
    }

    @Before("paramChkPc()")
    public void controllerBeforeAdvice(JoinPoint joinPoint) {
        String message = joinPoint.getSignature().toShortString();
        List<Object> objects = Arrays.asList(joinPoint.getArgs());
        logger.info("현재진입: {}, params: {}", message, objects.toString());
    }

    @After("paramChkPc()")
    public void showErrorAfterAdvice(JoinPoint joinPoint) {
        List<Object> objects = Arrays.asList(joinPoint.getArgs());

        objects.forEach((o) -> {
            String paramClassName = o.getClass().getSimpleName();
            if(paramClassName.equals("BeanPropertyBindingResult")){
                Errors errors = (Errors) o;
                if(((Errors) o).hasErrors()) {
                    logger.error("에러개수: {}", errors.getErrorCount());
                List<FieldError> fieldErrors = ((Errors) o).getFieldErrors();
                fieldErrors.forEach((e) -> {
                    logger.error("에러필드: {}, 에러내용: {}", e.getField(), e.getCode());
                });
                }
                return;
            }
        });
    }

}
