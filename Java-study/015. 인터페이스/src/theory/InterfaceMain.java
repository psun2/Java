package theory;

interface In1 {

//	public void meth_1() {}; // 구현부가 있을시 에러

	public void meth_1();

	abstract void meth_2(); // abstract 키워드를 붙여도 상관 없습니다.

	int a = 10, c = 30, d = 40;

}

interface In2 {

	public final static int b = 2000;
	// final 과 static 은 순서가 상관 없습니다.
	// 어디서든 접근이 가능하기 때문에 public 접근 제어자의 권한을 받습니다.

	int c = 3000, d = 4000;

	void meth_1();

	void meth_3();

}

class Impl implements In1, In2 {

//	a = 1234; // Error
	// interface 의 맴버변수는 final (상수) 이면서 final 입니다.

	int d = 4567; // 상속받아 부모의 D를 hiding 합니다.
	int cc = In1.c; // 누구의 C 인지 명시적으로 알려줍니다.
	// 상수가 겹칠 경우 인터페이스를 명시적으로 표기

	@Override
	public void meth_1() {
		System.out.println("Impl meth_1()" + a);

	}

	@Override
	public void meth_2() {
		System.out.println("Impl meth_2()");

	}

	@Override
	public void meth_3() {
		System.out.println("Imp2 meth_3()");

	}

}

public class InterfaceMain {

	public static void main(String[] args) {

		In1 in1 = new In1() {
			@Override
			public void meth_1() {
				System.out.println("In1 생성시 meth_1()");
			}

			@Override
			public void meth_2() {
				System.out.println("In1 생성시 meth_2()");
			}
		};

		in1.meth_1();
		in1.meth_2();

		In2 in2 = new In2() {
			@Override
			public void meth_1() {
				System.out.println("In2 생성시 meth_1()");
			}

			@Override
			public void meth_3() {
				System.out.println("In2 생성시 meth_3()");
			}
		};

		in2.meth_1();
		in2.meth_3();

		Impl imp = new Impl();
		imp.meth_1();
		imp.meth_2();
		imp.meth_3();

		System.out.println("In1-----------------------");

		In1 imp1 = imp;
		imp1.meth_1();
		imp1.meth_2();
		// Impl 클래스에서 meth_3 까지 정의를 했으나,
		// In1 type 즉 부모에서 가지고 있지 않은 메소드 이므로 사용이 불가능 합니다.

		System.out.println("In2-----------------------");

		In2 imp2 = imp;
		imp2.meth_1();
		imp2.meth_3();
//		imp2.meth_2();

		System.out.println("상수-----------------------");

//		In1.a = 11111; // Error
//		in1.a = 11111; // Error
//		imp.a = 11111; // Error
//		imp1.a = 11111; // Error
		// interface 에 있는 변수는 static 이면서 final (상수)
		// 이기때문에 변경이 불가능 합니다.

		System.out.println("In1.a : " + In1.a);
		System.out.println("in1.a : " + in1.a);
		System.out.println("imp.a : " + imp.a);
		System.out.println("imp1.a : " + imp1.a);
//		System.out.println("imp2.a : " + imp2.a);
		// interface 에 있는 변수는 static 입니다.

//		System.out.println("In1.b : " + In1.b);
//		System.out.println("in1.b : " + in1.b);
		System.out.println("in2.b : " + in2.b);
		System.out.println("imp.b : " + imp.b);
//		System.out.println("imp1.b : " + imp1.b);
		System.out.println("imp2.b : " + imp2.b);

		System.out.println("In1.c : " + In1.c);
		System.out.println("in1.c : " + in1.c);
//		System.out.println("imp.c : " + imp.c);
		// 클래스에서는 누구의 C 인지 몰라서 접근하지 못 합니다.
		System.out.println("imp1.c : " + imp1.c);
		System.out.println("imp2.c : " + imp2.c);
		System.out.println("In1.c : " + In1.c);
		System.out.println("in1.c : " + in1.c);
		System.out.println("in2.c : " + in2.c);
//		System.out.println("imp.c : " + imp.c);
		// 클래스에서는 누구의 C 인지 몰라서 접근하지 못 합니다.
		System.out.println("imp1.c : " + imp1.c);
		System.out.println("imp2.c : " + imp2.c);

		System.out.println("In1.d : " + In1.d);
		System.out.println("in1.d : " + in1.d);
		System.out.println("imp.d : " + imp.d);
		// 클래스에서 정의한 D 출력 (클래스 내부의 상수가 아닌 변수)
		System.out.println("imp1.d : " + imp1.d);
		System.out.println("imp2.d : " + imp2.d);
		System.out.println("In1.d : " + In1.d);
		System.out.println("in1.d : " + in1.d);
		System.out.println("in2.d : " + in2.d);
		System.out.println("imp.d : " + imp.d);
		// 클래스에서 정의한 D 출력 (클래스 내부의 상수가 아닌 변수)
		System.out.println("imp1.d : " + imp1.d);
		System.out.println("imp2.d : " + imp2.d);

//		System.out.println("In1.cc : " + In1.cc);
//		System.out.println("in1.cc : " + in1.cc);
//		System.out.println("imp1.cc : " + imp1.cc);
//		System.out.println("In2.cc : " + In2.cc);
//		System.out.println("imp2.cc : " + imp2.cc);
		// 클래스 소속의 cc 이므로 접근 이 제한됩니다.
		System.out.println("imp.cc : " + imp.cc);
	}

}