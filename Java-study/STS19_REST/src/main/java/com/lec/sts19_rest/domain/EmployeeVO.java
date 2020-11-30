package com.lec.sts19_rest.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Data => XML Response 시 setter가 존재 하면 에러발생 반드시 getter만 필요합니다.
@Getter
@NoArgsConstructor // XML Response 시 기본 생성자가 없으면 오류
@AllArgsConstructor
//@XmlRootElement(name = "employee")   // <employee> ~ </employee>
@XmlRootElement // name 을 지정 하지 않을시 <클래스명> ~ </클래스명>
public class EmployeeVO {
	
	//@XmlElement   // <id> ~ </id>
	@XmlAttribute   // id = "" root element의 id라는 attribute로 동작합니다
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private int age;

	// @XmlList // => list 표현식: <score>10 20 30</score>
	@XmlElement
	private int [] score; // xml response 시
	// xml을 배열을 표현할수 없으므로 동일한 태그이름으로 값이 들어갑니다.
	
	// 어노테이션 없으면 XML 에 포함안됨.
	private double point;

}
