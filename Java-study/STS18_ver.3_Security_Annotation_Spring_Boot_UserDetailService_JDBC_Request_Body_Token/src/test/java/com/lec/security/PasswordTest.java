package com.lec.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

    @Test
    void 비밀번호() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = null;
        for(int i = 0; i <= 4; i++) {
            password = bCryptPasswordEncoder.encode("0000");
            System.out.println(password);
            System.out.println(bCryptPasswordEncoder.matches("0000", password));

        }
    }

    @Test
    void 한번더확인() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.matches("0000", "$2a$10$iAyjeXB0HVFJmChG14c8cOBh1eII1loT8Gjx0NWuDHa8.q9AFuDSK"));
    }
}
