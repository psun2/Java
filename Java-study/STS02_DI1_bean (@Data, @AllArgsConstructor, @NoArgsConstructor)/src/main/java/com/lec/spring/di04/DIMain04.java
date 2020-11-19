package com.lec.spring.di04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.Student;

public class DIMain04 {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		System.out.println("Main 시작\n");

		String configLocation = "classpath:studentCtx.xml";
		AbstractApplicationContext context = new GenericXmlApplicationContext(configLocation);
		System.out.println("컨테이너 생성");

		System.out.println("생성된 빈의 갯수 : " + context.getBeanDefinitionCount() + " 개");

		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(context.getBean(name));
		}
		
		Student stud1 = context.getBean("Hong", Student.class);
		Student stud2 = context.getBean("Park", Student.class);
		
		System.out.println(stud1);
		System.out.println(stud2);
		// Student(name=홍길동, age=25, score=Score(kor=88, eng=70, math=91, comment=좋아요))
		// Student(name=박성언, age=21, score=Score(kor=88, eng=70, math=91, comment=좋아요))

		stud2.getScore().setEng(200);
		System.out.println(stud1);
		System.out.println(stud2);
		// Student(name=홍길동, age=25, score=Score(kor=88, eng=200, math=91, comment=좋아요))
		// Student(name=박성언, age=21, score=Score(kor=88, eng=200, math=91, comment=좋아요))
		
		// 같은 scoreA 를 참조 중이므로, stud2의 eng 값을 변경하였지만, stud1의 eng 값도 변경 됩니다.
		
		context.close();

		System.out.println("\nMain 종료");
		long end = System.currentTimeMillis();
		System.out.println(end - start + "ms");

	}

}
