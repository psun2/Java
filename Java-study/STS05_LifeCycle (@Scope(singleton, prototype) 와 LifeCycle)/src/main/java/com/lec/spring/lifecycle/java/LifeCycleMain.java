package com.lec.spring.lifecycle.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lec.spring.Score;

public class LifeCycleMain {
	
	public static void main(String[] args) {
		System.out.println("Main() 시작\n");
		
		// 컨테이너 등록
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		System.out.println("컨테이너 생성");
		
		// 설정파일 등록
		ctx.register(LifeCycleConfig.class);
		System.out.println("설정파일 등록");
		
		// refresh() => 한 개의 컨테이너에서 단 한번밖에 사용 할 수 있습니다.
		ctx.refresh();
		
		Score score = ctx.getBean("score", Score.class);
		System.out.println(score + " 찐 생성");
		
		ctx.close();
		System.out.println("\nMain() 종료");
		
		// Main() 시작

		// 컨테이너 생성
		// 설정파일 등록		
		// Score(70, 60, 50, 나빠요) 생성
		// ✔Bean 초기화 afterPropertiesSet() 호출
		// Score(kor=70, eng=60, math=50, comment=나빠요) 찐 생성
		// ✔Bean 소멸 destroy() 호출

		// Main() 종료
	}

}
