package exam_Game;

public class TestRandom {

	public static void main(String[] args) {

		while (true) {

			int a = ((int) (Math.random() * 100) + 1);

			System.out.println(a);
			if (a / 2 == 10) {

				System.out.println("브레이크 : " + a);
				break;

			}

//			10 과 -10만 큼만 나오게 하고 싶소

		}

//		System.out.println((int) (Math.random() * -10));
//		System.out.println((int) (Math.random() * 10));
//		- 10 이나 10 만 반환 받으면 되겟디

	}

}
