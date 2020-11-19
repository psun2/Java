package com.lec.spring;

// IS-A, HAS-A

// Food
//  └ Drink
//  └ Hamberger

// Drink is Food ⭕⭕
// Food is Drink ❌

// Car, Tire
// Car is Tire
// Tire is Car

// Car has Tire => 자동차는 타이어를 가지고 있다 => 자동차 설계시 맴버 변수로 타이어를 가지고 있어야 한다.❌

public class MessageKor implements MessageBean {

	String msgKor = "안녕하세요";

	// 생성자
	public MessageKor() {
		System.out.println("MessageKor() 생성");
	}

	@Override
	public void sayHello() {
		System.out.println(msgKor);
	}

}
