package com.lec.mybatis.service;

import com.lec.mybatis.domain.TestDAO;
import com.lec.mybatis.domain.TestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestServiceImpl {

    // spring boot 에서는 session을 이용한
    // mapper로 dao를 가져올수도 있지만,
    // dao 에 세션이 연결된 상태이므로 바로 접근도 가능합니다.
    @Autowired
    private TestDAO testDAO;

    public List<TestDTO> getTest(long data1) {

        List<TestDTO> list = null;

        try {
            list = testDAO.getTest(data1);
        } catch (Exception e) {
            log.error("트랜잭션시 에러 발생 : {}", e.getMessage());
        }

        log.info("paramter 확인 : {}", data1);

        log.info("쿼리 확인 : {}", testDAO.getTest(data1));

        return list;
    }

}
