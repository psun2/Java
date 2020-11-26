package com.lec.sts13_jdbc.domain;

import java.util.List;

import com.lec.beans.DAO;

public interface WriteDAO extends DAO{

	// 전체 SELECT
	List<WriteDTO> select();

	// final + ??
	// final + 변수 => 더이상 변경 X (상수)
	// final + 메소드 => 더이상 오버라이딩 X
	// final + 클래스 => 더이상 상속 X
	int insert(WriteDTO dto); // end insert()

	// 글을 읽어 조회수 증가
	List<WriteDTO> readByUid(long uid); // end readByUid()

	// 글 읽기(update.do) => 업데이트에 꽃아줄때 조회수가 증가하면 안되기 때문...
	List<WriteDTO> selectByUid(long uid); // end selectByUid();

	int update(WriteDTO dto); // end update()

	int deleteByUid(long uid);

}