package com.lec.sts19_rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest")
public class AjaxController {

	// SELECT
	@GetMapping
	public ResponseEntity<Result> get(HttpServletRequest request) {

		UserDTO dto = null;

		try {
			request.setCharacterEncoding("UTF-8"); // 인코딩 설정

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(request.getInputStream(), "UTF-8"));

			String str = null;
			StringBuffer json = new StringBuffer();

			// json 형태의 body를 stream을 통하여 빨아냅니다.
			while ((str = bufferedReader.readLine()) != null) {
				json.append(str);
				log.info("현재 들어온 json : {}", str);
			}

			log.info("최종 JSON 결과물 : {}", json.toString());

			// jackson 라이브러리의 ObjectMapper객체를 통하여
			// 최종결과물 json을 해당 컬럼이 존재하는 객체에 자동주입
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(json.toString(), UserDTO.class);
			// json 을 read 읽어들여 해당 name이 일치하는 해당 객체(UserDTO.class)의
			// 맴버 변수에 자동주입

			log.info("자동주입된 UserDTO 결과물 : {}", dto);

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("에러발생: {}", e.getMessage());
			return new ResponseEntity<Result>(HttpStatus.NOT_FOUND);
		}

		Result result = new Result();
		result.setStatus("OK");
		result.setMessage("반환 성공");
		result.setDto(dto);

		return new ResponseEntity<Result>(result, HttpStatus.OK);

	}

	// INSERT
	@PostMapping
	public ResponseEntity<Result> post(HttpServletRequest request) {

		UserDTO dto = null;

		try {
			request.setCharacterEncoding("UTF-8"); // 인코딩 설정

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(request.getInputStream(), "UTF-8"));

			String str = null;
			StringBuffer json = new StringBuffer();

			// json 형태의 body를 stream을 통하여 빨아냅니다.
			while ((str = bufferedReader.readLine()) != null) {
				json.append(str);
				log.info("현재 들어온 json : {}", str);
			}

			log.info("최종 JSON 결과물 : {}", json.toString());

			// jackson 라이브러리의 ObjectMapper객체를 통하여
			// 최종결과물 json을 해당 컬럼이 존재하는 객체에 자동주입
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(json.toString(), UserDTO.class);
			// json 을 read 읽어들여 해당 name이 일치하는 해당 객체(UserDTO.class)의
			// 맴버 변수에 자동주입

			log.info("자동주입된 UserDTO 결과물 : {}", dto);

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("에러발생: {}", e.getMessage());
			return new ResponseEntity<Result>(HttpStatus.NOT_FOUND);
		}

		Result result = new Result();
		result.setStatus("OK");
		result.setMessage("반환 성공");
		result.setDto(dto);

		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}

	// UPDATE
	@PutMapping
	public ResponseEntity<Result> update(HttpServletRequest request) {

		UserDTO dto = null;

		try {
			request.setCharacterEncoding("UTF-8"); // 인코딩 설정

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(request.getInputStream(), "UTF-8"));

			String str = null;
			StringBuffer json = new StringBuffer();

			// json 형태의 body를 stream을 통하여 빨아냅니다.
			while ((str = bufferedReader.readLine()) != null) {
				json.append(str);
				log.info("현재 들어온 json : {}", str);
			}

			log.info("최종 JSON 결과물 : {}", json.toString());

			// jackson 라이브러리의 ObjectMapper객체를 통하여
			// 최종결과물 json을 해당 컬럼이 존재하는 객체에 자동주입
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(json.toString(), UserDTO.class);
			// json 을 read 읽어들여 해당 name이 일치하는 해당 객체(UserDTO.class)의
			// 맴버 변수에 자동주입

			log.info("자동주입된 UserDTO 결과물 : {}", dto);

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("에러발생: {}", e.getMessage());
			return new ResponseEntity<Result>(HttpStatus.NOT_FOUND);
		}

		Result result = new Result();
		result.setStatus("OK");
		result.setMessage("반환 성공");
		result.setDto(dto);

		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}

	// DELETE
	@DeleteMapping
	public ResponseEntity<Result> delete(HttpServletRequest request) {

		UserDTO dto = null;

		try {
			request.setCharacterEncoding("UTF-8"); // 인코딩 설정

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(request.getInputStream(), "UTF-8"));

			String str = null;
			StringBuffer json = new StringBuffer();

			// json 형태의 body를 stream을 통하여 빨아냅니다.
			while ((str = bufferedReader.readLine()) != null) {
				json.append(str);
				log.info("현재 들어온 json : {}", str);
			}

			log.info("최종 JSON 결과물 : {}", json.toString());

			// jackson 라이브러리의 ObjectMapper객체를 통하여
			// 최종결과물 json을 해당 컬럼이 존재하는 객체에 자동주입
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(json.toString(), UserDTO.class);
			// json 을 read 읽어들여 해당 name이 일치하는 해당 객체(UserDTO.class)의
			// 맴버 변수에 자동주입

			log.info("자동주입된 UserDTO 결과물 : {}", dto);

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("에러발생: {}", e.getMessage());
			return new ResponseEntity<Result>(HttpStatus.NOT_FOUND);
		}
		Result result = new Result();
		result.setStatus("OK");
		result.setMessage("반환 성공");
		result.setDto(dto);

		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}

}
