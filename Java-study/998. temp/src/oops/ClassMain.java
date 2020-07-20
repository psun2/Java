package oops;

class ClaAAA { // 클래스 정의
	int aa = 10; // 맴버 변수
	String bb = "아기상어";

	void meth_1() { // 메소드
		System.out.println("ClaAAA meth_1() 실행"+ aa + bb);
	}
}

public class ClassMain {

	public static void main(String[] args) {

		ClaAAA ca1; // 인스턴스 변수 선언
		ca1 = new ClaAAA(); // 변수 = 인스턴스 객체 생성
							// 변수 = 객체 주소 대입

		ClaAAA ca2 = new ClaAAA();
		ClaAAA ca3 = ca2;

		ca1.aa = 123;
		ca2.bb = "엄마상어";

		System.out.println("ca1: " + ca1); // ca1: oops.ClaAAA@15db9742
		System.out.println("ca1.aa: " + ca1.aa);
		System.out.println("ca1.bb: " + ca1.bb);
		ca1.meth_1();

		System.out.println("ca2: " + ca2); // ca2: oops.ClaAAA@6d06d69c
		System.out.println("ca2.aa: " + ca2.aa);
		System.out.println("ca2.bb: " + ca2.bb);
		ca2.meth_1();

		System.out.println("ca3: " + ca3); // ca3: oops.ClaAAA@6d06d69c
		System.out.println("ca3.aa: " + ca3.aa);
		System.out.println("ca3.bb: " + ca3.bb);
		ca3.meth_1();

	}

}
