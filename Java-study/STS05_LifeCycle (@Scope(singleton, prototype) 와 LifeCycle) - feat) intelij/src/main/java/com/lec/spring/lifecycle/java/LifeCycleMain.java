package com.lec.spring.lifecycle.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifeCycleMain {

    public static void main(String[] args) {

        System.out.println("Main() 시작\n");

        // 컨테이너 생성
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        System.out.println("✔컨테이너 생성");

        annotationConfigApplicationContext.register(ScoreConfig.class);
        // 설정파일 register 생성자로 넣을때와 같은 기능을 합니다.
        System.out.println("✔설정 load 완료");

        annotationConfigApplicationContext.refresh(); // <-- refresh() 해야 제대로 설정 완료 (빈  생성) 됩니다.
        // refresh() 는 단 한개의 컨테이너에서 단 한번만 가능합니다.
        System.out.println("✔컨테이너 refresh 완료");

        System.out.println(annotationConfigApplicationContext.getBean("score") + " 찐 생성");
        
        annotationConfigApplicationContext.close();
        System.out.println("\nMain() 종료");

        // Main() 시작
        // ✔컨테이너 생성
        // ✔설정 load 완료
        // Score(100, 100, 100, 올백 좋아요!) 생성
        // ✔Bean 초기화 afterPropertiesSet() 호출
        // ✔컨테이너 refresh 완료
        // Score(kor=100, eng=100, math=100, comment=올백 좋아요!) 찐 생성
        // ✔Bean 소멸 destroy() 호출
        // Main() 종료
    }

}
