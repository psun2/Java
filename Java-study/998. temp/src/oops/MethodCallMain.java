package oops;

class Method {

	int a = 10;

	void meth_1() {
		int b = 200;
		System.out.println("meth_1()시작 " + a + ", " + b);
		meth_2();
		System.out.println("meth_1()끝 " + a + ", " + b);
	}

	void meth_2() {
		int b = 200;
		System.out.println("meth_2()시작 " + a + ", " + b);
		meth_3();
		System.out.println("meth_2()끝 " + a + ", " + b);
	}

	void meth_3() {
		int b = 200;
		System.out.println("meth_3()시작 " + a + ", " + b);
		System.out.println("meth_3()끝 " + a + ", " + b);
	}

}

public class MethodCallMain {

	public static void main(String[] args) {

		Method meth = new Method();
		meth.meth_1();
	}

}
