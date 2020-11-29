package com.lec.interceptor.service;

import com.lec.interceptor.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getListAll();

    int write(BoardDTO dto);

    List<BoardDTO> viewByUid(long uid);

    List<BoardDTO> selectByUid(long uid);

    int update(BoardDTO dto);

    int deleteByUid(long uid);
}
