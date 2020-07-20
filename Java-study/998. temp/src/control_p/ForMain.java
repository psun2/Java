package control_p;

public class ForMain {

	public static void main(String[] args) {

//		for (;;) { // 무한 반복문
//			System.out.println("for문이다...");
//		}

//		for (int i = 0;;) { // 무한 반복문
//			System.out.println("for문이다..." + i);
//		}

//		for (int i = 0;;i++) { // 무한 반복문
		// 초기값 ; ; 증감
//			System.out.println("for문이다..." + i);
//		}

//		for (byte i = 0;; i++) { // 무한 반복문
		// 초기값 ; ; 증감
//			System.out.println("for문이다..." + i);
//		}

//		for (byte i = 0; i < 5; i++) { 
//			// 초기값 ; 조건 ; 증감
//			System.out.println("for문이다..." + i);
//		}

//		for (byte i = 5; i < 5; i++) { 
//			// 초기값 ; 조건 ; 증감
//			System.out.println("for문이다..." + i);
//		}

		int tot = 0;
		System.out.println("for문 시작 " + tot);
		//////////////////////////////
		// Debug : ctrl + shift + B //
		// Run Debug : F11 //
		// step Into : F5 //
		// step Over : F6 //
		// Resume : F8 //
		//////////////////////////////
		for (int i = 0; i < 5; i++) {
			// 초기값 ; 조건 ; 증감
			tot += i;
			System.out.println("for문이다... " + i + ", " + tot);
		}

//		System.out.println("for문 종료 " +  i + ", " + tot); // i 는 내부변수로서 for문 밖에서 접근되지 않습니다.
		System.out.println("for문 종료 " + tot);

		// 1 => 100 짝수들의 합
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			if (i % 2 == 0)
				sum += i;
		}

		System.out.println("1 => 100 짝수들의 합 : " + sum);

		sum = 0;
		for (int i = 1; i <= 100; i += 2) {
			i += i % 2;
			System.out.println(i += i % 2);
			sum += i;
			System.out.println(i + " : " + sum);
		}

		System.out.println("1 => 100 짝수들의 합 : " + sum);
		System.out.println(1 % 2 + ", " + 1 / 2);

	}

}
