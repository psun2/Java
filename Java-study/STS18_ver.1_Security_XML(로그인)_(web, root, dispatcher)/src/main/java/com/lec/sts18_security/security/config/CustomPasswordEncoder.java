package com.lec.sts18_security.security.config;

import com.sun.xml.internal.bind.api.impl.NameConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder {
    // 주어진 rawPassword 를 인코딩하여 리턴, 일반적으로 SHA-1 혹은 그 이상의 알고리즘 활용
    @Override
    public String encode(CharSequence rawPassword) {

        log.info("encode 전 : {}", rawPassword);
        log.info("encode 후 : {}", sha512(rawPassword.toString()));

        return sha512(rawPassword.toString());
    }

    // 주어진 rawPassword 가 인코딩된 비번(encodedPassword) 와 동일한지 판정
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        log.info("rawPassword: {}, encodedPassword: {}", rawPassword, encodedPassword);

        log.info("marches 수행 : {} :: {}", sha512(rawPassword.toString()), sha512(encodedPassword));

        return sha512(rawPassword.toString()).equals(sha512(encodedPassword));
    }

    public String sha512(String password) {
        String parsingPassword = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            parsingPassword = String.format("%0128x", new BigInteger(digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return parsingPassword;
    }
}
