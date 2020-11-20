package com.lec.spring.aop03;

import com.lec.spring.beans.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect // 이 클래스가 Aspect 역할을 할 클래스임을 명시
public class LogAspect3 {

    // within => 클래스 기준
    // execution => 메소드 기중

    @Pointcut("within(com.lec.spring.aop03.*)")
    // within => 클래스 기준
    // com.lec.spring.aop03 패키지의
    // .* 모든 클래스
    public void pc1() {
    }

    @Pointcut("within(com.lec.spring.aop02.*)")
    // within => 클래스 기준
    // com.lec.spring.aop02 패키지의
    // .* 모든 클래스
    public void pc2() {
    }

    @Pointcut("execution(* com.lec.spring.aop*.My*.*Action(..))")
    // execution => 메소드 기준
    // * => 반환 값이 어떻든 간에
    // com.lec.spring.aop* => aop 로 시작하는 모든 패키지에서
    // .My* => My로 시작하는 모든 클래스에서
    // .*Action => Action으로 끝나는 모든 메소드 인데
    // (..) => 그 메소드의 arguments 는 어떠한 것이라도 상관 없다
    public void pc3() {
    }

    // @Before("pc1()")
    // @Before("pc2()")
    // @Before("pc3()")
    @Before("pc1() || pc2()") // => pc1 과 pc2 (전부)
    // @Before("pc1() && pc2()") // => pc1 과 pc2 의 공통적인 요소
    // @Before("!pc1()") // => pc1 이 아닌
    public void beforeAdvice() {
        System.out.print("[Advice] ");
    }

}
