package com.lec.sts19_rest.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
@MapperScan
public interface WriteDAO {

	List<WriteDTO> select();

	int insert(WriteDTO dto);

	int incViewCnt(int uid);

	List<WriteDTO> selectByUid(int uid);

	int update(int uid, @Param("a") WriteDTO dto);

	int deleteByUid(int uid);

}
