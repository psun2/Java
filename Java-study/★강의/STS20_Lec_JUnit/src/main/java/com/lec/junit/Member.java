package com.lec.junit;

public class Member {
	int age;

	public Member(int age) {
		super();
		if(age < 0) {
			throw new IllegalArgumentException("나이는 0보다 커야 합니다");
		}
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}



