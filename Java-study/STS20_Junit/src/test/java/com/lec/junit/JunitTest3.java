package com.lec.junit;

/* JUnit5 의 기본 애노테이션

@Test
    테스트를 진행하고자 하는 메소드를 구현할 때 사용한다.
@BeforeAll
    @Test를 모두 실행하기 전에 딱 한번만 호출 되는 것이다.
@AftereAll
    @Test를 모두 실행한 후에 딱 한번만 호출 되는것이다.
@BeforeEach
    @Test를 모두 실행할 때 각각의 @Test가 실행되기 전에 호출되는 것이다.
@AfterEach
    @Test를 모두 실행할 때 각각의 @Test가 실행된 후에 호출되는 것이다.
@Disabled
    테스트를 진행하지 않은 @Test가 있을 시 해당 애노테이션을 붙이면 테스트를 진행하지 않을 수 있다.
    현재 운영에 사용되어지지는 않은데 추후 사용될 수도 있으니! 지우지 않고 냅두는 상황이 매우 많다.

*/

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class JunitTest3 {

    @Test
    void test() {
        // expected: 기대 값
        // actual: 실제 값
        // assertEquals(expected, actual);

        assertEquals(10 * 10, 100);
        assertEquals(100, 90 + 10);
        // assertEquals(102, 1000 / 10); // org.opentest4j.AssertionFailedError:
        // expected: <102> but was: <100>
        assertEquals(100, 101 - 1);
        assertEquals("Hello", "Hell" + "o");

        assertNotNull(new int[10]); // null 이 아니므로 통과
        assertNull(null); // 매개변수가 null 일시 통과

        assertArrayEquals(new int[]{10, 20, 30}, new int[]{10, 10 * 2, 10 * 2 + 10});

        assertTrue(!false);
        assertFalse(!true);

        int a = 3;
        if (a % 2 != 0)
            fail("짝수가 아닐시 실패");

        log.info("create1");
    }

    @Test
    void test2() {
        assertAll(
                () -> assertNotNull(new Integer(100)),
                () -> assertEquals(5 * 2, 10),
                () -> assertTrue(true)
        );
    }
}
