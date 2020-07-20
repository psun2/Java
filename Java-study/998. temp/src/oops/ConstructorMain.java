package oops;

class Constructor {

	// 사용자 정의 생성자
	Constructor(int a, int b) { // 생성자 호출은 생성시에만 1회 실행, 맴버 변수의 초기화

		System.out.println("사용자 정의 생성자1");
		this.a = a;
		this.b = b;
	}

	Constructor(int a) { // 생성자 호출은 생성시에만 1회 실행, 맴버 변수의 초기화

//		Constructor(a, 2020);
		this(a, 2020); // 생성자 호출 방법
		System.out.println("사용자 정의 생성자2");
//		this(a, 2020); // error 생성자가 생성전에 작업이 있으면, 생성자 호출은 불가능 합니다
		this.a = a;
//		Constructor(a, 2020);
	}

	// 기본 생성자
	Constructor() { // 생성자 호출은 생성시에만 1회 실행, 맴버 변수의 초기화
		System.out.println("기본 생성자");
		this.a = 5678;
		this.b = 9012;
	}

//	void Constructor() { // 생성자와 혼동할 수 있으므로 생성자와 같은 이름의 메소드는 정의하지 않는다.
//		System.out.println("이것은 메소드임");
//	}

	int a = initA();
	static int b = initB();

	int initA() {
		System.out.println("initA()");
		// Constructor(); // 생성자는 호출 불가능
		return 10;
	}

	static int initB() {
		System.out.println("initB()");
		return 2000;
	}

	void ppp() {
		System.out.println(a + ", " + b);
	}

}

public class ConstructorMain {

	public static void main(String[] args) {

		System.out.println("initB 실행 => initA 실행 => 생성자 실행");
		System.out.println("static => instance => constructor");
		System.out.println("----------------------------------------");
		Constructor c1 = new Constructor(12, 34); // 인스턴스 생성시 init 함수가 실행되어 맴버 변수에 값을 할당 합니다.
		System.out.println("----------------------------------------");
		Constructor c2 = new Constructor(); // 인스턴스 생성시 init 함수가 실행되어 맴버 변수에 값을 할당 합니다.
		System.out.println("----------------------------------------");
		Constructor c3 = new Constructor(9999); // 인스턴스 생성시 init 함수가 실행되어 맴버 변수에 값을 할당 합니다.
		System.out.println("----------------------------------------");

		// initB 실행 => initA 실행 => 생성자 실행
		// static => instance => constructor

//		c1.Constructor(); // => 생성자는 호출 불가능

		c1.ppp();
		c2.ppp();
		c3.ppp();

		// 기본 생성자와 사용자 정의 생성자는 오버로딩으로 동시에 생성이 가능합니다.
		// Constructor c2 = new Constructor(); // 기본 생성자로 인해 인자값을 주지 않아도 error이 없습니다.
		// 생성자도 오버로딩이 가능합니다.

	}

}
