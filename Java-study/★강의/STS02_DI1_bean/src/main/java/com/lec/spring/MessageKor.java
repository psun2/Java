package com.lec.spring;

// IS-A, HAS-A

// Food
//  └ Drink
//  └ Hamburger

// Drink is Food  (o)
// Food is Drink  (X)

// Car, Tire
// Car is Tire
// Tire is Car

// Car has Tire : 



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

























