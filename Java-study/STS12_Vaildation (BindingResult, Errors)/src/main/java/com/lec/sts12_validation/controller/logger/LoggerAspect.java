package com.lec.sts12_validation.controller.logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Aspect
public class LoggerAspect {

	@Autowired
	@Qualifier("logger")
	private Logger logger;

	@Pointcut("execution(* com.lec.sts12_validation.*.*(..))")
	public void controllerPc() {}

	@After("controllerPc()")
	public void controllerAfterAdvice() {
//		for (int i = 0; i < new Throwable().getStackTrace().length; i++) {
//			System.out.println("[" + i + "] " + new Throwable().getStackTrace()[i].getClassName());
//		}

		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		String className = new Throwable().getStackTrace()[14].getClassName().substring(0, new Throwable().getStackTrace()[14].getClassName().indexOf("$"));
		String methodName = new Throwable().getStackTrace()[14].getMethodName();
		logger.info("{} {}() 실행", className, methodName);
		logger.info("경과시간: {}", (end - start));
	}

//	@Around("controllerPc()")
//	public Object controllerAroundAdvice(ProceedingJoinPoint joinPoint) {
//
//		Object obj = null;
//
//		try {
//			joinPoint.proceed();
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		long start = System.currentTimeMillis();
//		long end = System.currentTimeMillis();
//		String methodName = joinPoint.getSignature().toShortString();
//		logger.info("{} 실행, 경과시간: {}", methodName, (end - start));
//		return obj;
//	}

}
