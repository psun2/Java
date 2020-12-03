package com.lec.spring.di01;

import com.lec.spring.MessageBean;
import com.lec.spring.MessageEng;
import com.lec.spring.MessageKor;

/*
	Dependency Injection (DI, 의존주입)
	필요한 객체는 누가 만들어 사용하나?
	
	방법1 : 직접 생성하여 사용
*/
public class DIMain01 {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		
		MessageBean msg = null;
		
		// 필요한 MessageBean 객체를
		//msg = new MessageKor();  // 직접 만들어(new) 사용
		
		msg = new MessageEng();
		msg.sayHello();
		
		System.out.println("Main 종료");
	}

}
















