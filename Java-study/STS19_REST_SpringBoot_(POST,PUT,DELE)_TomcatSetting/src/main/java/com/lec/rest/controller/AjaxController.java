package com.lec.rest.controller;

import com.lec.rest.domain.Result;
import com.lec.rest.domain.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest")
public class AjaxController { // ServerSetting class 참조

    // SELECT
    @GetMapping
    public ResponseEntity<Result> get(@RequestBody UserDTO dto) {

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
    public ResponseEntity<Result> post(@RequestBody UserDTO dto) {

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
    public ResponseEntity<Result> update(@RequestBody UserDTO dto) {

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
    public ResponseEntity<Result> delete(@RequestBody UserDTO dto) {

        log.info("주입받은 Body: {}", dto);

        Result result = new Result();
        result.setStatus("OK");
        result.setMessage("반환 성공");
        result.setDto(dto);

        log.info("반환하는 Result: {}", result);

        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }

}
