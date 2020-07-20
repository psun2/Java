package pac.pac_2;

//public class BBB { // 한 자바파일 안에서 public은 하나만 가능 합니다.
class BBB { // 한 자바파일 안에서 public은 하나만 가능 합니다.
	public int a = 30;
	int b = 40;

	void meth_1() {
		System.out.println("pac.pac_2.BBB meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_2.BBB meth_2() 실행");
	}
}

public class AAA { // public => 다른 package 에서 접근 가능

	public int a = 10;
	int b = 20;

	public void meth_1() {
		System.out.println("pac.pac_2.AAA meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_2.AAA meth_2() 실행");
	}

}
