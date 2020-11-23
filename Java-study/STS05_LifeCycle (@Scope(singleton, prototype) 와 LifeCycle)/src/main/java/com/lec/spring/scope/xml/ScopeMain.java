package com.lec.spring.scope.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.Score;

public class ScopeMain {

	public static void main(String[] args) {
		System.out.println("Main() 시작\n");

		// 컨테이너 등록
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		System.out.println("컨테이너 생성");

		// 설정파일 등록
		ctx.load("classpath:scopeSetting.xml");
		System.out.println("설정파일 등록");

		// refresh() => 한 개의 컨테이너에서 단 한번밖에 사용 할 수 있습니다.
		ctx.refresh();

		Score scoreSingleton1 = ctx.getBean("score1", Score.class);
		Score scoreSingleton2 = ctx.getBean("score1", Score.class);
		System.out.println(scoreSingleton1);
		System.out.println(scoreSingleton2);
		// Score(kor=55, eng=99, math=5, comment=영어 공부하세요!)
		// Score(kor=55, eng=99, math=5, comment=영어 공부하세요!)

		scoreSingleton2.setComment("자고 싶어요 !!!");
		System.out.println(scoreSingleton1);
		System.out.println(scoreSingleton2);
		// Score(kor=100, eng=100, math=100, comment=자고 싶어요 !!!)
		// Score(kor=100, eng=100, math=100, comment=자고 싶어요 !!!)

		if (scoreSingleton1 == scoreSingleton2)
			System.out.println("주소값이 같은 객체 입니다.");
		else
			System.out.println("주소값이 다른 객체 입니다.");

		// 주소값이 같은 객체 입니다.

		Score scorePrototype1 = ctx.getBean("score2", Score.class);
		Score scorePrototype2 = ctx.getBean("score2", Score.class);
		System.out.println(scorePrototype1);
		System.out.println(scorePrototype2);
		// Score(kor=11, eng=22, math=33, comment=다음엔 44점이네요)
		// Score(kor=11, eng=22, math=33, comment=다음엔 44점이네요)

		scorePrototype2.setComment("잠은 사치입니다.");
		System.out.println(scorePrototype1);
		System.out.println(scorePrototype2);
		// Score(kor=11, eng=22, math=33, comment=다음엔 44점이네요)
		// Score(kor=10, eng=20, math=30, comment=잠은 사치입니다.)

		if (scorePrototype1 == scorePrototype2)
			System.out.println("주소값이 같은 객체 입니다.");
		else
			System.out.println("주소값이 다른 객체 입니다.");

		// 주소값이 다른 객체 입니다.

		ctx.close();
		System.out.println("\nMain() 종료");

	}

}
