package theory;

import java.text.DecimalFormat;

public class DecimalFormatMiniExam {

	public static void main(String[] args) {

		// 점수가 70점 이상이면 ▲, 미만이면 ▼ 로 표시해 주세요

		// if, switch, 3항연산자등 조건문 사용하지 말것

		int[] jum = { 67, 78, 89, 98, 76, 54, 78, 77, 90, 56 };

		String pattern = "▲#;▼";
		DecimalFormat format = new DecimalFormat(pattern);
		for (int i : jum) {

			String result = "";
			char ch = format.format(i - 70).charAt(0);
			result += i;
			result += ch;
			System.out.println(result);
		}

		System.out.println();
		pattern = "▲0;▼";
		format = new DecimalFormat(pattern);
		for (int i : jum) {
			System.out.println(format.format(i - 70).substring(0, 1) + i);
		}

		System.out.println();
		pattern = "▲!#;▼!-";
		format = new DecimalFormat(pattern);
		for (int i : jum) {
			int temp = i - 70;
			String buf = format.format(temp);
			String buf2 = buf.split("[!]")[0] + (Integer.parseInt(buf.split("[!]")[1]) + 70);
			System.out.println(buf2);
		}
	}
}
