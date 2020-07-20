package control_p;

public class HomeWork2 {

	public static void main(String[] args) {

		for (int i = 1; i <= 20; i += 3) {
			for (int j = i; j < i + 3; j++) {
				if (j > 20)
					break;

//				if (j == 3 || j == 6 || j == 9) {
				if (j < 10 && (double) j % 3 == 0) {
					System.out.print("\"짝\"");
//				} else if (j > 10 && (double) j % 3 == 1) {
//				} else if (j >= 10 && j % 10 == 3 || j % 10 == 6 || j % 10 == 9) {
				} else if (j % 10 == 3 || j % 10 == 6 || j % 10 == 9) {
					System.out.print("\"짝\" \t");
				} else {
					System.out.print(j + "\t");
				}
			}
			System.out.println();
		}

		System.out.println("-----------------------");

		for (int i = 1; i <= 100; i += 3) {
			for (int j = i; j < i + 3; j++) {
				if (j > 100)
					break;

				if (j < 10 && (double) j % 3 == 0) {
//					10 미만 일때 3으로 나눈 나머지가 0이되면 짝 출력
					System.out.print("\"짝\"");
				} else if ((j % 10 == 3 || j % 10 == 6 || j % 10 == 9) && (j / 10 == 3 || j / 10 == 6 || j / 10 == 9)) {
//					10 이상일때 10으로 나눈 몫 과 나머지가 3, 6, 9 라면 짝짝 출력
					System.out.print("\"짝짝\" \t");
				} else if (j % 10 == 3 || j % 10 == 6 || j % 10 == 9) {
					System.out.print("\"짝\" \t");
//					10 이상일때 10으로 나눈 몫이 3, 6, 9 라면 짝 출력
				} else if (j / 10 == 3 || j / 10 == 6 || j / 10 == 9) {
//					10 이상일때 10으로 나눈 나머지가 3, 6, 9 라면 짝 출력
					System.out.print("\"짝\" \t");
				} else {
					System.out.print(j + "\t");
				}
			}
			System.out.println();
		}

		int i = 13;
		int ten = i / 10; // 1
		int one = i % 10; // 3

		System.out.println(ten); // 1
		System.out.println(one); // 3
		System.out.println();
		System.out.println(ten % 3); // 1
		System.out.println(one % 3); // 0
		

	}
}
