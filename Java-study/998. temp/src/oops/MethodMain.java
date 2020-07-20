package oops;

class MeAAA {

	int a = 10; // 멤버변수 = 공유가 주 목적입니다.
	int b = 20;

	// parameter(매개변수) O, return O
	int meth(int i, String str) { // 메소드의 정의
		// 메소드 이름: func
		// 매개변수: int i, String str
		// 리턴형: int

		System.out.println("메소드 실행: " + i + ", " + str);

		return 1234; // 리턴
	}

	// parameter(매개변수) X, return X
	void meth_1() {
		System.out.println("meth_1() 실행: parameter X, return X");
	}

	// parameter(매개변수) O, return X
	void meth_2(int i, int j) {
		System.out.println("meth_2() 실행: parameter (" + i + ", " + j + "), return X");
//		void : return 형을 모르겠다.
//		return 123;
//		return "fuck";
		return;
	}

	// parameter(매개변수) X, return O
	int meth_3() {
		System.out.println("meth_3() 실행: parameter X, return O");
		return 9876; // 리턴형이 있으면 리턴이 반그시 필요합니다.
	}

	void val_1() {
		System.out.println("val_1() 실행: " + a + ", " + b); // 맴버변수 호출 가능
//		System.out.println("val_2() 실행: " + a + ", " + b + ", " + z);
//		 다른 메소드 지역변수(매개변수 포함) 접근 불가
	}

	void val_2(int z) {
		a = z; // 맴버변수 대입
//		int z; // 지역변수와 매개변수 이름 충돌
		int s = 2468; // 지역변수
		System.out.println("val_2() 실행: " + a + ", " + b + ", " + z + ", " + s); // 맴버변수 호출 가능
	}

	void val_3(int b) {
		// b: 매개변수(맴버변수 아님)
		System.out.println("val_3() 실행: " + a + ", " + b + ", " + this.b); // 맴버변수 호출 가능
	}

	void init(int a, int b) {
		this.a = a;
		this.b = b;
		System.out.println("init() 실행: " + a + ", " + b);
	}

//////////////////////////////////////////////////////////////////////////////
	int sum(int a, int b) {
		return a + b;
	}

	int factorial(int num) {
		if (num == 1) {
			return 1;
		} else {
			return num * factorial(num - 1);
		}
	}
}

public class MethodMain {

	public static void main(String[] args) {

		MeAAA ma = new MeAAA();
		System.out.println("ma.a: " + ma.a);
		ma.meth(100, "아기상어"); // 메소드 호출
		int rr = ma.meth(100, "아기상어"); // 메소드 호출
		System.out.println("rr: " + rr);
		ma.meth_1(); // 메소드 호출
//		ma.meth_1(1234); // argument(인수 == 인자값) 를 주면 error
//		int qq = ma.meth_1(1234); // return 이 없으므로 변수선언 불가 error
		ma.meth_2(10, 20); // 메소드 호출
//		ma.meth_2(1); // argument(인수 == 인자값) 의 갯수가 안맞으면 error
//		ma.meth_2(1, "asdkl"); // argument(인수 == 인자값) 의 dataType 이 틀리면 error
		ma.meth_3(); // 메소드 호출
		rr = ma.meth_3(); // 메소드 호출
		System.out.println("rr: " + rr);
		System.out.println("ma.meth_3(): " + ma.meth_3());
		ma.val_1();
		ma.a = 1111;
		ma.b = 2222;
		ma.val_1();
		ma.val_2(123456);
		ma.val_1();
//		System.out.println(z); // z는 MeAAA 클래스의 val_2 함수에 있는 지역 변수입니다.
//		System.out.println(s); // s는 MeAAA 클래스의 val_2 함수에 있는 지역 변수입니다.

		ma.val_3(975);
		ma.val_1();

		ma.init(9988, 5544);
		ma.val_1();

		System.out.println();
		System.out.println("ma.sum(1, 2): " + ma.sum(1, 2));
		System.out.println("ma.factorial(5): " + ma.factorial(5));

	}

}
