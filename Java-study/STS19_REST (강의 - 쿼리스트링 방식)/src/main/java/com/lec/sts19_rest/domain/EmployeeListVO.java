package com.lec.sts19_rest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="employees") // <employees> ~ </employees>
public class EmployeeListVO {

	@XmlElement
	private List<EmployeeVO> emp = new ArrayList<EmployeeVO>();
	
}
