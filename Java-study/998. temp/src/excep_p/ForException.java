package excep_p;

public class ForException {

	public static void main(String[] args) {

		System.out.println("1. try => for -------------- ");
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				int a = 10 / 0;
			}
		} catch (Exception e) {
			System.out.println("try => for 예외처리");
		}

		System.out.println("2. for => try -------------- ");
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println(i + " 시작");
				int a = 10;
				System.out.println(i + " 끝");
			} catch (Exception e) {
				System.out.println("for => try 예외처리 : " + i);
			}
		}
	}

}
