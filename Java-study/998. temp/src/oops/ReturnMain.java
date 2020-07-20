package oops;

import java.util.Arrays;

class ClaReturn {

	void meth_1() {
		System.out.println("meth_1()실행 ");
		return;
	}

	int meth_2() {
		System.out.println("meth_1()실행 ");
		return 1234; // return 이 명시 되어진 경우 반드시 return 필요

//		return 5678; // return은 1회만 실행

//		return을 만나는 경우 코드가 종료 되고, return 밑에는 어떠한 코드 내용도 들어올 수 없습니다.
	}

	// 2 개 이상의 값을 return 불가
//	int meth_3() {
//	int, int meth_3() {
//	int int meth_3() {
//	(int int) meth_3() {
//		System.out.println("meth_3()실행 ");
//		return 100, 200;
//	}

	int[] meth_4() {
		System.out.println("meth_4() 실행");

		return new int[] { 111, 222, 333 };
		// 1개 밖에 return 되지 않으므로 주소 참조 형태로 여러개 return
		// 배열 or 인스턴스
		// ✔ new int[] 나가기 때문에 메소드 실행시마다 계속 배열이 생성되어, 주소가 다 다르다.
		// ✔ new 로 생성 하는 것들은 heap 으로 저장 된다.
	}

	int meth_5(boolean bo) {
		System.out.println("meth_5() 실행 1------------------: " + bo);

		if (bo) // 조건문 등을 이용하여 조건별 리턴 가능
			return 3333;

		System.out.println("meth_5() 실행 2: ");
		System.out.println("meth_5() 실행 3------------------");
		return 9999;
	}

}

public class ReturnMain {

	public static void main(String[] args) {

		ClaReturn ct = new ClaReturn();

		ct.meth_1();
//		int r1 = ct.meth_1(); // 리턴형이 없는 메소드에서 값을 대입받을수 없음

		int r2 = ct.meth_2();
		System.out.println("r2: " + r2);
		System.out.println("ct.meth_2(): " + ct.meth_2());
		// 메소드 내부에서 호출하여 값으로 이용가능
		// 단 메소드 내부의 호출을 먼저 실행
		// 이후 메소드에서 값의 형태로 사용

		System.out.println("---------------------------------------------------");
		int[] arr1 = ct.meth_4();
		System.out.println("---------------------------------------------------");
		System.out.println("arr1: " + Arrays.toString(arr1));
		arr1[1] = 2468;
		System.out.println("---------------------------------------------------");
		System.out.println("arr1: " + Arrays.toString(arr1));
		System.out.println("---------------------------------------------------");
		System.out.println("ct.meth_4() 1번: " + Arrays.toString(ct.meth_4()));
		ct.meth_4()[1] = 2468;
		System.out.println("ct.meth_4() 2번: " + Arrays.toString(ct.meth_4()));

		System.out.println("ct.meth_5(): " + ct.meth_5(true));
//		meth_5() 실행
//		ct.meth_5(): 9999
		System.out.println("ct.meth_5(): " + ct.meth_5(false));
//		meth_5() 실행
//		ct.meth_5(): 9999
	}

}
