package com.lec.sts19_rest.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.sts19_rest.domain.WriteDAO;
import com.lec.sts19_rest.domain.WriteDTO;

@RestController // RestController 는 view의 이름을 주는것이 아닌 Object type 을 return 합니다.
@RequestMapping("MyRest")
public class MyRestJSONController {

	@Autowired
	SqlSession sqlSession;

	@GetMapping()
	public String tqText() {
		return "tq";
	}

	@GetMapping("/")
	public String helloText() {
		return "hello";
	}

	// 자바객체 [Object] -> JSON
	@GetMapping("/JSON")
	public WriteDTO JSON() {
		
		WriteDTO dto = new WriteDTO(100, "안녕하세요", "REST", "하하하", 123, new Timestamp(10000), new int[]{ 1, 2, 3 });
		return dto;
	}

	// 자바객체 [List] -> 배열형태 JSON
	@GetMapping("/listJSON")
	public List<WriteDTO> listJSON() {
		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
		return dao.select();
	}

	// 자바객체 [Array] -> 배열형태 JSON
	@GetMapping("/arrayJSON")
	public WriteDTO[] arrayJSON() {
		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
		List<WriteDTO> list = dao.select();

		WriteDTO[] arrays = new WriteDTO[list.size()];
		return list.toArray(arrays);
	}

	// 자바객체 [Map<K, V>] -> js Object형태 JSON
	@GetMapping("/jsObjectJSON")
	public Map<Integer, WriteDTO> jsObjectJSON() {
		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
		List<WriteDTO> list = dao.select();

		Map<Integer, WriteDTO> map = new HashMap<Integer, WriteDTO>();

		list.forEach((dto) -> map.put(dto.getUid(), dto));

		return map;
	}

}
