package com.lec.sts13_jdbc.controller;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Component
@Aspect
public class BoardControllerAspect {

	@Autowired
	@Qualifier("logger")
	Logger logger;

	@Pointcut("execution(* com.lec.sts13_jdbc.*.*(..))")
	public void homeControllerPc() {};

	@Pointcut("execution(* com.lec.sts13_jdbc.controller.*.*(..))")
	public void controllerPc() {};

	@Before("homeControllerPc() || controllerPc()")
	public void controllerLocationBeforeAdvice(JoinPoint joinPoint) {

		String info = joinPoint.getSignature().toShortString();
		logger.info("현재 위치: {}", info);
	}

	@After("homeControllerPc() || controllerPc()")
	public void controllerParameterErrorBeforeAdvice(JoinPoint joinPoint) {
		

		// 현재 실행중인 메소드의 arguments를 검사
		List<Object> objects = Arrays.asList(joinPoint.getArgs());
		
		objects.forEach((obj) -> {
			String className = obj.getClass().getSimpleName();
			
			// 검사한 arguments의 이름이 BeanPropertyBindingResult
			// 즉, Errors 객체의 자식이라면
			if(className.equals("BeanPropertyBindingResult")) {
				Errors errors = (Errors) obj;
				logger.info("{}", errors);
				
				// 에러내용이 있는지 검사
				if(errors.hasErrors()) { // 있다면 에러내용 출력
					List<FieldError> errorList = errors.getFieldErrors();
					
					logger.error("field\t|\tcode");
					
					errorList.forEach((e) -> {
						logger.error("{}\t|\t{}", e.getField(), e.getCode());
					});
				return;
				} else {
					logger.info("에러 없음");
				}
			}
		});
	}

}
