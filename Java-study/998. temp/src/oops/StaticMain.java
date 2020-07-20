package oops;

class ClaSta {

	// 메소드와 멤버 변수 의 라이프 싸이클
	// => static이 먼저 생성된후, 인스턴스가 생성 됩니다.
	// 클래스 정의 부에서는 static에서는 아직 생성이 안된 instance에 접근 할 수 없습니다.

	int a = initA();
	int b = initB();

//	static int c = 1000, d = 2000;

	int initA() {
		System.out.println("a 선언");
		return 10;
	}

	int initB() {
		System.out.println("b 선언");
		return 20;
	}

	int b2 = 30;

	void ins_1() {
		System.out.println("인스턴스 메소드 1 시작------------ ():" + a + ", " + b + ", " + c + ", " + d);
		ins_2();
		sta_2();
		System.out.println("인스턴스 메소드 1 끝------------ ()");
	}

	void ins_2() {
		System.out.println("인스턴스 메소드 2 실행 ()");
	}

	static void sta_1() {
//		System.out.println("static 메소드 1 시작------------ ():" + a + ", " + b + ", " + c + ", " + d);
		// static => static만 호출 가능

		System.out.println("static 메소드 1 시작------------ ():" + c + ", " + d);
//		ins_2();
		// 이당시엔 아직 인스턴스들은 생성이 안되어 접근이 불 가능 합니다.
		sta_2();

		System.out.println("static 메소드 1 끝------------ ()");
	}

	static void sta_2() {
		System.out.println("static 메소드 2 실행 ()");
	}

	static int c = initC(); // initA() // static이 instance보다 먼저 선언 되기 때문에 instance 멤버 호출불가
	static int d = 2000;

	static int initC() {
		System.out.println("c 선언 => 코드상으로 제일 마지막인제 제일 먼저 실행이 되네 ? // 한번만 선언이 되네 ?");
		System.out.println("즉 static은 클래스명을 부를때 한번 호출 됩니다.");
		return 1000;
	}
}

public class StaticMain {

	public static void main(String[] args) {
//		System.out.println("cs1: " + cs1.a + "," + cs1.b + "," + cs1.c + "," + cs1.d);
//		System.out.println("ClaSta: " + ClaSta.a + "," + ClaSta.b + "," + ClaSta.c + "," + ClaSta.d);

		System.out.println("ClaSta: " + ClaSta.c + "," + ClaSta.d);
		// 인스턴스 생성 전에 호출이 가능합니다. => 즉 클래스의 ✔(정의부) 에서 static이 메모리 공간에 할당 됩니다.
		ClaSta.sta_1();
//		ClaSta.ins_1(); // 일반 메소드는 불가능 합니다.
		ClaSta.sta_2();

		System.out.println("-----------------------------------------------------------");
		ClaSta cs1 = new ClaSta();
		System.out.println("-----------------------------------------------------------");
		ClaSta cs2 = new ClaSta();
		System.out.println("-----------------------------------------------------------");

		System.out.println("변경전: ");
		System.out.println("cs1: " + cs1.a + "," + cs1.b + "," + cs1.c + "," + cs1.d);
		// cs1: 10,20,1000,2000
		System.out.println("cs2: " + cs2.a + "," + cs2.b + "," + cs2.c + "," + cs2.d);
		// cs2: 10,20,1000,2000

		cs1.a = 17;
		cs1.c = 1111; // static 변수는 모든 인스턴스가 공유합니다.
		// cs1 의 static 변수 변경 => cs2의 c도 변경됨.
		// static 변수는 heap 공간에 자리에 있는 것이 아니고 주소가 가르키는 class 공간에 있으므로 static은 모든 인스턴스가
		// 공유합니다.
		System.out.println("변경후: ");
		System.out.println("cs1: " + cs1.a + "," + cs1.b + "," + cs1.c + "," + cs1.d);
		System.out.println("cs2: " + cs2.a + "," + cs2.b + "," + cs2.c + "," + cs2.d);

		// 스태틱 변수인 경우 cs2.c 게 표현이 가능 하지만 ClaSta.c 식으로 static 변수임을 표현해주는 것이 더 깔끔합니다.
		System.out.println("cs1: " + cs1.a + "," + cs1.b + "," + ClaSta.c + "," + ClaSta.d);
		System.out.println("cs2: " + cs2.a + "," + cs2.b + "," + ClaSta.c + "," + ClaSta.d);

		cs1.ins_1();
		cs1.sta_1();
		ClaSta.sta_1();

	}
}
