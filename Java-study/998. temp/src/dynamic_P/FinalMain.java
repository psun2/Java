package dynamic_P;

class FiPar {
	int a = 10;
	final int b = 20;
	static int c = 30;
	final static int d = 40;

	final int e; // 값이 없을시 error
//	final int f; // 초기화 되지 않은 상수 선언 불가

	public FiPar() {
		a = 12;
//		b = 23; // final (상수화 변경 불가)
		c = 34;
//		d = 45; // final (상수화 변경 불가)
		e = 67; // 초기화 되지 않은 상수 생성시 초기화 가능
	}

	void meth_1() {
		System.out.println("부모 meth_1()");
	}

	final void meth_2() {
		System.out.println("부모 meth_2()");
	}

	static void meth_3() {
		System.out.println("부모 meth_3()");
	}

	static final void meth_4() {
		System.out.println("부모 meth_4()");
	}

	final static void meth_5() {
		System.out.println("부모 meth_5()");
	}
}

class FiChild extends FiPar {

	int a = 99;
	final int b = 88;
	static int c = 77;
	final static int d = 66;

	void meth_1() {
		System.out.println("자식 meth_1()");
	}

//	final void meth_2() { // final 오버라이딩 불가
//		System.out.println("자식 meth_2()");
//	}

	static void meth_3() {
		System.out.println("자식 meth_3()");
	}

//	static final void meth_4() { // final 오버라이딩 불가
//		System.out.println("자식 meth_4()");
//	}

//	final static void meth_5() { // final 오버라이딩 불가
//		System.out.println("자식 meth_5()");
//	}

}

public class FinalMain {
	public static void main(String[] args) {

		System.out.println("FiPar.c : " + FiPar.c);
		System.out.println("FiPar.d : " + FiPar.d);

		FiPar.c = 3333;
//		FiPar.d = 3333; // final (상수화 변경 불가)

		System.out.println("FiPar.c : " + FiPar.c);
		System.out.println("FiPar.d : " + FiPar.d);

//		FiPar.meth_1();
//		FiPar.meth_2();
		FiPar.meth_3();
		FiPar.meth_4();
		FiPar.meth_5();

		FiPar pp = new FiPar();
		System.out.println("pp.a : " + pp.a);
		System.out.println("pp.b : " + pp.b);
		System.out.println("pp.c : " + pp.c);
		System.out.println("pp.d : " + pp.d);

		pp.a = 1111;
//		pp.b = 2222; // final (상수화 변경 불가)

		System.out.println("pp.a : " + pp.a);
		System.out.println("pp.b : " + pp.b);
		System.out.println("pp.c : " + pp.c);
		System.out.println("pp.d : " + pp.d);

		pp.meth_1();
		pp.meth_2();
		pp.meth_3();
		pp.meth_4();
		pp.meth_5();

		System.out.println();

		FiChild cc = new FiChild();
		System.out.println("cc.a : " + cc.a);
		System.out.println("cc.b : " + cc.b);
		System.out.println("cc.c : " + cc.c);
		System.out.println("cc.d : " + cc.d);

		cc.a = 1234;
//		cc.b = 3456; // final (상수화 변경 불가)

		System.out.println("cc.a : " + cc.a);
		System.out.println("cc.b : " + cc.b);
		System.out.println("cc.c : " + cc.c);
		System.out.println("cc.d : " + cc.d);

		cc.meth_1(); //자식
		cc.meth_2(); // 부모
		cc.meth_3(); // 자식
		cc.meth_4(); // 부모
		cc.meth_5(); // 부모

	}
}
