package com.lec.sts19_rest.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lec.sts19_rest.domain.AjaxWriteList;
import com.lec.sts19_rest.domain.AjaxWriteResult;
import com.lec.sts19_rest.domain.WriteDTO;
import com.lec.sts19_rest.service.AjaxService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/board")
@Slf4j
public class AjaxController {

	@Autowired
	private AjaxService ajaxService;

	// 글 목록 (페이징)
	@GetMapping("/{page}/{pageRows}")
	public ResponseEntity<AjaxWriteList> list(@PathVariable int page, // 현재 페이지
			@PathVariable int pageRows) { // 한 '페이지' 에 몇개의 글을 리스트

		// response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL";

		// 페이징 관련 세팅 값들
		int writePages = 10; // 한 [페이징] 에 몇개의 '페이지'를 표현할 것인가?
		int totalPage = 0; // 총 몇 '페이지' 분량인가?
		int totalCnt = 0; // 글은 총 몇개인가?

		List<WriteDTO> list = null;

		try {
			// 글 전체 개수 구하기
			totalCnt = ajaxService.count();

			// 총 몇 페이지 분량?
			totalPage = (int) Math.ceil(totalCnt / (double) pageRows);

			// from: 몇번째 row 부터?
			int from = (page - 1) * pageRows + 1; // ORACLE 은 1부터 시작
			// int from = (page - 1) * pageRows; // MySQL 은 0부터 시작

			list = ajaxService.list(from, pageRows);

			if (list == null) {
				message.append("[리스트할 데이터가 없습니다]");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				status = "OK";
				message.append("[트랜잭션 성공]");
			}

		} catch (Exception e) {
			log.error("[트랜잭션 에러: {}]" + e.getMessage());
			message.append("[트랜잭션 에러:" + e.getMessage() + "]");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		AjaxWriteList result = new AjaxWriteList();
		result.setStatus(status);
		result.setMessage(message.toString());

		if (list != null) {
			result.setCount(list.size());
			result.setList(list);
		}

		result.setPage(page);
		result.setTotalPage(totalPage);
		result.setWritePages(writePages);
		result.setPageRows(pageRows);
		result.setTotalCnt(totalCnt);
		result.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		return new ResponseEntity<AjaxWriteList>(result, HttpStatus.OK);
	}

	// 글 읽기
	@GetMapping("/{uid}")
	public ResponseEntity<AjaxWriteList> view(@PathVariable int uid) {
		AjaxWriteList result = new AjaxWriteList();
		result.setStatus("FAIL");
		result.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		List<WriteDTO> list = null;

		try {

			list = ajaxService.viewByUid(uid); // 조회수 증가와 글 읽기

			if (list == null || list.size() == 0) {
				result.setMessage("[해당 데이터가 없습니다.]");
				return new ResponseEntity<AjaxWriteList>(result, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				result.setStatus("OK");
				result.setCount(list.size());
				result.setMessage("[트랜잭션 성공 : " + list.size() + " insert]");
				result.setList(list);
			}

		} catch (Exception e) {
			log.error("[트랜잭션 에러 : {}]", e.getMessage());
			result.setMessage("[트랜잭션 에러:" + e.getMessage() + "]");
			return new ResponseEntity<AjaxWriteList>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<AjaxWriteList>(result, HttpStatus.OK);
	}

	// 글작성
	@PostMapping("/x-www-form-urlencoded") // querystring 형태의 body 가 들어옵니다. (lnline 형태의 body)
	// url : /board
	public ResponseEntity<AjaxWriteResult> writeOkXXX(WriteDTO dto) {
		// TODO

		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus("FAIL");
		result.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		int cnt = 0;

		try {
			cnt = ajaxService.write(dto);
			if (cnt == 0) {
				result.setMessage("[트랜잭션 실패 : 0 insert]");
				return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.BAD_REQUEST);
			} else {
				result.setMessage("[트랜잭션 성공 : " + cnt + " insert]");
				result.setStatus("OK");
				result.setCount(cnt);
			}
		} catch (Exception e) {
			log.error("[트랜잭션 에러: {}]" + e.getMessage());
			result.setMessage("[트랜잭션 에러:" + e.getMessage() + "]");
			return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.OK);

	}

	// 글작성
	@PostMapping // JSON 형태의 body 가 들어옵니다. (@RequestBody 필수)
	public ResponseEntity<AjaxWriteResult> writeOk(@RequestBody WriteDTO dto) {

		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus("FAIL");
		result.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		int cnt = 0;

		try {
			cnt = ajaxService.write(dto);
			if (cnt == 0) {
				result.setMessage("[트랜잭션 실패 : 0 insert]");
				return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.BAD_REQUEST);
			} else {
				result.setMessage("[트랜잭션 성공 : " + cnt + " insert]");
				result.setStatus("OK");
				result.setCount(cnt);
			}
		} catch (Exception e) {
			log.error("[트랜잭션 에러: {}]" + e.getMessage());
			result.setMessage("[트랜잭션 에러:" + e.getMessage() + "]");
			return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.OK);

	}

	// 글수정
	@PutMapping("/x-www-form-urlencoded") // querystring 형태의 body 가 들어옵니다. (lnline 형태의 body)
	public ResponseEntity<AjaxWriteResult> updateOXXX(WriteDTO dto) {

		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus("FAIL");
		result.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		int cnt = 0;

		try {
			cnt = ajaxService.update(dto);
			if (cnt == 0) {
				result.setMessage("[트랜잭션 실패 : 0 insert]");
				return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.BAD_REQUEST);
			} else {
				result.setMessage("[트랜잭션 성공 : " + cnt + " insert]");
				result.setStatus("OK");
				result.setCount(cnt);
			}
		} catch (Exception e) {
			log.error("[트랜잭션 에러: {}]" + e.getMessage());
			result.setMessage("[트랜잭션 에러:" + e.getMessage() + "]");
			return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.OK);
	}

	// 글수정
	@PutMapping // JSON 형태의 body 가 들어옵니다. (@RequestBody 필수)
	public ResponseEntity<AjaxWriteResult> updateOk(@RequestBody WriteDTO dto) {

		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus("FAIL");
		result.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		int cnt = 0;

		try {
			cnt = ajaxService.update(dto);
			if (cnt == 0) {
				result.setMessage("[트랜잭션 실패 : 0 insert]");
				return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.BAD_REQUEST);
			} else {
				result.setMessage("[트랜잭션 성공 : " + cnt + " insert]");
				result.setStatus("OK");
				result.setCount(cnt);
			}
		} catch (Exception e) {
			log.error("[트랜잭션 에러: {}]" + e.getMessage());
			result.setMessage("[트랜잭션 에러:" + e.getMessage() + "]");
			return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.OK);
	}

	// 글삭제
	@DeleteMapping("/x-www-form-urlencoded") // querystring 형태의 body 가 들어옵니다. (lnline 형태의 body)
	public ResponseEntity<AjaxWriteResult> deleteOkXXX(int[] uids) {

		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus("FAIL");
		result.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		int cnt = 0;

		try {
			cnt = ajaxService.delete(uids);
			if (cnt == 0) {
				result.setMessage("[트랜잭션 실패 : 0 insert]");
				return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.BAD_REQUEST);
			} else {
				result.setMessage("[트랜잭션 성공 : " + cnt + " insert]");
				result.setStatus("OK");
				result.setCount(cnt);
			}
		} catch (Exception e) {
			log.error("[트랜잭션 에러: {}]" + e.getMessage());
			result.setMessage("[트랜잭션 에러:" + e.getMessage() + "]");
			return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.OK);
	}

	// 글삭제
	@DeleteMapping // JSON 형태의 body 가 들어옵니다. (@RequestBody 필수)
	public ResponseEntity<AjaxWriteResult> deleteOk(@RequestBody WriteDTO uids) {
		// int[] uid => Error
		// jackson이 자동주입 해주므로... setter가 반드시 있어야하며
		// inline  으로 들어오는 x-www-form-urlencoded 이 방식은
		// setter 가 필요없이 int[] 을 사용 할 수 있습니다.

		AjaxWriteResult result = new AjaxWriteResult();
		result.setStatus("FAIL");
		result.setTimeStamp(new Timestamp(System.currentTimeMillis()));

		int cnt = 0;

		try {
			cnt = ajaxService.delete(uids.getUids());
			if (cnt == 0) {
				result.setMessage("[트랜잭션 실패 : 0 insert]");
				return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.BAD_REQUEST);
			} else {
				result.setMessage("[트랜잭션 성공 : " + cnt + " insert]");
				result.setStatus("OK");
				result.setCount(cnt);
			}
		} catch (Exception e) {
			log.error("[트랜잭션 에러: {}]" + e.getMessage());
			result.setMessage("[트랜잭션 에러:" + e.getMessage() + "]");
			return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AjaxWriteResult>(result, HttpStatus.OK);
	}

}
