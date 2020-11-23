package com.lec.spring.lifecycle.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class LifeCycleMain {

    public static void main(String[] args) {

        System.out.println("Main() 시작\n");

        // 컨테이너 생성
        GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext();
        System.out.println("✔컨테이너 생성");

        // 설정파일 load 생성자로 넣을때와 같은 기능을 합니다.
        genericXmlApplicationContext.load("classpath:lifeCycleSetting.xml");
        System.out.println("✔설정 load 완료");

        genericXmlApplicationContext.refresh(); // <-- refresh() 해야 제대로 설정 완료 (빈  생성) 됩니다.
        // refresh() 는 단 한개의 컨테이너에서 단 한번만 가능합니다.
        System.out.println("✔컨테이너 refresh 완료");

        System.out.println(genericXmlApplicationContext.getBean("score") + " 찐 생성");

        genericXmlApplicationContext.close();
        System.out.println("\nMain() 종료");

        // Main() 시작
        // ✔컨테이너 생성
        // ✔설정 load 완료
        // Score(100, 80, 75, 좋아요) 생성
        // ✔Bean 초기화 afterPropertiesSet() 호출
        // ✔컨테이너 refresh 완료
        // Score(kor = 100, eng = 80, math = 75, comment = 좋아요) 찐 생성
        // ✔Bean 소멸 destroy() 호출
        // Main() 종료
    }

}
