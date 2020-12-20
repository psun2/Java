package com.lec.security.detailsevise;

import com.lec.security.domain.AuthenticationDAO;
import com.lec.security.domain.UserDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private UserDAO userDAO;

    private AuthenticationDAO authenticationDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 메서드 UserDetails loadUserByUsername(String username)가 유효한 UserDetail개체를 반환 하는지 확인하십시오 .
        // 반환 된 개체가 null / 잘못된 값이있는 개체 인 경우에도이 오류가 표시됩니다.

        return new CustomUserDetails(username, userDAO, authenticationDAO);
    }

}
