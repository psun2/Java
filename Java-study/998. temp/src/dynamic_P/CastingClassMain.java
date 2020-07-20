package dynamic_P;

class CastingClass {
	String name = "부모";
	int a = 10, b = 20;

	void meth_1() {
		System.out.println("부모 meth_1()" + a + ", " + b);
	}

	void meth_2() {
		System.out.println("부모 meth_2()");
	}
}

class CastingChild extends CastingClass {

	String name = "자식";
	int a = 1000, c = 2000;

	void meth_1() {
		System.out.println("자식 meth_1()" + a + ", " + b + ", " + c);
	}

	void meth_2() {
		System.out.println("자식 meth_2()");
	}
}

public class CastingClassMain {

	public static void main(String[] args) {

		CastingClass pp = new CastingClass();

		CastingClass pc = new CastingChild();

		// CastingChild cp = new CastingClass();

		CastingChild cc = new CastingChild();

		System.out.println("pp.name: " + pp.name); // pp.name: 부모
		System.out.println("✔pc.name: " + pc.name); // ✔ pc.name: 부모
		System.out.println("cc.name: " + cc.name); // cc.name: 자식

		pp.meth_1(); // 부모 meth_1()
		pc.meth_1(); // ✔ 자식 meth_1()
		cc.meth_1(); // 자식 meth_1()

		System.out.println(pp instanceof CastingClass);
		System.out.println(pp instanceof CastingChild);

		cc.a = 123;
		System.out.println("cc.a: " + cc.a); // 1000
		System.out.println("cc.c: " + cc.c); // 1000

		CastingClass pcc = cc;
		System.out.println("pcc.a: " + pcc.a); // 10
//		System.out.println("pcc.c: " + pcc.c); // 1000

		CastingChild cpcc = (CastingChild) pcc;
		System.out.println("cpcc.a: " + cpcc.a); // 10
		System.out.println("cpcc.c: " + cpcc.c); // 1000

		System.out.println("instanceof 는 생성기준 ");

		System.out.println(pp instanceof CastingClass); // true
		System.out.println(pp instanceof CastingChild); // false

		System.out.println(cc instanceof CastingClass); // true
		System.out.println(cc instanceof CastingChild); // true

		System.out.println(pcc instanceof CastingClass); // true
		System.out.println(pcc instanceof CastingChild); // true

		System.out.println(cpcc instanceof CastingClass); // true
		System.out.println(cpcc instanceof CastingChild); // true

	}

}
