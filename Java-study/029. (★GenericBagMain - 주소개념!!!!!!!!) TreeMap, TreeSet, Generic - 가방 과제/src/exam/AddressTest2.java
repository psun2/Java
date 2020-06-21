package exam;

class AAA2 {

	String name;

	public AAA2() {
		System.out.println("AAA 생성");
	}

	void ppp() {
		System.out.println("AAA 의 ppp 메소드 실행");
	}
}

class BBB2 extends AAA2 {
	public BBB2() {
		System.out.println("BBB 생성");
	}

	void ppp() {
		System.out.println("BBB 의 ppp 메소드 실행");
	}

	void mb() {
		System.out.println("BBB 의 mb 메소드 실행");
	}
}

public class AddressTest2 {

	static void 주소확인(AAA2 a) {

		System.out.println(a.name);

		a.name = "park"; // 주소 교체 전

//		((BBB) a).mb(); // Casting 즉 형변환 할 수 없음

		System.out.println(a.name);

		BBB2 b = new BBB2();

		b.mb();

		System.out.println("메소드 내부에서의 주소 a : " + a);
		System.out.println("메소드 내부에서의 주소 b : " + b);

		a = b;

		a.name = "asdkjalksdjklasjd"; // 주소 교체후 바꾼 name 은 main 에 있는 name 을 건드리지 않습니다.

//		a.mb(); // Error
		((BBB2) a).mb();

		System.out.println("메소드 내부에서 주소 변경후 주소 a : " + a);

	}

	static AAA2 returnAddress(AAA2 a) {

		return a;

	}

	static AAA2 returnAddress2(AAA2 a) {

		BBB2 b = new BBB2();

		a = b;

		return a;

	}

	public static void main(String[] args) {

		AAA2 a = new AAA2();

		System.out.println("생성시 주소 확인 a : " + a);

		a.ppp();

		주소확인(a);

		System.out.println("메소드 반환 후 a : " + a);

		System.out.println(a.name);

		AAA2 b = returnAddress(a);

		System.out.println();
		System.out.println("------------------------------------");
		System.out.println();
		System.out.println(a);
		System.out.println(b);

		System.out.println();
		System.out.println("------------------------------------");
		AAA2 c = returnAddress2(a);
		System.out.println();
		System.out.println(a);
		System.out.println(c);
	}

}
