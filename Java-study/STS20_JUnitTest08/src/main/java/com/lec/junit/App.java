package com.lec.junit;

public class App {
	// 실습 1
	public int add(int a, int b) {
		return a + b;
	}

	// 실습 2
	public boolean comp(String a, String b) {
		if (a.equals(b)) {
			return true;
		}
		return false;
	}

	// 실습3
	public int toNumber(String num) {
		return Integer.parseInt(num);
	}

	// 실습4
	public String toDigit(String str) {

		StringBuffer result = new StringBuffer();

		result.append(str.replaceAll("[^0-9]", ""));

		return result.toString();
	}

}
