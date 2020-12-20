package com.ltns.rest_area.validator.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

@Slf4j
public class UserValidationTest {

    private String regex = null;

    @Test
    public void 이메일_정규식() {
        regex = "[a-zA-Z0-9]{1,}\\@[a-zA-Z]{1,}\\.[a-zA-Z]{1,}";
        log.info("regex : {}", regex);
        Assert.assertTrue(Pattern.matches(regex, "tjddjs90@naver.com")); // 정상 case
        Assert.assertFalse(Pattern.matches(regex, "tjddjs90  @  naver.com")); // 공백 허용 x
        Assert.assertFalse(Pattern.matches(regex, "tjddjs90naver.com")); // @ 없음 허용 x
        Assert.assertFalse(Pattern.matches(regex, "@naver.com")); // @앞자리에 없음 허용 x
        Assert.assertFalse(Pattern.matches(regex, "tjddjs90@.com")); // @ 와 . 사이의 문자 없음 허용 x
        Assert.assertFalse(Pattern.matches(regex, "tjddjs90@com")); // .없음 허용 x
        Assert.assertFalse(Pattern.matches(regex, "tjddjs90@naver")); // . 과 . 이하의 문자없음 허용 x
    }

    @Test
    public void 비밀번호_정규식() {
        regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
        Assert.assertTrue(Pattern.matches(regex, "!Qq12345678")); // 정상 case
        Assert.assertFalse(Pattern.matches(regex, "!Qq1234")); // 길이 모자름 허용 x
        Assert.assertFalse(Pattern.matches(regex, "!!@#$%^&%$")); // 특수만자로만 이루어짐 허용 x
        Assert.assertFalse(Pattern.matches(regex, "AAAAAAAA")); // 대문자로만 이루어짐 허용 x
        Assert.assertFalse(Pattern.matches(regex, "ssssssss")); // 소문자로만 이루어짐 허용 x
        Assert.assertFalse(Pattern.matches(regex, "123456789")); // 숫자로만 이루어짐 허용 x
        Assert.assertFalse(Pattern.matches(regex, "!@Qw1234567894561230123654")); // 패턴은 맞으니 길이 초과 허용 x
    }

    @Test
    public void 닉네임_정규식() {
        regex = "[a-zA-Z0-9ㄱ-힣]{2,8}";
        Assert.assertTrue(Pattern.matches(regex, "한국다람쥐")); // 정상 case
        Assert.assertTrue(Pattern.matches(regex, "asdsdh")); // 정상 case
        Assert.assertTrue(Pattern.matches(regex, "456456")); // 정상 case
        Assert.assertTrue(Pattern.matches(regex, "Aa2")); // 정상 case
        Assert.assertFalse(Pattern.matches(regex, "Aa2ddddddddddd")); // 길이 초과 허용 x
        Assert.assertFalse(Pattern.matches(regex, "Aa2!")); // 특수문자 입력 허용 x
    }
}
