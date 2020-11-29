package com.lec.generatedkey.service;

import com.lec.generatedkey.domain.BoardDTO;
import com.lec.generatedkey.domain.JdbcTemplateBoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcTemplateBoardServiceImpl {

    @Autowired
    JdbcTemplateBoardDAO dao;

    public int write(BoardDTO dto) {
        return dao.write(dto);
    }

}
