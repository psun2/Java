package com.lec.security.config.security.config;

import com.lec.security.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUserDetails customUserDetails = null;
        try {
            customUserDetails = userService.findByUserDetails(username);

            if(customUserDetails == null) {
                throw new UsernameNotFoundException("해당 유저가 존재 하지 않습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("트랜잭션 실패 : {}", e.getMessage());
        }

        return customUserDetails;
    }
}
