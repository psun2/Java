package com.lec.spring.beans;

public abstract class Service {
	
	// 테스트용 : 메소드 호출정보 출력
	// TODO
    public void printInfo() {

        // 현재 속한
        String className = this.getClass().getSimpleName();

        // 현재 실행중인 메소드 이름
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String methodName = stackTrace[1].getMethodName();
        // 현재 실행중인 printInfo 메소드 이름이 0 번째 index 이므로 1번째 인덱스를 불러들여,
        // printInfo 가 어디서 사용중인지 알 수 있습니다.
        System.out.println(className + " 의" + methodName + "() 호출");

        // Object
        //  Throwable
        //   Exception
        //    RuntimeException
        //     NullplinterException
    }

	// 추상메소드
	// TODO
    public abstract void doAction();
}

