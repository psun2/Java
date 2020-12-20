package com.lec.security.domain.mybatis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


import static org.junit.jupiter.api.Assertions.*;

class MemberDAOTest {

    private static final Logger log = LoggerFactory.getLogger(MemberDAOTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void 템플릿체크() {

        log.info("jdbcTemplate : {}", jdbcTemplate);

       Assertions.assertThat(jdbcTemplate)
       .isNotNull();

    }

}