package control_p;

public class ifMain {

	public static void main(String[] args) {

		int a = 10;
		if (false) {
			// {} 안의 구간은 전부 if문의 종속 구간입니다.
			System.out.println("참 입니다. 1");
			System.out.println("참 입니다. 2");
			System.out.println("참 입니다. 3");
			System.out.println("참 입니다. 4");
			a = 30;
		}

		System.out.println("시스템 종료");

		if (false) // {} 없이 사용할 경우 if문 다음 프로세스 1개만 해당
//		실행구문의 기준은 구문 종결어 ; 로 구분
			System.out.println("참 입니다. 1");

		System.out.println("참 입니다. 2");
		System.out.println("참 입니다. 3");
		System.out.println("참 입니다. 4");

		int b = 78;
		String res = "불합격";

		String res2;

		if (b >= 70) {
			res = "합격";
		}

//		if(b >= 70) { // 거짓이 되었을때 res2 값이 없기 때문에 error
		if (true) {
			res = "합격";
			res2 = "합격";
		}

		System.out.println("시스템 종료 " + a + ", " + res + ", " + res2);

//		if(true) // {} 없이 사용할 경우 반드시 1개의 실행구문이 있어야한다. 
		// 없으면 에러
	}

}
