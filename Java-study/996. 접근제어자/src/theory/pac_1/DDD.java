package theory.pac_1;

public class DDD {
	int a = 70000;
	int b = 80000;

	void meth_1() {
		System.out.println("pac.pac_1.DDD meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_1.DDD meth_2() 실행");
	}
}

class EEE {
	int a = 90000;
	private int b = 10000;

	void meth_1() {
		System.out.println("pac.pac_1.EEE meth_1() 실행");
	}

	private void meth_2() {
		System.out.println("pac.pac_1.EEE meth_2() 실행");
	}

	void ppp() {
		System.out.println("EEE.ppp() 시작 b:" + b);
		meth_2();
		System.out.println("EEE.ppp() 끝");
	}

}

class GGG {
	public int a = 12345;
	int b = 67890;

	public void meth_1() {
		System.out.println("pac.pac_1.GGG meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_1.GGG meth_2() 실행");
	}
}