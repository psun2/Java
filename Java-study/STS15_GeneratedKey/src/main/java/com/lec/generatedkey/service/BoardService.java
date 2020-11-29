package com.lec.generatedkey.service;

import com.lec.generatedkey.domain.BoardDTO;

import java.util.List;



public interface BoardService {
    List<BoardDTO> getListAll();

    // GeneratedKey 사용
    int write(BoardDTO dto);

    List<BoardDTO> viewByUid(long uid);

    List<BoardDTO> selectByUid(long uid);

    int update(BoardDTO dto);

    int deleteByUid(long uid);
}
