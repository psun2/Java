package pac.pac_1;

import pac.pac_2.NePar;

class WePar {
	int a = 10;
	private int b = 20;

	void meth_1() {
		System.out.println("pac1.WePar 부모  meth_1()");
	}

	private void meth_2() {
		System.out.println("pac1.WePar 부모  meth_2()");
	}
}

class WeChild extends WePar {
	int ca = a;
//	int cb = b; // 부모의 private 변수

	void meth_c1() {
		super.meth_1();
//		super.meth_2(); // 부모의 private 메소드
	}
}

class NeChild extends NePar {
	int ca = a;
//	int cb = b; // 부모의 private 변수

	void meth_c1() {
		super.meth_1();
//		super.meth_2(); // 부모의 private 메소드0
	}
}

public class ProtectedMain {

	public static void main(String[] args) {

		WeChild wc = new WeChild();
		NePar np = new NePar();

		System.out.println("wc.a: " + wc.a);
//		System.out.println("wc.b: " + wc.b); // 부모의 private 변수

		wc.meth_1();
//		wc.meth_2(); // 부모의 private 메소드

		System.out.println();
		System.out.println("np.a: " + np.a);
//		System.out.println("np.b: " + np.b); // protected
//		System.out.println("np.c: " + np.c); // default
//		System.out.println("np.d: " + np.d); // private

	}

}
