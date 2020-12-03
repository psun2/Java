package com.lec.beans;

import lombok.Data;

@Data
public class DAO {
	String uid;  // DAO 객체 식별용 필드
	
	public DAO() {
		System.out.println("DAO() 생성");
	}
	
	public DAO(String uid) {
		super();
		System.out.printf("DAO(%s) 생성\n", uid);
		this.uid = uid;
	}
}

// DAO 상속 받은 클래스
@Data
class DAOEx extends DAO {
	public DAOEx() {
		super();
		System.out.println("DAOEx() 생성");
	}
	
	public DAOEx(String uid) {
		super(uid);
		System.out.println("DAOEx(" + uid + ") 생성");
	}
}














































