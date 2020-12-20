package com.ltns.rest_area.service.user;

import com.ltns.rest_area.domain.user.AuthDTO;
import com.ltns.rest_area.domain.user.UserAuthDTO;

import java.util.List;

public interface AuthService {

    // 권한 추가 (회원 가입시)
    int insertAuth(long uid);
    
    // 권한 추가 설정
    int insertAuthByUsername(UserAuthDTO userAuth);
        
    // 권한 삭제 (회원 탈퇴시)
    int deleteAuth(long uid);

    // auth 테이블 조회
    List<AuthDTO> findByUid(long uid);

    // 권한 등급 조정 (유저의 uid 와 이전에 가지고 있던 권한, 바뀔 권한)
    int updateByUid(UserAuthDTO userAuth);

    // 권한 삭제 (어떠한 유저의 어떤 권한을 삭제 할 것인가?)
    int deleteByUsername(UserAuthDTO userAuth);

    // userView 를 조회 findAll
    List<UserAuthDTO> finAll();

    // userView 를 조회 findByUsername
    List<UserAuthDTO> findByUsername(String username);
}
