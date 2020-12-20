package com.lec.sts18_security.sha;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class SHA_Tests {

    @Test
    public void SHA256Test() {
        try {

            String password = "비밀번호";

            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.reset();
            instance.update(password.getBytes(StandardCharsets.UTF_8));

            String newPassword = String.format("%064x", new BigInteger(instance.digest()));

            log.info("기존 비밀번호 : {}", password);
            log.info("SHA-256 해시로 파싱된 비밀번호 : {}", newPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
   @Test
    public void SHA512Test() {
        try {

            String password = "비밀번호";

            MessageDigest instance = MessageDigest.getInstance("SHA-512");
            instance.reset();
            instance.update(password.getBytes(StandardCharsets.UTF_8));

            String newPassword = String.format("%0128x", new BigInteger(instance.digest()));

            log.info("기존 비밀번호 : {}", password);
            log.info("SHA-512 해시로 파싱된 비밀번호 : {}", newPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
