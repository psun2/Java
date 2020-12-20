package com.ltns.rest_area.service.user;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.user.UserDAO;
import com.ltns.rest_area.domain.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserSrvice {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthService authService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // 전체 유저 검색
    public List<UserDTO> findAll() {
        List<UserDTO> users = new ArrayList<UserDTO>();

        List<DTO> list = userDAO.selectAll();

        if (list != null)
            list.forEach(item -> users.add((UserDTO) item));

        return users;
    }

    // 유저고유값, 유저아이디, 닉네임 을 반영한 select 동적 쿼리
    public List<UserDTO> findSelector(UserDTO user) {
        List<UserDTO> users = new ArrayList<UserDTO>();
        List<DTO> temp = userDAO.selectByObject(user);
        if (temp != null && temp.size() != 0)
            users.add((UserDTO) temp.get(0));
        return users;
    }

    // 회원 가입
    public int insertUser(UserDTO user) {
        user.setUm_password(bCryptPasswordEncoder.encode(user.getUm_password()));

        int result = userDAO.insertByObject(user);

        if (result == 1) {
            long seq = user.getUm_uid(); // 방금 회원가입한 회원의 sequence
            log.info("방금가입한 회원의 시퀀스 값 : {}", seq);

            result = authService.insertAuth(seq); // 회원 가입한 회원의 등급 테이블 insert
        }

        return result;
    }

    @Override // nickname password 을 반영한 update 동적 쿼리
    public int updateSelector(UserDTO user) {
        if (user.getUm_password() != null) {
            user.setUm_password(bCryptPasswordEncoder.encode(user.getUm_password()));
        }
        return userDAO.updateByObject(user);
    }

    // 회원 탈퇴
    public int deleteByUesrname(UserDTO user) {

        // 먼저 회원의 uid 를 알기 위하여 user 에 있는 username 으로 회원 정보 find
        List<UserDTO> findUsers = findSelector(user);
        UserDTO findUser = findUsers.get(0);

        // 등급테이블 부터 지우기 (여긴 트리거가 없기때문에 먼저 지워줘야합니다.)
        int result = authService.deleteAuth(findUser.getUm_uid());

        if (result == 1)  // 정상적으로 auth 가 지워 졌다면... 이제 그 등급을 가졌던 회원 탈퇴
            result = userDAO.deleteByObject(user);

        return result;
    }
}
