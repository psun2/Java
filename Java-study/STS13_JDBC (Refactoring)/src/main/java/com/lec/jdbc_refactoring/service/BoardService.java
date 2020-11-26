package com.lec.jdbc_refactoring.service;

import com.lec.jdbc_refactoring.domain.WriteDTO;

import java.util.List;

public interface BoardService {
    List<WriteDTO> readAll();

    int create(WriteDTO dto);

    List<WriteDTO> readyAndViewcntIncrease(long uid);

    List<WriteDTO> readByUid(long uid);

    int update(WriteDTO dto);

    int delete(long uid);
}
