package com.lec.sts19_rest.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.sts19_rest.domain.WriteDAO;
import com.lec.sts19_rest.domain.WriteDTO;

@RestController // RestController 는 view의 이름을 주는것이 아닌 Object type 을 return 합니다.
@RequestMapping("MyRest")
public class MyRestResponseEntityController {

	@Autowired
	SqlSession sqlSession;

	// 접근 할수없는 uid 에 대해서 200 코드의 정상 요청에 빈 배열 반환
//	@GetMapping("/read/{uid}") 
//	public List<WriteDTO> read(@PathVariable("uid") long uid) {
//		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
//		
//		return dao.selectByUid(uid);
//	}

//	@RequestMapping("/read/{uid}")
//	public ResponseEntity<WriteDTO> read(@PathVariable("uid") int uid){
//		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
//		List<WriteDTO> list = dao.selectByUid(uid);
//		
//		// 실패
//		if(list == null || list.size() == 0)
//			return new ResponseEntity<WriteDTO>(HttpStatus.NOT_FOUND); // 404에러
//			
//		// 성공
//		return new ResponseEntity<WriteDTO>(list.get(0), HttpStatus.OK);  // 200
//	}

	@GetMapping("/read/{uid}")
	public ResponseEntity<List<WriteDTO>> read(@PathVariable("uid") int uid) {
		WriteDAO dao = sqlSession.getMapper(WriteDAO.class);
		List<WriteDTO> list = dao.selectByUid(uid);

		// 실패 404 페이지
		if (list == null || list.size() == 0)
			// return new ResponseEntity<List<WriteDTO>>(HttpStatus.NOT_FOUND); // 404에러
			return new ResponseEntity<List<WriteDTO>>(HttpStatus.BAD_REQUEST); // 404에러

		// 성공
		return new ResponseEntity<List<WriteDTO>>(list, HttpStatus.OK); // 200
	}

}
