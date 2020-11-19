package com.lec.spring.di01;

import com.lec.spring.MessageBean;
import com.lec.spring.MessageEng;

/*
Dependency Injection (DI, 의존주입)
필요한 객체는 누가 만들어 사용하나?

방법1 : 직접 생성하여 사용
*/

public class DIMain01 { // DIMain01 이 MessageBean 을 의존 하고 있음.
	// DIMain01 은 MessageBean 이 없으면 동작하지 않습니다.
	// MessageBean 이 바뀌면 의존하는 객체에서도 코드 변경이 이루어 집니다.

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("Main 시작");

		MessageBean msg = null;
		// msg = new MessageKor(); // 직접 만들어(new) 사용

		msg = new MessageEng();
		// 의존성이 강화되어, 다른 객체로 변경시 코드가 변경되어야 하는 불편함이 따릅니다.

		msg.sayHello();

		long end = System.currentTimeMillis();
		System.out.println("Main 종료");
		System.out.println(end - start + "ms");
	}

}
