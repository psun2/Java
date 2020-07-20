package lang_p;

import java.util.Scanner;

public class 가위바위보 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String[] index = { "", "가위", "바위", "보" };

		while (true) {

			System.out.println("1. 가위, 2. 바위, 3. 보, 0. 종료");
			System.out.print("입력 : ");
			int user = sc.nextInt();

			try {
				if (user >= 4 && user != 0)
					throw new Exception("입력값이 존재 하지 않습니다. 게임을 강제 종료 합니다.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				break;
			}

			if (user == 0)
				break;

			int com = (int) (Math.random() * 3 + 1);

			System.out.println("게이머 : " + index[user]);
			System.out.println("컴퓨터 : " + index[com]);

			String print = "패";
			if (user == com)
				print = "비김";
			if (user > com)
				print = "승";

			System.out.println(print + "\n");

		}

		System.out.println("게임 종료");

		sc.close();

	}

}
