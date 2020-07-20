package lang_p;

import java.util.Scanner;

public class CharmCharmCharm {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String[] index = { "", "왼쪽", "오른쪽" };

		while (true) {
			System.out.println("추억의 게임 참참참");
			System.out.println("1. 왼쪽 , 2. 오른쪽, 0. 종료");
			System.out.print("입력 : ");

			int user = sc.nextInt();

			if (user == 0)
				break;

			int com = (int) (Math.random() * 2 + 1);

			String res = "패";
			if (user == com)
				res = "승";

			System.out.println("게이머 : " + index[user]);
			System.out.println("컴퓨터 : " + index[com]);
			System.out.println("결과 : " + res);
		}

		System.out.println("게임 종료");

	}

}
