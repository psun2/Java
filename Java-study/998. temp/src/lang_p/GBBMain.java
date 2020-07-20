package lang_p;

import java.util.Scanner;

public class GBBMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] index = { "가위", "바위", "보" };

		String[][] res = { // user
				{ "비김", "패", "승" }, { "승", "비김", "패" }, { "패", "승", "비김" }
				// com
		};

		while (true) {
			System.out.println("가위바위보");
			System.out.println("1. 가위, 2. 바위, 3.보,  0.종료");
			System.out.print("입력 : ");

			int user = sc.nextInt();

			if (user == 0)
				break;

			user--;
			int com = (int) (Math.random() * 3);

			System.out.println("게이머:" + index[user]);
			System.out.println("콤퓨타:" + index[com]);
			System.out.println("결과:" + res[user][com] + "\n");
		}

	}

}
