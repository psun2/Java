package com.lec.spring.config01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ConfigMain01 {

	public static void main(String[] args) {

		// 컨테이너는 복수개 생성 가능합니다.
		AbstractApplicationContext ctxA, ctxB, ctxAB;

		ctxA = new GenericXmlApplicationContext("classpath:appCtx01_A.xml");
		System.out.println("ctxA 컨테이너 생성\n");

		ctxB = new GenericXmlApplicationContext("classpath:appCtx01_B.xml");
		System.out.println("ctxB 컨테이너 생성\n");

		System.out.println(ctxA.getBean("score1"));
		// Score(kor=100, eng=80, math=75, comment=좋아요)
		System.out.println(ctxB.getBean("score2"));
		// Score(kor=55, eng=66, math=23, comment=점수가 말이아님..)

		ctxAB = new GenericXmlApplicationContext(
				// "classpath:appCtx01_A.xml", "classpath:appCtx01_B.xml"
				"classpath:appCtx01_B.xml", "classpath:appCtx01_A.xml"
				);
		// A 파일과 B 파일의 id 가 겹쳐도 에러 발생은 하지 않지만,
		// 나중에 시행된 파일의 id bean 이 먼저 실행된 bean 을 덮어 씌웁니다.

		// XML 과 JAVA 파일에 동시에 존재 할때 항상 XML 파일에 있는 id가 우선 입니다.
		System.out.println("ctxAB 컨테이너 생성\n");

		System.out.println(ctxAB.getBean("score1"));
		System.out.println(ctxAB.getBean("score2"));
		// Score(kor=100, eng=80, math=75, comment=좋아요)
		// Score(kor=55, eng=66, math=23, comment=점수가 말이아님..)

		if (ctxA.getBean("score1") == ctxAB.getBean("score1")) {
			System.out.println("true");
		} else {
			System.out.println("false");
			// 같은 객체지만 컨테이너가 생성된 공간이 다르므로...
			// 다른 주소로 생성됩니다.
		}

		ctxA.close();
		ctxB.close();
		ctxAB.close();

	}

}
