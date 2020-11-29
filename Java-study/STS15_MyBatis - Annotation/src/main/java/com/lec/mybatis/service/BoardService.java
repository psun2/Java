package com.lec.mybatis.service;


import com.lec.mybatis.domain.BoardDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getListAll();

    // GeneratedKey 사용
    int write(BoardDTO dto);

    List<BoardDTO> viewByUid(long uid);

    List<BoardDTO> selectByUid(long uid);

    int update(@Param("board") BoardDTO dto);

    int deleteByUid(long uid);
}
