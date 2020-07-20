package excep_p;

public class Exam {

	public static void main(String[] args) {

		// if문을 사용하지 않고, 3, 6, 9 게임을 완성하세요.
		// 일의 자리만
		// 1. 1 => 20
		// 2. 1 => 100

		for (int i = 1; i <= 20; i++) {
			int one = i % 10;
			try {
				int a = 10 / (one % 3);
				System.out.println(i);
			} catch (Exception e) {
				try {
					int a = 10 / one;
					System.out.println("짝");
				} catch (Exception e2) {
					System.out.println(i);
				}

			}

		}

		System.out.println(3 % 10); // 3
		System.out.println((3 % 10) % 3); // 0

	}

}
