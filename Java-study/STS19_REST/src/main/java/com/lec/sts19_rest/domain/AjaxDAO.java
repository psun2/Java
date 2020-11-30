package com.lec.sts19_rest.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface AjaxDAO {

	// 페이징용 SELECT
	// from : 몇번째 row 부터
	// pageRows : 몇개의 데이터
	public List<WriteDTO> selectFromRow(@Param("from") int from, @Param("pageRows") int pageRows);
	
	// 전체 글의 개수
	public int countAll();
	
	// 긁 읽기 특정 (uid)
	public List<WriteDTO> selectByUid(int uid);
	
	// 조회수 증가
	public int incViewCnt(int uid);
	
	// 글작성
	public int insert(WriteDTO dto);
	
	// 글 수정
	public int update(WriteDTO dto);
	
	// 특정 uid(들) 의 글(들) 삭제하기
	public int deleteByUid(int[] uids);

}