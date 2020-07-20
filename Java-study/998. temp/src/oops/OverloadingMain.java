package oops;

import java.util.Arrays;

class OverCla {

	int a = 10;

	void meth(int a, String b) {
		System.out.println("meth() 실행 " + a + ", " + b);
	}

	void meth(int a, String b, int c) {
		System.out.println("1. 갯수가 다름: " + a + ", " + b + ", " + c);
	}

	void meth(int a, int b) {
		System.out.println("2. 자료형이 다름: " + a + ", " + b);
	}

	void meth(String b, int a) {
		System.out.println("3. 위치가 다름: " + a + ", " + b);
	}

	void meth(int... is) {
		System.out.println("4. 가변 매개변수도 다름: " + Arrays.toString(is));
	}

	// 매개변수 이름이 다른 것은 Overloading 이 아님
//	void meth(int c, String b) {
//		System.out.println("meth()실행: " + a + ", " + b);
//	}

	// return 과 Overloading은 관련없음
//	int meth(int a, String b) {
//		System.out.println("meth()실행: " + a + ", " + b);
//		return 100;
//	}
}

public class OverloadingMain {

	public static void main(String[] args) {

		OverCla oc = new OverCla();

		oc.meth(10, "aaa");
		oc.meth(10, "aaa", 20);
		oc.meth(10, 20);
		oc.meth("aaa", 20);
		oc.meth(10, 20); // 명시적으로 인수 갯수가 맞는 것이 우선권 => 오버로딩 후이지만, int 형식을 2개 받는 2번 메소드가 실행됩니다.
		oc.meth(1111);
		oc.meth();

	}

}
