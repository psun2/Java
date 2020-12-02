package com.lec.sts19_rest.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "employee")   // <employee> ~ </employee>
public class EmployeeVO {
	//@XmlElement   // <id> ~ </id>
	@XmlAttribute   // id = ""
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private int age;
	@XmlElement
	private int [] score;
	
	// 어노테이션 없으면 XML 에 포함안됨.
	private double point;
}
