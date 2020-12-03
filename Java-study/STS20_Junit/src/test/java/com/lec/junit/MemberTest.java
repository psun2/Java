package com.lec.junit;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberTest {

    @Test
    @DisplayName("회원 생성") // test 에 대한 이름 부여
    void createNew() {

        // 예외가 발생해야 성공
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Member(-1));

        log.error(illegalArgumentException.getMessage());
        assertEquals("나이는 0 보다 커야 합니다.", illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("회원 생성 시간") // test 에 대한 이름 부여
    void createNewTime() {
    assertTimeout(Duration.ofMillis(100), () -> {
        for (int i = 0; i < 10; i++) {
            new Member(i);
            Thread.sleep(100);
        }
    });
    }

    @Test
    @DisplayName("AssertJ 사용") // test 에 대한 이름 부여
    void test() {
        Member child = new Member(3); // 0, 15 에러

        Assertions.assertThat(child.getAge())
                .isGreaterThan(0) // 0보다 큰가? (0 초과)
                .isLessThan(15); // 15보다 작은가 ? (15 미만)
    }

}