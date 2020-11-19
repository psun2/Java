package com.lec.spring;

public class MessageEng implements MessageBean {

	String msgEng = "\nHello\n";

	@Override
	public void sayHello() {
		System.out.println(msgEng);
	}

}
