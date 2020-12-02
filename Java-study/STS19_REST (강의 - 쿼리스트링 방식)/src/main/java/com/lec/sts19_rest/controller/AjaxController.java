package com.lec.sts19_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lec.sts19_rest.domain.AjaxWriteList;
import com.lec.sts19_rest.domain.AjaxWriteResult;
import com.lec.sts19_rest.domain.WriteDTO;
import com.lec.sts19_rest.service.AjaxService;

@RestController
@RequestMapping("/board")
public class AjaxController {
	
	@Autowired
	AjaxService ajaxService;
	
	// 글 목록 (페이징)
	@GetMapping("/{page}/{pageRows}")
	public AjaxWriteList list(
			@PathVariable int page,     // 현재 페이지
			@PathVariable int pageRows) {  // 한 '페이지' 에 몇개의 글을 리스트
		
		// response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		// 페이징 관련 세팅 값들
		int writePages = 10;    // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
		int totalPage = 0; // 총 몇 '페이지' 분량인가? 
		int totalCnt = 0;  // 글은 총 몇개인가?
		
		List<WriteDTO> list = null;	
		
		try {
			// 글 전체 개수 구하기
			totalCnt = ajaxService.count();
			
			// 총 몇 페이지 분량?
			totalPage = (int)Math.ceil(totalCnt / (double)pageRows);
			
			// from: 몇번째 row 부터?
			int from = (page - 1) * pageRows + 1;  // ORACLE 은 1부터 시작
//			int from = (page - 1) * pageRows;  // MySQL 은 0부터 시작
			
			list = ajaxService.list(from, pageRows);
			
			if(list == null) {
				message.append("[리스트할 데이터가 없습니다]");
			} else {
				status = "OK";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러:" + e.getMessage() + "]");
		}
		
		AjaxWriteList result = new AjaxWriteList();
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
		
		result.setPage(page);
		result.setTotalPage(totalPage);
		result.setWritePages(writePages);
		result.setPageRows(pageRows);
		result.setTotalCnt(totalCnt);
		
		return result;
	}
	
	// 글 읽기
	@GetMapping("/{uid}")   // URI : /board/{uid}
	public AjaxWriteList view(@PathVariable int uid) {
		List<WriteDTO> list = null;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		try {
			list = ajaxService.viewByUid(uid);  // 조회수 증가 + 읽기
			if(list.size() == 0) {
				message.append("[해당 데이터가 없습니다.]");
			} else {
				status = "OK";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러:" + e.getMessage() + "]");
		} 
		
		AjaxWriteList result = new AjaxWriteList();
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}	
		
		return result;
	}
	
	// 글작성
	@PostMapping("")   //  url : /board
	public AjaxWriteResult writeOk(WriteDTO dto) {

		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		int cnt = 0;
		
		try {
			cnt = ajaxService.write(dto);
			if(cnt == 0) {
				message.append("[트랜잭션 실패 : 0 insert]");
			} else {
				status = "OK";
			}
		} catch(Exception e) {
			message.append("[트랜잭션 에러: " + e.getMessage() + "]");
		}
		
		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(cnt);
		return result;
	}
	
	// 글수정
	@PutMapping("")
	public AjaxWriteResult updateOk(WriteDTO dto) {
		
		int cnt = 0;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";  
		
		try {
			cnt = ajaxService.update(dto);
			status = "OK";
			
			if(cnt == 0) {
				message.append("[트랜잭션 실패 : 0 update]");
			}
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러:" + e.getMessage() + "]");
		}
		
		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(cnt);
		return result;
	}
	
	// 글삭제
	@DeleteMapping("")
	public AjaxWriteResult deleteOk(int [] uid) {
		
		int count = 0;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";  
		
		try {
			if(uid != null)
				count = ajaxService.deleteByUid(uid);
			status = "OK";
			
		} catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러:" + e.getMessage() + "]");
		}
		
		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus(status);
		result.setMessage(message.toString());
		result.setCount(count);
		return result;
	}

}


















