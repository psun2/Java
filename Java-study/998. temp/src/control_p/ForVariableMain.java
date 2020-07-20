package control_p;

public class ForVariableMain {

	public static void main(String[] args) {

		int i = 0, a = 0;
		System.out.println("for 시작 : " + i + ", " + a);
//		for (int i = 1; i < 5; i++) { // 선언 불가능 error C언어에서는 가능합니다.
//		for (i; i < 5; i++) { // error
//		for (; i < 5; i++) {
//		for (i = 3; i < 5; i++) {
			// 반복변수는 내부, 외부 둘다 가능
			// 반복변수 외부인 경우 초기값 생략 가능
			// 초기값 명시 할 경우 변수에 초기값 대입 필수
		for (int k = 3; k < 5; k++) {
			// 반복변수 k는 for문 최초 실행시에만 선언
			// for문 박복시 소멸되지 않고 for문을 빠져나갈때 소멸됩니다.
			a++;
			int b = 0;
			b++;
//			System.out.println("for 내부 : " + i + ", " + a + ", " + b);
			System.out.println("for 내부 : " + k + ", " + a + ", " + b);
			// b는 내부 변수로서 for문이 돌때 마다 선언되고 끝나면 소멸됩니다.
			// 결국 b는 1만 출력됩니다.
		}
//		System.out.println("for 끝 : " + i + ", " + a + ", " + b);
		System.out.println("for 끝 : " + i + ", " + a);

	}

}
