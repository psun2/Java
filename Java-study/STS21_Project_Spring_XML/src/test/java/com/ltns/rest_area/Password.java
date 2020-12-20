package com.ltns.rest_area;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class Password {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void 비밀번호() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 4; i++) {
            System.out.println(bCryptPasswordEncoder.encode("0000"));
        }
        String rawPassword = "0000";
        String encodedPassword = bCryptPasswordEncoder.encode("0000");
        log.info("rawPassword : {}, encodedPassword : {}", rawPassword, encodedPassword);
        log.info("비밀번호 일치여부 : {}", bCryptPasswordEncoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void 비밀번호_일치여부() {
        bCryptPasswordEncoder.matches("0000", "$2a$10$.ty2lbI.rSz7bjpmWXRop.S5SZZPGzNQuKmPFDgHscDhjijAPlhai");
    }
}
