package com.ltns.rest_area.service.user;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.user.AuthDAO;
import com.ltns.rest_area.domain.user.AuthDTO;
import com.ltns.rest_area.domain.user.UserDTO;
import com.ltns.rest_area.domain.user.UserAuthDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDAO authDAO;

    @Autowired
    private UserSrvice userSrvice;

    // 권한 추가 (회원 가입시)
    @Override
    public int insertAuth(long uid) {
        return authDAO.insertByObject(uid);
    }

    @Override // <!-- 권한 추가 설정 -->
    public int insertAuthByUsername(UserAuthDTO userAuth) {

        UserDTO user = new UserDTO();
        user.setUm_username(userAuth.getUsername());

        List<UserDTO> users = userSrvice.findSelector(user);

        userAuth.setUid(users.get(0).getUm_uid());

        return authDAO.insertByDTO(userAuth);
    }

    // 권한 삭제 (회원 탈퇴시)
    @Override
    public int deleteAuth(long uid) {
        return authDAO.deleteByObject(uid);
    }

    // auth 테이블 조회
    @Override
    public List<AuthDTO> findByUid(long uid) {
        List<DTO> list = null;
        list = authDAO.selectByObject(uid);

        List<AuthDTO> result = new ArrayList<AuthDTO>();

        if (list != null)
            list.forEach(item -> result.add((AuthDTO) item));

        return result;
    }

    // 권한 등급 조정 (유저의 uid 와 이전에 가지고 있던 권한, 바뀔 권한)
    @Override
    public int updateByUid(UserAuthDTO userAuth) {
        UserDTO user = new UserDTO();
        user.setUm_username(userAuth.getUsername());

        List<UserDTO> users = userSrvice.findSelector(user);

        userAuth.setUid(users.get(0).getUm_uid());

        return authDAO.updateByObject(userAuth);
    }

    // 권한 삭제 (어떠한 유저의 어떤 권한을 삭제 할 것인가?)
    @Override
    public int deleteByUsername(UserAuthDTO userAuth) {
        UserDTO user = new UserDTO();
        user.setUm_username(userAuth.getUsername());

        List<UserDTO> users = userSrvice.findSelector(user);

        userAuth.setUid(users.get(0).getUm_uid());

        return authDAO.deleteByUserAuth(userAuth);
    }

    // userView 를 조회 findAll
    @Override
    public List<UserAuthDTO> finAll() {
        List<DTO> list = null;
        list = authDAO.selectAll();

        List<UserAuthDTO> result = new ArrayList<UserAuthDTO>();

        if (list != null)
            list.forEach(item -> result.add((UserAuthDTO) item));

        return result;
    }

    // userView 를 조회 findByUsername
    @Override
    public List<UserAuthDTO> findByUsername(String username) {
        List<DTO> list = null;
        list = authDAO.selectByString(username);

        List<UserAuthDTO> result = new ArrayList<UserAuthDTO>();

        if (list != null)
            list.forEach(item -> result.add((UserAuthDTO) item));

        return result;
    }


}
