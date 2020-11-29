package com.lec.generatedkey.domain;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardDAO {
    // MyBatis 는 interface 의 구현체를 만듭니다. (BoardDAOImpl)

    List<BoardDTO> getListAll();

    int write(@Param("dto") BoardDTO dto);

    // 오버로딩 !!!!!!!!!!!!!!
    // 자바 파일에선 가능하지만 xml에서 mapper 에서 id 는 유니크 하기때문에
    // 에러가 발생합니다.
    int write(String subject, String content, String name);

    // MyBatis 는 두개의 트랜잭션을 사용 할 수 없습니다.
    // List<WriteDTO> readByUid(long uid);

    // 조회수 증가
    int viewCnt(long uid);

    List<BoardDTO> selectByUid(long uid);

    int update(BoardDTO dto);

    int update(long uid, @Param("a") BoardDTO dto);

    int update(long uid, String subject, String content, @Param("dto") BoardDTO dto);

    int deleteByUid(long uid);

}
