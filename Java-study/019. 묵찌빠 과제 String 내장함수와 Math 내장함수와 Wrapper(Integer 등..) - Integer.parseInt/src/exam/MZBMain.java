package exam;

import java.util.Arrays;
import java.util.Scanner;

//TODO 무한 게임 반복과 0을 눌렀을시 종료 error 해결 과제

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
			int[] arr = input(0); // 가위바위보 인지 묵찌빠 인지 index 배열을 출력합니다.
			// 그리고 배열의 형태로 user의 입력값과 com의 입력값을 받아 옵니다.

//			System.out.println(arr.length);
//			System.out.println(Arrays.toString(arr));

			// arr = { -1, com}

//			System.out.println("arr[0] : " + arr[0]);

			if (arr[0] == -1) // arr[0] user의 입력값이 0인경우 즉 게임을 종료할 경우
				return null; // null

			String ttt = res[arr[0]][arr[1]];
			System.out.println("공수:" + ttt); // 공격 과 수비가 번갈아 가며 나옵니다.

			if (!ttt.equals("비김")) // 비겻을시 즉 승부가 낫을시
				return ttt; // 반복문을 종료하고 리턴합니다.
		}

	}

	void gameModue() { // 게임을 실행합니다.
		while (true) {
			last = firstGo(); // 먼저 가위바위보를 실행합니다.
			// 리턴된 last 는 승패를 가지고 있습니다.

//		System.out.println("last : " + last);

			if (last == null) { // 0의 값을 받아 null로 리턴된경우 게임을 종료
				System.out.println("게임 종료");
				return;
			}

			int[] arr = input(1); // 묵찌빠 실행

			if (arr[0] == -1) {
				System.out.println("게임 종료");
				return;
			}

			String ttt = res[arr[0]][arr[1]];
			if (ttt.equals("비김")) { // 비김 즉, 묵찌빠의 승부가 났고
				if (last.equals("공격")) { // 공격일때 비겻으면
					System.out.println("결과: 승"); // 승
					continue;
				} else {
					System.out.println("결과: 패"); // 패
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