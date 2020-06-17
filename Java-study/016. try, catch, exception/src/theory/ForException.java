package theory;

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

		System.out.println("try 문 안쪽에 있는 for문이 catch 되었을시, 나머지 루틴을 무시한채 끝납니다.");

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

		System.out.println("for 문이 try catch 밖에 있다면, 모든 반복 루틴을 소화하면서 예외처리 합니다.");

	}

}
