package com.lec.sts12_validation.controller.logger;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Aspect
public class LoggerAspect {

	@Autowired
	@Qualifier("logger")
	private Logger logger;

	@Pointcut("execution(* com.lec.sts12_validation.*.*(..))")
	public void controllerPc() {}

	@Before("controllerPc()")
	public void parameterBeforeAdvice(JoinPoint joinPoint) {
//		System.out.println("진입");
//		Object[] objs = joinPoint.getArgs();
//		for (Object object : objs) {
//			System.out.println(object.toString());
//		}
		
//		System.out.println("중간");
		
		List<Object> list = Arrays.asList(joinPoint.getArgs());
		list.forEach((obj) -> {
			
			if(obj.getClass().getSimpleName().equals("BeanPropertyBindingResult")) {
				
				Errors errors = (Errors) obj;
				if(errors.hasErrors()) 
					showError(errors);
			}
		});
//		System.out.println("끝");
		
//		진입
//		WriteDTO [uid=0, subject=null, content=null, name=null, viewcnt=0, regDate=null]
//		org.springframework.validation.BeanPropertyBindingResult: 0 errors
//		중간
//		WriteDTO [uid=0, subject=null, content=null, name=null, viewcnt=0, regDate=null]
//		org.springframework.validation.BeanPropertyBindingResult: 0 errors
//		끝
	}
	
	@After("controllerPc()")
	public void parameterAfterAdvice(JoinPoint joinPoint) {

		List<Object> list = Arrays.asList(joinPoint.getArgs());
		list.forEach((obj) -> {
			
			if(obj.getClass().getSimpleName().equals("BeanPropertyBindingResult")) {
				
				Errors errors = (Errors) obj;
				if(errors.hasErrors()) 
					showError(errors);
			}
		});

	}
	
//	@After("controllerPc()")
//	public void controllerAfterAdvice() {
////		for (int i = 0; i < new Throwable().getStackTrace().length; i++) {
////			System.out.println("[" + i + "] " + new Throwable().getStackTrace()[i].getClassName());
////		}
//		
//		long start = System.currentTimeMillis();
//		long end = System.currentTimeMillis();
//		String className = new Throwable().getStackTrace()[14].getClassName(); 
//		// .substring(0, new Throwable().getStackTrace()[14].getClassName().indexOf("$")); // 그때그때 다른가봄
//		// StringIndexOutOfBoundsException 이 나옵니다.
//		String methodName = new Throwable().getStackTrace()[14].getMethodName();
//		logger.info("{} {}() 실행", className, methodName);
//		logger.info("경과시간: {}", (end - start));
//	}
	

	@Around("controllerPc()")
	public Object controllerAroundAdvice(ProceedingJoinPoint joinPoint) {

		long start = System.currentTimeMillis();

		Object obj = null;

		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		String methodName = joinPoint.getSignature().toShortString();
		logger.info("{} 실행, 경과시간: {}ms", methodName, (end - start));
		 return obj;
	}
	
	public void showError(Errors Errors) {
		if(Errors.hasErrors()) {
			
			logger.error("에러 개수: {}", Errors.getErrorCount());
			logger.error("\t[field]|\t[code]");
			
			List<FieldError> errorList = Errors.getFieldErrors();
			
			errorList.forEach((e) -> {
				logger.error("\t{}|\t{}", e.getField(), e.getCode());
			});
		} else {
			logger.info("에러 없음");
		}
	}

}
