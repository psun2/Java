package com.lec.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest")
public class AjaxController { // ServerSetting class 참조

	// SELECT
	@GetMapping
	public ResponseEntity<Result> get(UserDTO dto) {

		log.info("주입받은 Body: {}", dto);

		Result result = new Result();
		result.setStatus("OK");
		result.setMessage("반환 성공");
		result.setDto(dto);

		log.info("반환하는 Result: {}", result);

		return new ResponseEntity<Result>(result, HttpStatus.OK);

	}

	// INSERT
	@PostMapping
	public ResponseEntity<Result> post(UserDTO dto) {

		log.info("주입받은 Body: {}", dto);

		Result result = new Result();
		result.setStatus("OK");
		result.setMessage("반환 성공");
		result.setDto(dto);

		log.info("반환하는 Result: {}", result);

		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}

	// UPDATE
	@PutMapping
	public ResponseEntity<Result> update(UserDTO dto) {

		log.info("주입받은 Body: {}", dto);

		Result result = new Result();
		result.setStatus("OK");
		result.setMessage("반환 성공");
		result.setDto(dto);

		log.info("반환하는 Result: {}", result);

		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}

	// DELETE
	@DeleteMapping
	public ResponseEntity<Result> delete(UserDTO dto) {

		log.info("주입받은 Body: {}", dto);

		Result result = new Result();
		result.setStatus("OK");
		result.setMessage("반환 성공");
		result.setDto(dto);

		log.info("반환하는 Result: {}", result);

		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}

}
