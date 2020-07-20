package control_p;

public class GuGuDanExample {

	public static void main(String[] args) {

		for (int i = 2; i <= 9; i++) {
			System.out.println(i + " 단 ");
			for (int j = 1; j <= 9; j++) {
				System.out.println(i + " * " + j + " = " + i * j);
			}
		}

		System.out.println("");

		for (int i = 2; i <= 9; i++) {
			System.out.print(i + " 단\t\t");
		}
		System.out.println("");

		for (int i = 1; i <= 9; i++) {
			for (int j = 2; j <= 9; j++) {
				System.out.print(j + " * " + i + " = " + i * j + "\t");
			}
			System.out.println("");
		}

		System.out.println("");

//		for (int i = 2; i <= 9; i++) {
//			System.out.print(i + " 단\t");
//			for (int j = 1; j <= 9; j++) {
//				System.out.print(i + " * " + j + " = " + i * j + "\t");
//			}
//			System.out.println("");
//		}

		System.out.println("-----------------------");

		int kan = 3; // 각 줄에 나타날 단의 갯수
		int max = 13; // 구구단 마지막 단

		for (int i = 1; i <= max; i += kan) { // 크게 돌린다.
//			System.out.println(i);
			int end = i + kan - 1;
			if (end > max)
				end = max;
			for (int j = 1; j < 10; j++) {
				for (int k = i; k <= end; k++) {
					System.out.print(k + " * " + j + " = " + j * k + "\t");
				}
				System.out.println("");
			}
			System.out.println("");
		}


	}

}
