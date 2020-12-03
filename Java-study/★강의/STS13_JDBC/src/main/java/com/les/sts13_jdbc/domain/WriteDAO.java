package com.les.sts13_jdbc.domain;

import java.util.List;

public interface WriteDAO {

	// 전체 SELECT
	List<WriteDTO> select();

	int insert(WriteDTO dto); // end insert()

	List<WriteDTO> readByUid(int uid);

	// 글 읽기 (update.do)
	List<WriteDTO> selectByUid(int uid);

	//  글 수정 
	int update(WriteDTO dto);

	// 글삭제
	int deleteByUid(int uid);

}