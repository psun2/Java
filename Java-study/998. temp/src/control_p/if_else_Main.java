package control_p;

public class if_else_Main {

	public static void main(String[] args) {

//	else { // 단독 사용 불가
//		
//	}

		if (true) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		System.out.println("시스템 종료");

		int a = 78;
		String res;

		if (a >= 80) {
			res = "합격";
		} else {
			res = "불합격";
		}

//		System.out.println("시스템 종료 " + res);

		////////////////////////////////////////
//
//		if (a >= 80) {
//			System.out.println("우수");
//		}
//
//		//////////////////////////////////////
//
//		if (a >= 60) {
//			System.out.println("정상");
//		} else {
//			System.out.println("미달");
//		}
//		
//		System.out.println("시스템 종료 " + res);

		if (a >= 80) {
			System.out.println("우수2");
		} else if (a >= 60) {
			System.out.println("양호2");
			if (a % 2 == 0) {
				System.out.println("짝수");
			} else {
				System.out.println("홀수");
			}
		} else if (a >= 40) {
			System.out.println("미달2");
		} else {
			System.out.println("불량2");
		}

		System.out.println("시스템 종료 " + res);

		int c = 100; // 외부변수
		if (true) {
			c = 100;
			int d = 400; // if 영역 내부변수 -- if문 종결시 변수 소멸
			System.out.println("if 영역 :" + c + ", " + d);
		}
		
//		System.out.println("if 영역 :" + c + ", " + d); // 외부에서 d 접근 불가
		System.out.println("시스템 종료2 : " + c);

	}

}
