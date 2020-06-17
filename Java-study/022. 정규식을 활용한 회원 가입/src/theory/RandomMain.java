package theory;

import java.util.Random;

public class RandomMain {

	public static void main(String[] args) {

//		Math.random() 함수와 다르게 바운더리 범위를 정해줄수 있습니다.
//		또한 난수발생을 규칙적으로 할 수 있게 합니다.

		Random r1 = new Random();

		System.out.println("r1.nextInt() : " + r1.nextInt());
		System.out.println("r1.nextDouble() :" + r1.nextDouble());
		System.out.println("r1.nextFloat() : " + r1.nextFloat());
		System.out.println("r1.nextLong() : " + r1.nextLong());
		System.out.println("r1.nextBoolean() : " + r1.nextBoolean());

		System.out.println("----------------------------------------------------");

//		System.out.println(r1.nextInt(bound)); // 범위 설정
		System.out.println("r1.nextInt(3) : " + r1.nextInt(3)); // 0, 1, 2
		System.out.println("(int) (Math.random() * 3) : " + (int) (Math.random() * 3));
		// r1.nextInt(3) = Math.random() * 3 과 같은 결과 값이 발생합니다.

		System.out.println("----------------------------------------------------");

		Random r2 = new Random();
		Random r3 = new Random(10);
		Random r4 = new Random(10);

		for (int i = 0; i < 5; i++) {
			System.out.println("new Random().nextInt() : " + r2.nextInt());
		}

		System.out.println("------------");

		for (int i = 0; i < 5; i++) {
			System.out.println("new Random().nextInt(10) : " + r3.nextInt());
		}

		System.out.println("------------");

		for (int i = 0; i < 5; i++) {
			System.out.println("new Random().nextInt(10) : " + r4.nextInt());
		}

		// r3 와 r4 는 값이 나오는 규칙성은 없는 난수 이지만,
		// 매 반복시 마다 r3 와 r4 는 같은 값이 나옵니다.

	}

}
