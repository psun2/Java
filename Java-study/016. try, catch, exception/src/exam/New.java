package exam;

public class New {

	public static void main(String[] args) {

		for (int i = 1; i <= 100; i++) {
			int a = i % 10;
			try { // 1 ~ 100 까지 3, 6, 9 가 없다면 출력
				int b = i / (a % 3);
				System.out.println(i);
			} catch (Exception e) { // 1 ~ 100 까지 3, 6, 9 가 있다면 출력
				try { // (10 번대) 숫자에 0 이 없다면 짝 출력
					int b = i / a;
					try {
						int c = i / (i % 3);
						System.out.println(i);
					} catch (Exception e2) {
						System.out.println("짝");
					}
				} catch (Exception e2) { // (10 번대) 숫자에 0 이 있다면 숫자 출력
					System.out.println(i);
				}

			}

		}
	}

}
