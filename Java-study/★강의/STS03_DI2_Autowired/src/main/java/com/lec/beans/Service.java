package com.lec.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.Data;

public interface Service {}

@Data
class RegisterService implements Service{
	DAO dao;
	
	// 기본생성자 없다
	
	public RegisterService(DAO dao) {
		System.out.printf("RegisterService(%s)생성\n", dao.toString());
		this.dao = dao;
	}
}

@Data
class ReadService implements Service{
	DAO dao;
	
	// 기본생성자 없다
	
	// 컨테이너 내에서 생성될때
	// 컨테이너 안에 이미 생성된 매개변수 '타입'과 일치하는 bean 객체가 주입된다.
	@Autowired
	public ReadService(DAO dao) {
		System.out.printf("ReadService(%s)생성\n", dao.toString());
		this.dao = dao;
	}
}

@Data
class UpdateService implements Service {
	
	// 멤버변수 '타입' 과 일치하는 bean 객체가 자동 주입된다.
	@Autowired
	DAO dao;
	
	public UpdateService() {
		System.out.println("UpdateService() 생성");
	}
}

@Data
class DeleteService implements Service {
	
	DAO dao;
	
	public DeleteService() {
		System.out.println("DeleteService() 생성");
	}
	
	// setter 의 매개변수 '타입' 과 일치하는 bean 이 자동주입된다.
	@Autowired
	public void setDao(DAO dao) {
		System.out.println("setDao(" + dao + ") 호출");
		this.dao = dao;
	}
}

@Data
class ListService implements Service{
	
	@Autowired 
	@Qualifier("memberDAO")
	DAO memberDao;
	
	DAO boardDao;
	
	public ListService() {
		System.out.println("ListService() 생성");
	}
	
	@Autowired
	@Qualifier("boardDAO")
	public void setBoardDao(DAO boardDao) {
		this.boardDao = boardDao;
		System.out.println("setBoardDao(" + boardDao + ") 호출");
	}
	
}


//동일 타입의 DAO 객체가 여러개이고 qualifier 가 지정되어 있지 않으면 
//이름이 같은 것으로 찾는다.
@Data
class ViewService implements Service {
	@Autowired
	DAO myDao;   // @Autowired 된 변수 '이름' 에 주목
	
	public ViewService() {
		System.out.println("ViewService() 생성\n");
	}
}


class PageService implements Service {
	
	DAO memberDao;
	DAO boardDao;
	
	public PageService() {
		System.out.println("PageService() 생성");
	}
	
}


















