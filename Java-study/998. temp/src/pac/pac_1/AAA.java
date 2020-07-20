package pac.pac_1;

public class AAA { // public => 다른 package 에서 접근 가능

	public int a = 123482;
	int b = 78415545;

	public void meth_1() {
		System.out.println("pac.pac_2.AAA meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_2.AAA meth_2() 실행");
	}

}