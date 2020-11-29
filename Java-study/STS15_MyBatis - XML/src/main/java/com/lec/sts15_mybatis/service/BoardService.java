package com.lec.sts15_mybatis.service;

import com.lec.sts15_mybatis.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getListAll();

    int write(BoardDTO dto);

    List<BoardDTO> viewByUid(long uid);

    List<BoardDTO> selectByUid(long uid);

    int update(BoardDTO dto);

    int deleteByUid(long uid);
}
