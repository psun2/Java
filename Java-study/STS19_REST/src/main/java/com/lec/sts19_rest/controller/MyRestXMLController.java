package com.lec.sts19_rest.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.sts19_rest.domain.EmployeeListVO;
import com.lec.sts19_rest.domain.EmployeeVO;

@RestController // RestController 는 view의 이름을 주는것이 아닌 Object type 을 return 합니다.
@RequestMapping("MyRest")
public class MyRestXMLController {

	@Autowired
	SqlSession sqlSession;

	@GetMapping("/XML")
	public EmployeeVO XML() {
		// xml response 시
		// xml을 배열을 표현할수 없으므로 동일한 태그이름으로 값이 들어갑니다.
		return new EmployeeVO(100, "홍길동", 300, new int[] { 10, 20, 30 }, 10.2);
	}

	// XML List response
	@GetMapping("/listXML")
	public EmployeeListVO listXML() {
		EmployeeListVO employees = new EmployeeListVO();

		EmployeeVO emp1 = new EmployeeVO(1, "제임스", 23, new int[] { 10, 50, 90 }, 34.2);
		EmployeeVO emp2 = new EmployeeVO(2, "클레어", 31, new int[] { 10, 50, 90 }, 23.2);
		EmployeeVO emp3 = new EmployeeVO(3, "크리스", 34, new int[] { 100, 50, 90 }, 45.2);

		employees.getEmp().add(emp1);
		employees.getEmp().add(emp2);
		employees.getEmp().add(emp3);

		return employees;
	}

}
