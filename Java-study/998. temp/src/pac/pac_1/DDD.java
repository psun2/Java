package pac.pac_1;

class DDD {

	int a = 7000;
	int b = 8000;

	void meth_1() {
		System.out.println("pac.pac_1.DDD meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_1.DDD meth_2() 실행");
	}

}

class EEE {

	int a = 9000;
	private int b = 10000; // 자신의 클래스 내부에서만 접근이 가능하다. => ppp 참조

	void meth_1() {
		System.out.println("pac.pac_1.EEE meth_1() 실행");
	}

	private void meth_2() {
		System.out.println("pac.pac_1.EEE meth_2() 실행");
	}

	void ppp() {
		System.out.println("EEE.ppp().b: " + b);
		meth_2();
		System.out.println("EEE.ppp() 끝");
	}

}

class GGG {

	public int a = 123456;
	int b = 6784568;

	public void meth_1() {
		System.out.println("pac.pac_1.GGG meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_1.GGG meth_2() 실행");
	}

}
