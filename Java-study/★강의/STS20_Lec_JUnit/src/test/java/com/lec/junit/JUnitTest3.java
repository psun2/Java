package com.lec.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnitTest3 {

	@Test
	void test1() {
		assertEquals(100, 10 * 10);
		assertEquals(100, 90 + 10);
//		assertEquals(102, 1000 / 10);
		assertEquals(100, 101 - 1);
		assertEquals("Hello", "Hell" + "o");
		
		assertNotNull(new int[10]);
		assertNull(null);
		
		assertArrayEquals(new int[] {10,20,30}, new int[] {10, 10 * 2, 10 * 3});
		
		assertTrue(! false);
		assertFalse(! true);
		
		int a = 3;
		if(a % 2 != 0)
			fail("짝수가 아니라서 실패");
		
	}
	
	@Test
	void test2() {
		assertAll(
				() -> assertNotNull(new Integer(100)),
				() -> assertEquals(10, 5 * 2),
				() -> assertTrue(true)
				);
	}

}













