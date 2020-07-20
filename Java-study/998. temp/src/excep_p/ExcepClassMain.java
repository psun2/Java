package excep_p;

class ClaExcep {

	void meth_1() throws Exception {
		try {
			System.out.println("meth_1() 시작");
			meth_2();
			System.out.println("meth_1() 끝");
		} catch (Exception e) {
			System.out.println("m1 처리 : " + e.getMessage());
			throw e;
		}
	}

	void meth_2() throws Exception {
		try {
			System.out.println("\t meth_2() 시작");
			meth_3();
			System.out.println("\t meth_2() 끝");
		} catch (Exception e) {
			System.out.println("m2 처리 : " + e.getMessage());
			throw e;
		}
	}

	void meth_3() throws Exception { // => throws Exception 부른놈이 치워야함
		try {
			System.out.println("\t\t meth_3() 시작");
			throw new Exception("엄마 나 똥 쌋어");
//			System.out.println("\t\t meth_3() 끝");
		} catch (Exception e) {
			System.out.println("m3 처리 : " + e.getMessage());
			throw e; // re ~ throwing => 위에서 예외 처리를 했으나 또한번 예외 처리를 하므로, Error
			// throws Exception 을 하면 사용 가능
		}
	}

}

public class ExcepClassMain {

	public static void main(String[] args) throws Exception {

		try {

			// Exception ex = new Exception("남친은 용인");

			// throw ex;

//			System.out.println("끝"); 반드시 error 이 나기때문에 print 하지 못 합니다.
			throw new Exception("북친은 용인");
		} catch (Exception e) {
			System.out.println("하지마!!!!!: " + e.getMessage());
		}

		// 하지마!!!!!: 남친은 용인
		// getMessage 가 Exception 객체를 받아와 그 메세지를 출력합니다.

		ClaExcep ce = new ClaExcep();

		try {
			System.out.println("main 시작");

			ce.meth_1();

			System.out.println("main 끝");
		} catch (Exception e) {
			System.out.println("main 처리 : " + e.getMessage());
			throw e;
		}

	}

}
