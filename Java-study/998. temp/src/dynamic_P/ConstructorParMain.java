package dynamic_P;

class ConstructorPar {

	int a = initA(); // ----------------------- 3
	static int b = initB(); // --------------------------1

	ConstructorPar(int a, int b) { // ------------- 4
		System.out.println("부모생성자");
		this.a = a;
		this.b = b;
	}

	int initA() { // ---------------------------- 3.1
		System.out.println("부모 a: 1");
		return 1;
	}

	static int initB() { // ------------------------------ 1.1
		System.out.println("부모 static b: 2");
		return 2;
	}

}

class ConstructorChild extends ConstructorPar {

	ConstructorChild(int dd, int ee) { // -------------------- 6
//		System.out.println(); // super 위에 무엇인가 들어오면 에러
		super(d, ee); // c는 안된다 D는 스테틱이여서 사용 가능 합니다. ---------- 0
		d = dd;
		System.out.println("자식생성자");
	}

	int c = initC(); // ---------------------- 5
	static int d = initD(); // -------------------------- 2

	int initC() { // --------------------- 5.1
		System.out.println("자식 c: 1111");
		return 1111;
	}

	static int initD() { // -------------------------------- 2.1
		System.out.println("자식 static d: 2222");
		return 2222;
	}

	void ppp() {
		System.out.println(a + ", " + b + ", " + c + ", " + d);
	}

}

public class ConstructorParMain {

	public static void main(String[] args) {

		ConstructorChild cc = new ConstructorChild(123, 456);
		// 부모부터 생성되고 자식인 ConstructorChild 클래스가 생성 됩니다.

//	⏬	부모 static b
//		자식 static d  // 자식의 static은 부모보다 일찍 생성되어, 조심하게 코딩 해야합니다.
//		부모 a
//		부모생성자
//		자식 c
//		자식생성자

//		✔ 순서 중요 
//		✔ 부모 static => 자식 static => 부모 인스턴스 => 부모 생성자 => 자식 인스턴스 => 자식생성자

		cc.ppp();

	}

}
