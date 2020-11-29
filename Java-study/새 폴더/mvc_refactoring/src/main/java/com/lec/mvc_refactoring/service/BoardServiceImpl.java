package com.lec.mvc_refactoring.service;

import com.lec.mvc_refactoring.domain.BoardDAOImpl;
import com.lec.mvc_refactoring.domain.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl {

    @Autowired
    BoardDAOImpl boardDAO;

    // list 반환
    public List<BoardDTO> readAll() {
        return boardDAO.readAll();
    }
}
