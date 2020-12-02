package com.lec.sts19_rest.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.sts19_rest.domain.EmployeeListVO;
import com.lec.sts19_rest.domain.EmployeeVO;
import com.lec.sts19_rest.domain.WriteDAO;
import com.lec.sts19_rest.domain.WriteDTO;

@RestController
@RequestMapping("MyRest")
public class MyRestController {
	
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/")
	public String helloTEXT() {
		return "Hello REST";
	}
	
	@RequestMapping("/helloJSON")
	public WriteDTO helloJSON() {
		WriteDTO dto = 
				new WriteDTO(100, "안녕하세요",
						"REST", "하하하", 123,  new Timestamp(10000));
		return dto;
	}
	
	// JSON 배열 데이터 ← 자바객체 List
	@RequestMapping("/listJSON")
	public List<WriteDTO> listJSON(){
		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
		return dao.select();
	}
	
	// JSON 배열 데이터 <- 자바 배열 
	@RequestMapping("/arrJSON")
	public WriteDTO [] arrJSON() {
		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
		List<WriteDTO> list = dao.select();
		WriteDTO [] arr = new WriteDTO[list.size()];
		return list.toArray(arr);
	}
	
	// JSON object 데이터 <- 자바 Map<k, v>
	@RequestMapping("/mapJSON")
	public Map<Integer, WriteDTO> mapJSON(){
		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
		List<WriteDTO> list = dao.select();
		
		Map<Integer, WriteDTO> map = new HashMap<Integer, WriteDTO>();
		
//		for(WriteDTO dto : list) {
//			// key : value => uid : DTO
//			map.put(dto.getUid(), dto);
//		}
		
		list.forEach( dto -> map.put(dto.getUid(), dto));

		
		
		return map;		
	}
	
	// XML response
	@RequestMapping("/helloXML")
	public EmployeeVO helloXML() {
		return new EmployeeVO(100, "홍길동", 200, new int[] {10, 20, 30}, 34.2);
	}
	
	@RequestMapping("/listXML")
	public EmployeeListVO listXML() {
		EmployeeListVO employees = new EmployeeListVO();
		
		EmployeeVO emp1 = new EmployeeVO(1, "제임스", 23, new int[] {10, 50, 90}, 34.2);
		EmployeeVO emp2 = new EmployeeVO(2, "클레어", 31, new int[] {10, 50, 90}, 23.2);
		EmployeeVO emp3 = new EmployeeVO(3, "크리스", 34, new int[] {100, 50, 90}, 45.2);
		
		employees.getEmp().add(emp1);
		employees.getEmp().add(emp2);
		employees.getEmp().add(emp3);
		
		return employees;
	}

	
//	@RequestMapping("/read/{uid}")
//	public List<WriteDTO> read(@PathVariable("uid") int uid){
//		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
//		return dao.selectByUid(uid);
//	}
	
	@RequestMapping("/read/{uid}")
	public ResponseEntity<WriteDTO> read(@PathVariable("uid") int uid){
		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
		List<WriteDTO> list = dao.selectByUid(uid);
		
		// 실패
		if(list == null || list.size() == 0)
			return new ResponseEntity<WriteDTO>(HttpStatus.NOT_FOUND); // 404에러
			
		// 성공
		return new ResponseEntity<WriteDTO>(list.get(0), HttpStatus.OK);  // 200
	}
	
	
	
	
}


































