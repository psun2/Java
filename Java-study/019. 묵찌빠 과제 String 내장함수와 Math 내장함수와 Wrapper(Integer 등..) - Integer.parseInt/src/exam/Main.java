package exam;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int userNum() {

		Scanner sc = new Scanner(System.in);

		System.out.println("1. 가위, 2. 바위, 3. 보");
		System.out.print("입력 : ");
		int user2 = sc.nextInt();
		user2--;

		return user2;

	}

	static void show(String[] index, String[][] result, int user, int computer) {

		System.out.println("유저 : " + index[user]);
		System.out.println("컴퓨터 : " + index[computer]);
		System.out.println("결과 : " + result[user][computer] + "\n");
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String[] index = { "가위", "바위", "보" };
		String[][] result = { { "비김", "패배", "승리" }, { "승리", "비김", "패배" }, { "패배", "승리", "비김" } };

		game: while (true) {

			System.out.println("공격권을 위한 가위바위보 진행후 ㅁㅉㅃ 진행");
			System.out.println("1. 가위, 2. 바위, 3. 보, 0. 종료");
			System.out.print("입력 : ");
			int user = sc.nextInt();

			if (user == 0)
				break;

			user--;

			int computer = (int) (Math.random() * 3);

			show(index, result, user, computer);

			String attack = result[user][computer];

			if (!result[user][computer].equals("비김")) { // 가위바위 보에서 승부가 났을시 ㅁㅉㅃ 진행

				String[][] mukchipa = { { "이김", "공격권 넘어감", "공격권 넘어감" }, { "이김", "공격권 넘어감", "공격권 넘어감" },
						{ "이김", "공격권 넘어감", "공격권 넘어감" } };

				while (true) {

					if (attack.equals("승리") || attack.equals("공격권 넘어감")) {
						System.out.println("공격권 : 유저");
						int user2 = userNum();
						int computer2 = (int) (Math.random() * 3);
						if (user2 == computer2) {
							show(index, mukchipa, user2, computer2);
							break game;
						}
						attack = "컴퓨터가 공격";
						show(index, mukchipa, user2, computer2);
					} else {
						String[][] mukchipaClone = mukchipa.clone();
						for (int i = 0; i < mukchipaClone.length; i++) {
							mukchipaClone[i][0] = "패배";
						}
						System.out.println("공격권 : 컴퓨터");
						int user2 = userNum();
						int computer2 = (int) (Math.random() * 3);
						if (user2 == computer2) {
							show(index, mukchipaClone, user2, computer2);
							break game;
						}
						attack = mukchipaClone[user2][computer2];
						show(index, mukchipaClone, user2, computer2);
					}

				}
			}
		}

//		공격권을 위한 가위바위보 진행후 ㅁㅉㅃ 진행
//		1. 가위, 2. 바위, 3. 보, 0. 종료
//		입력 : 1
//		유저 : 가위
//		컴퓨터 : 보
//		결과 : 승리
//
//		공격권 : 유저
//		1. 가위, 2. 바위, 3. 보
//		입력 : 1
//		결과 : 공격권 넘어감
//
//		공격권 : 컴퓨터
//		1. 가위, 2. 바위, 3. 보
//		입력 : 1
//		결과 : 공격권 넘어감
//
//		공격권 : 유저
//		1. 가위, 2. 바위, 3. 보
//		입력 : 1
//		결과 : 공격권 넘어감
//
//		공격권 : 컴퓨터
//		1. 가위, 2. 바위, 3. 보
//		입력 : 1
//		결과 : 공격권 넘어감
//
//		공격권 : 유저
//		1. 가위, 2. 바위, 3. 보
//		입력 : 1
//		결과 : 공격권 넘어감
//
//		공격권 : 컴퓨터
//		1. 가위, 2. 바위, 3. 보
//		입력 : 1
//		결과 : 공격권 넘어감
//
//		공격권 : 유저
//		1. 가위, 2. 바위, 3. 보
//		입력 : 1
//		결과 : 공격권 넘어감
//
//		공격권 : 컴퓨터
//		1. 가위, 2. 바위, 3. 보
//		입력 : 1
//		결과 : 패배

	}

}
