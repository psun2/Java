package spring.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAroundAdvice implements MethodInterceptor {

	// AroundAdvice : 비지니스 로직의 앞뒤로 곁다리 업무를 넣어야 하기 때문에 around 사용

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();

		// 주업무의 로직을 처리합니다.
		Object result = invocation.proceed(); // == Object result = method.invoke(exam, args);

		long end = System.currentTimeMillis();

		String message = (end - start) + "ms 시간이 걸렸습니다.";
		System.out.println(message);
		return result;
	}

}
