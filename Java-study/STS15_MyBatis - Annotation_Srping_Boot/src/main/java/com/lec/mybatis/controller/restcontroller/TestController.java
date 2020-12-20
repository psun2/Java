package com.lec.mybatis.controller.restcontroller;

import com.lec.mybatis.domain.TestDTO;
import com.lec.mybatis.domain.TestResult;
import com.lec.mybatis.service.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestServiceImpl testService;

    @GetMapping("/test/{data}")
    public ResponseEntity<TestResult> getTest(@PathVariable("data") long data1) {

        log.info("paramter 확인 : {}", data1);

        TestResult testResult = new TestResult();
        testResult.setStatus("FAIL");


        try {

            List<TestDTO> list = testService.getTest(data1);
            log.info("service 동작 확인 : {}", list);

            if (list.size() == 0) {
                testResult.setMessage("해당 자료를 찾지 못했습니다.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            testResult.setStatus("SUCCESS");
            testResult.setMessage("트랜잭션 성공");
            testResult.setCount(list.size());
            testResult.setList(list);


        } catch (Exception e) {
            log.error("에러발생 : {}", e.getMessage());
            testResult.setMessage(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TestResult>(testResult, HttpStatus.OK);
    }
}
