package excep_p;

class AAA {
	int a = 10;
}

public class TryCatchMain {

	public static void main(String[] args) {

		int c = 10;
		try {

			try {
				int b = 10 / 0; // 구간별 변수 선언
			} catch (Exception e2) {
				System.out.println("try catch 예외 처리 1");
			}

			// int c = 20; 에러
			int b = 10 / 0;

		} catch (Exception e) {
			System.out.println("catch 예외 처리2");

			try {
				int b = 10 / 0; // 구간별 변수 선언
			} catch (Exception e2) {
				System.out.println("catch catch 예외 처리 3");
			}
		}

		/*
		 * catch (ArithmeticException e) {
		 * 
		 * }
		 */

		try {
			System.out.println("시작");

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[2]);

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
		} catch (ArithmeticException e) {
			System.out.println("수학적 예외 처리");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("인덱스 예외 처리");
		}
/////////////////////////////////////////////////////////////////////////////
		try {
			System.out.println("시작");

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[3]); // 최초 예외 발생지점에서 catch 진입

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
		} catch (ArithmeticException e) {
			System.out.println("수학적 예외 처리");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("인덱스 예외 처리");
		}
//////////////////////////////////////////////////////////////////////////////
		try {
			System.out.println("시작");

			AAA[] arr1 = new AAA[3];

			System.out.println(arr1[1].a);

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[3]); // 최초 예외 발생지점에서 catch 진입

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
		} catch (ArithmeticException e) {
			System.out.println("수학적 예외 처리");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("인덱스 예외 처리");
		} catch (Exception e) {
			System.out.println("일반 예외 처리");
		}

///////////////////////////////////////////////////////////////////////////////
		try {
			System.out.println("시작");

			AAA[] arr1 = new AAA[3];

			System.out.println(arr1[1].a);

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[3]); // 최초 예외 발생지점에서 catch 진입

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
//		} catch (ArithmeticException e) {
//			System.out.println("수학적 예외 처리");
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("인덱스 예외 처리");
		} catch (Exception e) {
			System.out.println("일반 예외 처리");
		}
////////////////////////////////////////////////////////////////////////
		try {
			System.out.println("시작");

			AAA[] arr1 = new AAA[3];

			System.out.println(arr1[1].a);

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[3]); // 최초 예외 발생지점에서 catch 진입

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
		} catch (ArithmeticException e) {
			System.out.println("수학적 예외 처리\n" + e);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("인덱스 예외 처리\n" + e);
		} catch (Exception e) {
			System.out.println("일반 예외 처리\n" + e);
		}
/////////////////////////////////////////////////////////////////////
		try {
			System.out.println("시작");

			AAA[] arr1 = new AAA[3];

			System.out.println(arr1[1].a);

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[3]); // 최초 예외 발생지점에서 catch 진입

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
		} catch (ArithmeticException e) {
			System.out.println("수학적 예외 처리\n" + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("인덱스 예외 처리\n" + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 처리\n" + e.getMessage());
		}
///////////////////////////////////////////////////////////////////////////////
		try {
			System.out.println("시작");

			AAA[] arr1 = new AAA[3];

			System.out.println(arr1[1].a);

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[3]); // 최초 예외 발생지점에서 catch 진입

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
		} catch (ArithmeticException e) {
			System.out.println("수학적 예외 처리\n" + e.getMessage());
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("인덱스 예외 처리\n" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("일반 예외 처리\n" + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("\n프로그램 종료\n");
/////////////////////////////////////////////////////////////
		try {
			System.out.println("finally 시작");

			AAA[] arr1 = new AAA[3];

			// System.out.println(arr1[1].a);

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[3]); // 최초 예외 발생지점에서 catch 진입

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
		} catch (ArithmeticException e) {
			System.out.println("수학적 예외 처리\n" + e.getMessage());
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("인덱스 예외 처리\n" + e.getMessage());
			e.printStackTrace();
			return; // return 시 프로그램 종료 메세지까지 도달 하지 못하고 메인 함수 종료
		} catch (Exception e) {
			System.out.println("일반 예외 처리\n" + e.getMessage());
			e.printStackTrace();
		} finally { // return 을 만나면 메인 함수가 종료되므로 프로그램 종료라는 메세지는 필요하기 때문에 무조건 실행되는 fanally 에 종료 구문을
					// 줍니다.
			System.out.println("\n프로그램 종료");
		}
//////////////////////////////////////////////////////////////
		try {
			System.out.println("return 시작");

			AAA[] arr1 = new AAA[3];

			// System.out.println(arr1[1].a);

			int[] arr = { 11, 22, 33 };

			System.out.println(arr[3]); // 최초 예외 발생지점에서 catch 진입

			int a = 10 / 0; // error 발생된 시점에서 throw -> catch
			System.out.println("끝 " + a);
		} catch (ArithmeticException e) {
			System.out.println("수학적 예외 처리\n" + e.getMessage());
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("인덱스 예외 처리\n" + e.getMessage());
			e.printStackTrace();
			return; // return 시 프로그램 종료 메세지까지 도달 하지 못하고 메인 함수 종료
		} catch (Exception e) {
			System.out.println("일반 예외 처리\n" + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("\n프로그램 종료");

	}

}
