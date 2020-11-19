package com.lec.spring;

public class MessageKor implements MessageBean {

	String msgKor = "\n안녕하세요\n";
	
	public MessageKor() {
		System.out.println("MessageKor() 생성");
	}
	
	@Override
	public void sayHello() {
	System.out.println(msgKor);
	}

}
