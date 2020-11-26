package com.lec.sts13_jdbc.service;

import java.util.List;

import com.lec.sts13_jdbc.domain.WriteDTO;

public interface BoardService {

	List<WriteDTO> list();

	int write(WriteDTO dto);

	List<WriteDTO> viewByUid(long uid);

	List<WriteDTO> selectByUid(long uid);

	int update(WriteDTO dto);

	int deleteByUid(long uid);

}