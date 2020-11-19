package com.lec.spring.config02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lec.spring.Score;
import com.lec.spring.Student;

//JAVA 를 이용한 DI 설정

//클래스 이름앞에 반드시 어노테이션 명시 필요
//@Configuration --> 이 클래스는 '스프링 설정'에 사용되는 클래스 입니다. 
//결국 IOC 컨테이너 역할을 하게 됨

@Configuration // 이건 설정용 자바파일입니다를 의미합니다.
public class AppConfig02 {

	@Bean // 메소드 return 타입이 bean 의 타입이 된다.
	public Score score1() { // 메소드 이름 score1 이 bean 의 name(id) 이 된다.
		// 반환타입 Score => Score 생성 빈
		// id = 메소드 이름(score1)
		return new Score(100, 80, 75, "좋아요");
	}

	@Bean
	public Student stud1() {
		return new Student("이주혁", 34, score1()); // score1() 메소드 실행시 반환되는 new 로 생성하는 것 이아닌,
		// container loding 과 동시에 생성된 score1의 id 또는 name 을 가져다 쓰겟다는 의미로 해석합니다.
	}

}
