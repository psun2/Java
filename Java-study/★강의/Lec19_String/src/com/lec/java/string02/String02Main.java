package com.lec.java.string02;

public class String02Main {

	public static void main(String[] args) {
		System.out.println("String 생성자, literal(상수)");

		int num1 = 1;
		int num2 = 1;
		
		if(num1 == num2) {
			System.out.println("같은숫자");
		} else {
			System.out.println("다른숫자");
		}
		
		System.out.println();
		System.out.println("[1] String literal");
		
		String str1 = "Hello";
		String str2 = "Hello";
		System.out.printf("str1=%s str2=%s\n", str1, str2);
		if(str1 == str2) { // 문자열 비교가 아니라 동일한 곳(주소) 비교
			System.out.println("참");  // ◀  
		} else {
			System.out.println("거짓");
		}
		
		// String 은 immutable 객체다!
		// immutable : 안의 내용을 변경 불가!
		//  String 의 모든 메소드는 원본객체를 변경하지 않는다. (새로 생성한다)
		str2 = "He".concat("llo");
		System.out.printf("str1=%s str2=%s\n", str1, str2);
		if(str1 == str2) {
			System.out.println("참");
		} else {
			System.out.println("거짓");  // ◀
		}
		
		System.out.println();
		System.out.println("[2] String 생성자 사용");
		String str3 = new String("Hello");
		String str4 = new String("Hello");
		System.out.printf("str3=%s str4=%s\n", str3, str4);
		if(str3 == str4) {
			System.out.println("== 참");
		} else {
			System.out.println("== 거짓");   // ◀
		}
		
		
		System.out.println();
		if(str3.equals(str4)) {
			System.out.println("equals 같은 문자열"); // ◀
		} else {
			System.out.println("equals 다른 문자열");
		}
		
		
		System.out.println("\n프로그램 종료");
	}

}













