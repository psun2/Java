package theory.pac_1;

import theory.pac_2.NePar;

class WePar {
	public int a = 10;
	protected int b = 20;
	int c = 20;
	private int d = 20;

	public void meth_1() {
		System.out.println("pac1.WePar meth_1()");
	}

	protected void meth_2() {
		System.out.println("pac1.WePar meth_2()");
	}

	void meth_3() {
		System.out.println("pac1.WePar meth_3()");
	}

	private void meth_4() {
		System.out.println("pac1.WePar meth_4()");
	}
}

class WeChild extends WePar {
	// 같은 패키지 내의 클래스를 상속 받음.
	int ca = a;
	int cb = b;
	int cc = c;
//	int cd = d; // private 접근 불가

	void meth_test() {
		meth_1();
		meth_2();
		meth_3();
//		meth_4(); // private 접근 불가
	}
}

class NeChild extends NePar {
	// 다른 패키지의 public 클래스를 상속

	int ca = a;
	int cb = b;
//	int cc = c; // default 접근 불가
//	int cd = d; // private 접근 불가

	void meth_test() {
		meth_1();
		meth_2();
//		meth_3(); // default 접근 불가
//		meth_4(); // private 접근 불가
	}

	public int getB() {
		return this.b;
	}
}

public class ProtectedMain {

	public static void main(String[] args) {

		WePar wp = new WePar();

		System.out.println("wp.a : " + wp.a);
		// 같은 패키지의 public 접근 가능
		System.out.println("wp.b : " + wp.b);
		// 같은 패키지의 protected 접근 가능
		System.out.println("wp.c : " + wp.c);
		// 같은 패키지의 default 접근 가능
//		System.out.println("wp.d : " + wp.d);
		// 패키지가 같더라도 클래스가 다르면 private 접근 불가능

		wp.meth_1(); // 같은 패키지의 public 접근 가능
		wp.meth_2(); // 같은 패키지의 protected 접근 가능
		wp.meth_3(); // 같은 패키지의 default 접근 가능
//		wp.meth_4(); // 패키지가 같더라도 클래스가 다르면 private 접근 불가능

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////

		WeChild wc = new WeChild();

		System.out.println("wc.a : " + wc.a);
		System.out.println("wc.ca : " + wc.ca);
		// 같은 패키지의 class를 상속 받는 부모 public 접근 가능
		System.out.println("wc.b : " + wc.b);
		System.out.println("wc.cb : " + wc.cb);
		// 같은 패키지의 class를 상속 받는 같은 패키지의 protected 접근 가능
		System.out.println("wc.c : " + wc.c);
		System.out.println("wc.cc : " + wc.cc);
		// 같은 패키지의 class를 상속 받는 같은 패키지의 default 접근 가능
//		System.out.println("wc.d : " + wc.d);
//		System.out.println("wc.cd : " + wc.cd);
		// 패키지가 같고 상속을 받더라도 같은 클래스의 내부가 아니면 private 접근 불가

		wc.meth_1(); // 같은 패키지의 class를 상속 받는 부모 public 접근 가능
		wc.meth_2(); // 같은 패키지의 class를 상속 받는 같은 패키지의 protected 접근 가능
		wc.meth_3(); // 같은 패키지의 class를 상속 받는 같은 패키지의 default 접근 가능
//		wc.meth_4(); // 패키지가 같고 상속을 받더라도 같은 클래스의 내부가 아니면 private 접근 불가
		wc.meth_test();

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////
		NeChild nc = new NeChild();

		System.out.println("nc.a : " + nc.a);
		System.out.println("nc.ca : " + nc.ca);
		// 다른 패키지의 public 은 직, 간접적 접근 가능

//		System.out.println("nc.b : " + nc.b);
		System.out.println("nc.cb : " + nc.cb);
		System.out.println("nc.getB() : " + nc.getB());
		// 다른 패키지의 protected 는 직접적으로 접근은 불가능 하지만,
		// 간접적으로 우회해서 접근은 가능
		// 하지만 getter을 사용하여 메소드로 반환된 값을 받는 다면 상속 받는 클래스 내부에서
		// 처리하기 때문에 상속받는 클래스 내부에서는 직접적으로 접근 가능

//		System.out.println("nc.c : " + nc.c);
//		System.out.println("nc.cc : " + nc.cc);
		// 다른 패키지의 default 는 직, 간 접적으로 접근 불가

//		System.out.println("nc.d : " + nc.d);
//		System.out.println("nc.cd : " + nc.cd);
		// 다른 패키지의 private 는 직, 간 접적으로 접근 불가

		nc.meth_1(); // 다른 패키지의 public 은 직, 간접적 접근 가능
//		nc.meth_2();
		// 다른 패키지의 protected 는 직접적으로 접근은 불가능 하지만,
		// 간접적으로 우회해서 접근은 가능
		// 하지만 getter을 사용하여 메소드로 반환된 값을 받는 다면 상속 받는 클래스 내부에서
		// 처리하기 때문에 상속받는 클래스 내부에서는 직접적으로 접근 가능
//		nc.meth_3(); // 다른 패키지의 default 는 직, 간 접적으로 접근 불가
//		nc.meth_4(); // 다른 패키지의 private 는 직, 간 접적으로 접근 불가
		nc.meth_test();

	}

}
