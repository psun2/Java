package com.lec.spring.config02;
//JAVA 를 이용한 DI 설정
//클래스 이름앞에 반드시 어노테이션 명시 필요
//@Configuration --> 이 클래스는 '스프링 설정'에 사용되는 클래스 입니다. 
//결국 IOC 컨테이너 역할을 하게 됨

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lec.spring.Score;
import com.lec.spring.Student;

@Configuration
public class AppConfig02 {
	
	@Bean                    // 메소드 리턴타입이 bean 의 타입이 된다.
	public Score score1() {  // 메소드 이름 score1 이 bean 의 name이 된다.
		return new Score(100, 80, 75, "좋아요");
	}
	
	@Bean
	public Student stu1() {
		return new Student("이주혁", 34, score1());
	}
	
	
	
}














