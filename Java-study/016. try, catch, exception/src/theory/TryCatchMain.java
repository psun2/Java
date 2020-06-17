package theory;

public class TryCatchMain {

	public static void main(String[] args) {

		System.out.println("1. ---------------------------- ");

		try {
			System.out.println("try 시작");

			int a = 10 / 0;

			System.out.println("try 끝");
		} catch (Exception e) {
			System.out.println("catch 시작");
			System.out.println("catch 끝");
			System.out.println(e.getMessage()); // / by zero
//			e.printStackTrace();
//			java.lang.ArithmeticException: / by zero
//			at theory.TryCatchMain.main(TryCatchMain.java:10)
//			throw e;
//			java.lang.ArithmeticException: / by zero
//			at theory.TryCatchMain.main(TryCatchMain.java:10)
		} finally {
			System.out.println("finally 시작");
			System.out.println("finally 끝");
		}

		System.out.println(
				"catch 의 자주 사용되는 error 메세지의 종류는 e.printStackTrace(); 과  throw e; 이 있으며, 이 문장을 만나도 finally 는 실행이되며, return 과 같이 메인 함수를 종료하여 더이상 진행하지 않습니다.");

		System.out.println();

		System.out.println("2. 정상진행------------------------ ");

		try {
			System.out.println("try 시작");

//			int a = 10 / 0;

			System.out.println("try 끝");
		} catch (Exception e) {
			System.out.println("catch 시작");
			System.out.println("catch 끝");
		} finally {
			System.out.println("finally 시작");
			System.out.println("finally 끝");
		}

		System.out.println("비정상적인 진행 또는 정상적 진행 모두 finally 를 실행합니다.");

		System.out.println();

		System.out.println("3. return------------------------ ");

		try {
			System.out.println("try 시작");

			int a = 10 / 0;

			System.out.println("try 끝");
		} catch (Exception e) {
			System.out.println("catch 시작");
			System.out.println("catch 끝");
			return;
		} finally {
			System.out.println("finally 시작");
			System.out.println("finally 끝");
		}

		// return 키워드를 만나면 메인함수는 더이상 실행 되지 않고 종료됩니다.
		System.out.println("return 키워드를 만나면 메인함수는 더이상 실행 되지 않고 종료됩니다.");
	}

}
