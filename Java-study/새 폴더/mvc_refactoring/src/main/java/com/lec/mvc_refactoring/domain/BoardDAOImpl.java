package com.lec.mvc_refactoring.domain;

import common.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardDAOImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<BoardDTO> readAll() {
        return jdbcTemplate.query(C.SQL_WRITE_SELECT, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
    }

    ;
}
