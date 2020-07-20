package array_p;

public class PrimeNumberMain {

	public static void main(String[] args) {

		int[] numbers = { 78, 11, 45, 67, 9, 62, 13, 26, 38, 17, 61, 94, 5 };

		String[] title = { "소수", "소수가 아닌" };

		int[][] result = new int[title.length][];

		for (int i : numbers) {
			int count = 1;
			for (int j = 2; j < i; j++) {
				if ((count = i % j) == 0) {
					break;
				}
			}
			//
			asdasdasd
		}
	}

}
