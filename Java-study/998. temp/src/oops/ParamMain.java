package oops;

import java.util.Arrays;

// ✔ 가변 매개 변수

class ClaParam {

	void meth_1(int a, int b, int c) { // 기본 자료형
		System.out.println("meth_1() " + a + ", " + b + ", " + c);
	}

	void meth_2(int[] arr) { // 배열은 주소로 받습니다.
		System.out.println("meth_2() " + Arrays.toString(arr));
	}

	void meth_3(int... arr) { // 가변매개변수 => 매개변수들을 묶어 배열로 처리후 주소 대입
		// 가변 매개 변수 => parameter 을 배열의 형태로 전환하여 받아 옵니다.
		System.out.println("meth_3() " + Arrays.toString(arr));
		System.out.println("meth_3() " + arr);
	}

	void meth_4(int a, int b, int... arr) { // 가변매개변수 => 매개변수들을 묶어 배열로 처리후 주소 대입
		// 가변 매개 변수 => parameter 을 배열의 형태로 전환하여 받아 옵니다.
		System.out.println("meth_4() " + a + ", " + b + ", " + Arrays.toString(arr));
		System.out.println("meth_4() " + a + ", " + b + ", " + arr);
	}

	// arr -> 이후의 모든 변수를 배열로 묶음
	// 따라서 a 의 값이 할당 되지 않음.
//	void meth_5(int... arr, int a) {
//		// 가변매개 변수는 모든 수를 받아오기때문에 a 가 존재 할수 가 없음으로, ... 앞으로 옴겨주면 error 해결
//		System.out.println("meth_5() " + Arrays.toString(arr));
//		System.out.println("meth_5() " + arr);
//	}

	// 자료형이 달라도 위치의 문제는 변화없음.
	// void meth_6(int... arr, String a) {
//		// 가변매개 변수는 모든 수를 받아오기때문에 a 가 존재 할수 가 없음으로, ... 앞으로 옴겨주면 error 해결
//		System.out.println("meth_6() " + Arrays.toString(arr));
//		System.out.println("meth_6() " + arr);
//	}

	void meth_7(int... arr) {
		if (arr.length == 1)
			System.out.println("hi");
		else
			System.out.println("hello");

	}

////////////////////////////////////////////////////////////////////////////////
//	void exam(int... arr, String name) {
//		// 스프레드 문법 사용시 parameter의 맨 마지막 인자로 받 아야 합니다.
//		System.out.println("meth_3()" + Arrays.toString(arr));
//		System.out.println("meth_3()" + arr);
//	}

//	Multiple markers at this line
//	- Method breakpoint:ClaParam [entry] - meth_4(int[], String)
//	- The variable argument type int of the method meth_4 must be the last 
//
//	이 라인의 여러 마커
//	- 방법 중단점:ClaParam [entry] - method_4(int[], String)
//	- method_4의 변수 인수 유형이 마지막이어야 함

	void exam_2(String name, int... arr) {
		// 스프레드 문법 사용시 parameter의 맨 마지막 인자로 받 아야 합니다.
		System.out.println("meth_3()" + Arrays.toString(arr));
		System.out.println("meth_3()" + arr);
	}

//	void exam_3(int... arr, String... name) {
//		// 스프레드 문법은 동시에 사용할 수 없습니다.
//		System.out.println("meth_3()" + Arrays.toString(arr));
//		System.out.println("meth_3()" + arr);
//	}
//////////////////////////////////////////////////////////////////////////////

}

public class ParamMain {

	public static void main(String[] args) {

		// ✔ 가변 매개 변수

		ClaParam cp = new ClaParam();

		cp.meth_1(10, 20, 30); // meth_1()10, 20, 30
		cp.meth_2(new int[] { 10, 20, 30 }); // meth_2()[10, 20, 30]

		cp.meth_3(10, 20, 30);
//		meth_3()[10, 20, 30]
//		meth_3()[I@15db9742

//		✔ 가변매개 변수는 변수의 갯수와 상관없고 자료형이 같은 모든 매개 변수를 받아와 배열화 합니다.

		cp.meth_3(new int[] { 10, 20, 30 });
//		meth_3()[10, 20, 30]
//		meth_3()[I@6d06d69c

		cp.meth_3();
//		meth_3()[]
//		meth_3()[I@7852e922

		cp.meth_3(1);
//		meth_3()[1]
//		meth_3()[I@4e25154f

		System.out.println("-----------------------------------------------");

		cp.meth_4(12, 34, 56, 78, 90);
//		meth_4() 12, 34, [56, 78, 90]
//		meth_4() 12, 34, [I@70dea4e

		cp.meth_4(12, 34, 56, 78, 90);
		// a = 12, b = 34, c = [56, 78, 90]
		cp.meth_4(12, 34, 56, 78);
		cp.meth_4(12, 34, 56);
		cp.meth_4(12, 34);
//		cp.meth_4(12); // b의 값이 없어서 error 
		// a = 12, b = ?, c = []

		cp.meth_7(1); // hi
		cp.meth_7(1, 2); // hello

	}

}
