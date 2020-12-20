package com.lec.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class PasswordEncoding implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
      log.info("비밀번호 : {}", rawPassword.toString());
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        log.info("rawPassword(사용자 입력) : {}, encodedPassword(DB) : {}", rawPassword, encodedPassword);

        log.info("비밀번호일치여부 : {}", rawPassword.toString().equals(encodedPassword));

        return rawPassword.toString().equals(encodedPassword);
    }
}
