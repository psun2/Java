package com.lec.spring.aop02;

import com.lec.spring.beans.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import sun.rmi.runtime.Log;

@Aspect // 이 클래스가 Aspect 역할을 할 클래스임을 명시
public class LogAspect {

    // within => 클래스 기준
    // execution => 메소드 기중

    @Pointcut("within(com.lec.spring.aop02.*)") // => 클래스를 기준으로 함
    // within => 클래스 기준
    // com.lec.spring.aop02 패키지의
    // .* => 모든 클래스를 대상으로 합니다.
    public void pc1() {
    }

    @Pointcut("within(com.lec.spring.aop02.*)")
    public void pc2() {
    }

    // Before Advice (핵심코드의 앞으로의 곁다리 업무)
    @Before("pc1()")
    public void beforeAdvice() {
        System.out.println("[Before] ");
        new Logger().login();
    }

    // After Advice (핵심코드의 뒤로의 곁다리 업무)
    @After("pc2()")
    public void afterAdvice() {
        System.out.println("[After] ");
        new Logger().logout();
    }

    // Around Advice (핵심코드의 앞, 뒤로의 곁다리 업무)
    // 직접 target 메소드를 호출하고 결과나 예외 처리 가능!
    @Around("within(com.lec.spring.aop02.*)")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        // @Around 는 Object 를 return 합니다.

        // joinPoint 메소드 이름
        String signatureStr = joinPoint.getSignature().toShortString(); // MyService21.doAction() , MyService22.doAction()

        System.out.println("[Around] " + signatureStr + " 시작");
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed(); // 직접 target 메소드를 호출하고 결과나 예외 처리 가능!

        long end = System.currentTimeMillis();

        System.out.println("[Around] " + signatureStr + " 종료");
        System.out.println("[Around] 경과시간: " + (end - start) + "ms");

    }

}
