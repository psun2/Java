package lang_p;

import java.util.Arrays;
import java.util.Scanner;

public class MZB2Main {
	String last;
	Scanner sc = new Scanner(System.in);
	String[][] index = { { "가위", "바위", "보" }, { "찌", "묵", "빠" } };

	String[][] res = { // user
			{ "비김", "수비", "공격" }, { "공격", "비김", "수비" }, { "수비", "공격", "비김" }
			// com
	};

	int[] input(int i) throws Exception {

		System.out.println(Arrays.toString(index[i]));

		String str = "";
		for (int j = 0; j < index[i].length; j++) {
			str += (j + 1) + "." + index[i][j] + " , ";
		}
		str += "0.종료";
		System.out.println(str);
		System.out.print("입력:");

		int user = sc.nextInt();

		if (user == 0)
			throw new Exception();
		user--;
		int com = (int) (Math.random() * 3);

		System.out.println("게이머:" + index[i][user]);
		System.out.println("콤퓨타:" + index[i][com]);

		return new int[] { user, com };

	}

	String firstGo() throws Exception {
		while (true) {
			int[] arr = input(0);

			String ttt = res[arr[0]][arr[1]];
			System.out.println("공수:" + ttt);

			if (!ttt.equals("비김"))
				return ttt;
		}

	}

	void gameModue() throws Exception {
		last = firstGo();

		while (true) {
			int[] arr = input(1);

			String ttt = res[arr[0]][arr[1]];
			if (ttt.equals("비김")) {
				if (last.equals("공격")) {

					System.out.println("결과: 승");
				} else
					System.out.println("결과: 패");

				break;
			}

			last = ttt;

			System.out.println("공수:" + last);

		}

	}

	MZB2Main() {
		try {
			while (true) {
				System.out.println("묵지빠 시작 >>>>>>>>>");
				gameModue();
				System.out.println("묵지빠 끝 >>>>>>>>>");
			}
		} catch (Exception e) {
			System.out.println("묵지빠 게임 종료");

		}
	}

	public static void main(String[] args) {

		new MZB2Main();
	}

}
