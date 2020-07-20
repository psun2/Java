package lang_p;

import java.util.Arrays;
import java.util.Scanner;

class MZB {
	String last;
	Scanner sc = new Scanner(System.in);
	String[][] index = { { "가위", "바위", "보" }, { "찌", "묵", "빠" } };

	String[][] res = { // user
			{ "비김", "수비", "공격" }, { "공격", "비김", "수비" }, { "수비", "공격", "비김" }
			// com
	};

	int[] input(int i) {

		System.out.println(Arrays.toString(index[i]));

		String str = "";
		for (int j = 0; j < index[i].length; j++) {
			str += (j + 1) + "." + index[i][j] + " , ";
		}
		str += "0.종료";
		System.out.println(str);
		System.out.print("입력:");

		int user = sc.nextInt();

		user--;

//		System.out.println("user : " + user); // 여기서 -1 받음

		int com = (int) (Math.random() * 3);

		try { // 유저가 0 입력시 -1로 받아오고 index[i][-1] 이라는 배열값이 없음 => Error
//			if (user == -1)
//				throw new Exception();
			System.out.println("게이머:" + index[i][user]);
			System.out.println("콤퓨타:" + index[i][com]);
//			System.out.println("user" + user);
			return new int[] { user, com };
		} catch (Exception e) {
			return new int[] { user, com };
		}

	}

	String firstGo() {

		while (true) {
			int[] arr = input(0);

//			System.out.println(arr.length);
//			System.out.println(Arrays.toString(arr));

			// arr = { -1, com}

//			System.out.println("arr[0] : " + arr[0]);

			if (arr[0] == -1)
				return null;

			String ttt = res[arr[0]][arr[1]];
			System.out.println("공수:" + ttt);

			if (!ttt.equals("비김"))
				return ttt;
		}

	}

	void gameModue() {
		while (true) {
			last = firstGo();

//		System.out.println("last : " + last);

			if (last == null) {
				System.out.println("게임 종료");
				return;
			}

			int[] arr = input(1);

			if (arr[0] == -1) {
				System.out.println("게임 종료");
				return;
			}

			String ttt = res[arr[0]][arr[1]];
			if (ttt.equals("비김")) {
				if (last.equals("공격")) {
					System.out.println("결과: 승");
					continue;
				} else {
					System.out.println("결과: 패");
					continue;

				}
			}

			last = ttt;

			System.out.println("공수:" + last);

		}

	}

}

public class MZBMain {

	public static void main(String[] args) {
		new MZB().gameModue();
		System.out.println("묵지빠 게임 종료");
	}

}
