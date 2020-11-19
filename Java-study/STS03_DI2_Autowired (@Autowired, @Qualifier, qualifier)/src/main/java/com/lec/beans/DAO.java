package com.lec.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 모든 필드를 받는 생성자
@NoArgsConstructor // 기본 생성자
public class DAO {
	private String uid; // DAO 객체 식별용도
}

// @Data annotation이 없지만 있는 DAO 부모를 상속받아서 setter 사용 가능
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