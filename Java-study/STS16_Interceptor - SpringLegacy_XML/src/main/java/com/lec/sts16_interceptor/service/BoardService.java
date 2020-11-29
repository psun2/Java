package com.lec.sts16_interceptor.service;

import java.util.List;

import com.lec.sts16_interceptor.domain.BoardDTO;

public interface BoardService {
    List<BoardDTO> getListAll();

    int write(BoardDTO dto);

    List<BoardDTO> viewByUid(long uid);

    List<BoardDTO> selectByUid(long uid);

    int update(BoardDTO dto);

    int deleteByUid(long uid);
}
