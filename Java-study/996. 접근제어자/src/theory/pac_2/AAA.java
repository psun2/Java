package theory.pac_2;

//public class BBB{ // public 접근 제어자는 한가지의 java file 에서 한종류의 클래스만 허용 가능
class BBB {
	int a = 30;
	int b = 40;

	void meth_1() {
		System.out.println("pac.pac_2.BBB meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_2.BBB meth_2() 실행");
	}
}

public class AAA { // public 클래스는 java 파일로 만든 클래스만 가능

	public int a = 10;
	int b = 20;

	public void meth_1() {
		System.out.println("pac.pac_2.AAA meth_1() 실행");
	}

	void meth_2() {
		System.out.println("pac.pac_2.AAA meth_2() 실행");
	}
}