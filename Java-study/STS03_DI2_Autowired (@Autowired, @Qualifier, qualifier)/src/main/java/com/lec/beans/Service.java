package com.lec.beans;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.Data;

public interface Service {

}

@Data
class RegisterService implements Service {
	DAO dao;

	// 기본생성자 없다

	public RegisterService(DAO dao) {
		System.out.printf("RegisterService(%s) 생성\n", dao.toString());
		this.dao = dao;
	}
}

//생성자 autowired
@Data
class ReadService implements Service {
	DAO dao;

	// 기본생성자 없다

	// 컨테이너 내에서 생성될때
	// 컨테이너 안에 이미 생성된 매개변수 '타입'과 일치하는 bean 객체가 자동 주입된다.
	@Autowired
	public ReadService(DAO dao) {
		System.out.printf("ReadService(%s) 생성\n", dao.toString());
		this.dao = dao;
	}
}

//맴버변수 autowired
@Data
class UpdateService implements Service {

	// 맴버변수 '타입' 과 일치하는 bean 객체가 자동 주입된다.
	@Autowired
	DAO dao;

	public UpdateService() {
		System.out.println("UpdateService() 생성\n");
	}

}

//setter autowired
@Data
class DeleteService implements Service {

	DAO dao;

	public DeleteService() {
		System.out.println("DeleteService() 생성\n");
	}

	// setter 의 매개변수 '타입' 과 일치하는 bean 객체가 자동 주입된다.
	@Autowired
	public void setDao(DAO dao) {
		System.out.println("setDAO(" + dao + ") 호출");
		this.dao = dao;
	}

}

@Data
class ListService implements Service {

	@Autowired
	@Qualifier("memberDAO")
	// <!-- @Qualifier 는 @Autowired 와 달리 ‘멤버변수’ 와 ‘setter’ 에만 적용 가능 (생성자 에는 적용 불가)
	// -->
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

//<!-- 
//	동일 차입의 DAO 객체가 여러개이고 qualifier 가 지정되어 있지 않으면 이름이 같은 것으로 찾는다.
//	bean 의 id 와 맴버변수의 변수 이름이 같은 것 을 찾아 주입합니다.
//-->

//@Autowired 빈(bean) 객체 적용 순서 (정리)
//@Autowired 로 자동 주입되는 Bean 객체 matching 순서는 다음과 같음
//1.   타입이 같은 bean 객체 검색함 . 한 개면 해당 bean 객체 사용
//2.   타입이 같은 bean 객체가 두 개 이상이면 , 
//    @Qualifier 로 지정한 bean 객체를 찾음 . 있으면 해당 객체 사용 .
//3.   타입이 같은 bean 객체가 두 개 이상이고 , 
//    @Qualifier 가 없을 경우 변수이름이 같은/유사한 bean 객체를 찾음 .
@Data
class ViewService implements Service {

	@Autowired
	DAO myDao; // @Autowried 된 변수 '이름' 에 주목

	public ViewService() {
		System.out.println("ViewService() 생성");
	}
}

@Data
class PageService implements Service {

	@Resource(name = "dao1")
	DAO memberDao;
	@Resource(name = "dao2")
	DAO boardDao;

	public PageService() {
		System.out.println("PageService() 생성");
	}
}
