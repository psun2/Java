package dynamic_P;

// title : 상속 (extends)

class Grand {

	int gg = 100;

	void meth_g() {
		System.out.println("할아버지 meth_g 실행");
	}
}

class InPar extends Grand {
	int a = 10;
	int b = 20;
	int gg = 1234;
	int g_gg = super.gg;

	void meth_1() {
		System.out.println("부모 meth_1() 실행");
	}

	void meth_2() {
		System.out.println("부모 meth_2() 실행");
	}

	void meth_g() {
		System.out.println("부모 meth_g 실행");
	}
}

class InUncle {

}

//class InChild {
class InChild extends InPar {
//class InChild extends InPar, InUncle { // 다중상속 불가능 // 부모는 하나만 가능
	int c = 300000;
	int a = 100000; // hiding
	int p_a = super.a; // 내껀 this 면 .... 부모는 super ...
	int g_gg = super.g_gg; // 내껀 this 면 .... 부모는 super ...

	void meth_1() { // overriding
		System.out.println("자식 meth_1() 실행");
	}

	void meth_3() {
		System.out.println("자식 meth_3() 실행");
	}

	void meth_p_1() { // 부모의 메소드 호출
		super.meth_1();
	}
}

class InChild2 extends InPar { // 한부모에 여러 자식들의 상속이 가능합니다.
	int d = 4578458;

	void meth_4() {
		System.out.println("자식2 meth_4() 실행");
	}
}

public class InheritanceMain {

	public static void main(String[] args) {

		InPar pp = new InPar();
		InChild cc = new InChild();
		InChild2 cc2 = new InChild2();

		System.out.println("pp.gg: " + pp.gg);
		System.out.println("pp.a: " + pp.a);
		System.out.println("pp.b: " + pp.b);
//		System.out.println("pp.c: " + pp.c); // 상속전 error // 상속후 부모에 서 자식 접근 불가능

		pp.meth_g();
		pp.meth_1();
		pp.meth_2();
//		pp.meth_3(); // 상속전 error // 상속후 부모에 서 자식 접근 불가능

		System.out.println("------------------------------------------");

		System.out.println("cc.gg: " + cc.gg); // 상속전 error
		System.out.println("cc.g_gg: " + cc.g_gg); // 상속전 error
		System.out.println("cc.a: " + cc.a); // 상속전 error
		System.out.println("cc.p_a: " + cc.p_a); // 상속전 error
		System.out.println("cc.b: " + cc.b); // 상속전 error
		System.out.println("cc.c: " + cc.c);
//		System.out.println("cc.d: " + cc.d); // 직계만 사용가능

		cc.meth_g();
		cc.meth_1(); // 상속전 error
		cc.meth_p_1(); // 상속전 error
		cc.meth_2(); // 상속전 error
		cc.meth_3();
//		cc.meth_4(); // 직계만 가능

		System.out.println("------------------------------------------");

		System.out.println("cc2.a: " + cc2.a); // 상속전 error
		System.out.println("cc2.b: " + cc2.b); // 상속전 error
//		System.out.println("cc2.c: " + cc2.c); // 형제 맴버 변수 사용 불가 직계 만 사용 가능
		System.out.println("cc2.d: " + cc2.d);

		cc2.meth_1(); // 상속전 error
		cc2.meth_2(); // 상속전 error
//		cc2.meth_3(); // 직계만 사용가능
		cc2.meth_4();
	}

}
