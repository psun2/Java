package com.lec.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Production1Test {

	@Test
	void test() {
		int [][] arr = {
			{40, 10, 20, 30},
			{4, 5, 3, 2, 1, 0},
			{400, 300, 200},
			{50},
			{}
		};
		int [][] expected = {
			{10, 20, 30, 40},
			{0, 1, 2, 3, 4, 5},
			{200, 300, 400},
			{50},
			{}
		};
		
		Production1 p1 = new Production1();
		
		for(int i = 0; i < arr.length; i++) {			
			p1.sortArr(arr[i]);  // arr 정렬
			assertArrayEquals(arr[i], expected[i]);
		}
	}

}
















