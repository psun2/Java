package com.ltns.rest_area.domain;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthDAO {

    // 권한 추가 (회원 가입시)
    @Insert(AuthSQL.INSERT_AUTH)
    int insertAuth(long uid);

    // 권한 추가 설정
    @Insert(AuthSQL.SELECT_AUTH_BY_UID)
    int insertAuthByUsername(UserAuthDTO userAuth);

    // 권한 삭제 (회원 탈퇴시)
    @Delete(AuthSQL.DELETE_AUTH_BY_UID)
    int deleteAuth(long uid);

    // auth 테이블 조회
    @Select(AuthSQL.SELECT_AUTH_BY_UID)
    List<AuthDTO> findByUid(long uid);

    // 권한 등급 조정 (유저의 uid 와 이전에 가지고 있던 권한, 바뀔 권한)
    @Update(AuthSQL.UPDATE_AUTHORITY_BY_UID_AND_AUTHORITY)
    int updateByUid(UserAuthDTO userAuth);

    // 권한 삭제 (어떠한 유저의 어떤 권한을 삭제 할 것인가?)
    @Delete(AuthSQL.DELETE_MODIFY_AUTH_BY_UID_AND_AUTHORITY)
    int deleteByUsername(UserAuthDTO userAuth);

    // userView 를 조회 findAll
    @Select(AuthSQL.SELECT_USERVIEW)
    List<UserAuthDTO> finAll();

    // userView 를 조회 findByUsername
    @Select(AuthSQL.SELECT_USERVIEW_BY_USERNAME)
    List<UserAuthDTO> findByUsername(String username);

}
