package lang_p;

import java.util.Arrays;

public class JuminMain {

	static int atoi(String str) {

		int res = 0;

		for (int i = 0; i < str.length(); i++) {
			res *= 10;
			res += str.charAt(i) - '0';
		}
		// System.out.println(res+1000);
		return res;

	}

	static void ppp(String title, int[] arr) {
		title += ":";

		for (int i = 0; i < arr.length; i++) {
			title += arr[i] + "" + index.charAt(i) + " ";
		}
		System.out.println(title);

	}

	static String index = "년월일";

	public static void main(String[] args) {
		String jumin = "920531-18234567";

		int k = jumin.charAt(7) - '0'; // 49 - 48 = 1

		System.out.println("성별:" + "여남".charAt(k % 2) + "자");

		// - 48
		// 1 2 3 4 5 6 7 8
		// %2
		// 0 0 1 0 1 0 1 0
		// 여 남 여 남 여 남 여 남

		System.out.println("국적:" + "내외".charAt(k / 5) + "국인");

		// 1 2 3 4 5 6 7 8
		// /5
		// 0 0 0 0 1 1 1 1

		int[] now = { 2020, 6, 2 };
		int[] birth = new int[index.length()];

		for (int i = 0; i < birth.length; i++) {
			birth[i] = atoi(jumin.substring(i * 2, i * 2 + 2));
			// i = 0 => 0, 2
			// i = 1 => 2, 4
			// i = 2 => 4, 6
		}

		// k = 주민번호 뒷자리의 첫번호
		birth[0] += ((k - 1) % 4 / 2 + 19) * 100;
		/*
		 * -1 %4 /2 +19 *100 1900 1 0 0 0 19 1900 2 1 1 0
		 * 
		 * 2000 3 2 2 1 20 2000 4 3 3 1
		 * 
		 * 1900 5 4 0 1900 6 5 1 2000 7 6 2 2000 8 7 3
		 * 
		 * 
		 */

		ppp("오늘", now);
		ppp("생년월일", birth);
		int age = now[0] - birth[0];
		System.out.println("한국나이:" + (age + 1));

		boolean pass = now[1] > birth[1] || (now[1] == birth[1] && now[2] > birth[2]);

		birth[0] = now[0];

		int[] mm = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int tot = 0;

		for (int i : mm) {
			tot += i;
		}
		// System.out.println("전체:"+tot);

		int nowDD = now[2];

		for (int i = 0; i < now[1]; i++) {
			nowDD += mm[i];
		}
		// System.out.println("nowDD:"+nowDD);

		int birDD = birth[2];

		for (int i = 0; i < birth[1]; i++) {
			birDD += mm[i];
		}
		// System.out.println("birDD:"+birDD);

		int dday = birDD - nowDD;
		if (pass) {
			birth[0]++;
			dday += tot;
		} else {
			age--;
		}
		System.out.println("만나이:" + age);

		ppp("다가올 생일", birth);
		System.out.println("dday:" + dday);

	}

}
