package excep_p;

import java.util.Scanner;

public class StudExcepLoginMain {

	public static void main(String[] args) {

		String[][] arr = { { "aa", "1111", "이효리" }, { "bb", "2222", "삼효리" }, { "cc", "3333", "사효리" },
				{ "dd", "4444", "오효리" }, { "ee", "5555", "육효리" }

		};
		Scanner sc = new Scanner(System.in);

		while (true) {

			try {
				int pos = -1; // 초기값 : 없을 경우 -1

				// id 입력
				System.out.print("id:");
				String buf = sc.nextLine();

				/* id 해당 객체 찾기 */
				for (int i = 0; i < arr.length; i++) {
					if (buf.equals(arr[i][0])) {
						pos = i;
						break;
					}

				}
				// 못 찾으면 --> 꺼지셈
				if (pos < 0)
					throw new Exception("id 꺼지셈");

				// pw 입력
				System.out.print("pw:");
				buf = sc.nextLine();
				// id번호 해당하는 객체 pw와 비교
				if (!arr[pos][1].equals(buf))
					// 아니면 ---> 꺼지셈
					throw new Exception("pw 꺼지셈");

				// id 번호에 해당하는 이름 출력
				System.out.println(arr[pos][2] + " 로그인 성공");
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}

}
