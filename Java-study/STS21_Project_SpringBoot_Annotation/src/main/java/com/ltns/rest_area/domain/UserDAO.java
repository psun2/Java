package com.ltns.rest_area.domain;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {

    @Select(MemberSQL.FIND_ALL)
    public List<UserDTO> findAll();

    // UpdateProvider : 동적 쿼리를 사용하기 위한 어노 테이션
    @SelectProvider(type = MemberSQL.class, method = "findSelector")
    public List<UserDTO> findSelector(UserDTO user);

    @Insert(MemberSQL.INSERT_USER)
    public int insertUser(UserDTO user); // 트리거로 인한 auth 테이블 테이터 자동 생성성

    @Delete(MemberSQL.DELETE_USER)
    public int deleteByUsername(UserDTO user); // 트리거로 인한 유저 삭제시 권한삭제
    // (이부분은 좀 생각해 보아야 할게 유저마다 권한이 1:1 이 아닌 1:N 이라서 다시한번 생각해 보아야 합니다.)

    // UpdateProvider : 동적 쿼리를 사용하기 위한 어노 테이션
    @UpdateProvider(type = MemberSQL.class, method = "updateUserByUsername")
    public int updateUserByUsername(UserDTO user); // nickname password 을 반영한 update 동적 쿼리
}
