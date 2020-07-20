package text_p;

import java.text.DecimalFormat;
import java.text.ParseException;

public class DecimalFormatMain {

	public static void main(String[] args) throws Exception {

		DecimalFormat df = new DecimalFormat();

		double dd = (double) df.parse("123,456.789");

		System.out.println(dd + 100); // 123556.789

//		dd = Double.parseDouble("123,456.789");
//		System.out.println(dd + 100); //, 때문에 형변환이 안됨

//		dd = (double) df.parse("123_4?56.789"); // Error

//		  0 : 자릿수 표현, 값이 없으면 0으로 처리
//		  # : 자릿수 표현, 값이 없으면 생략
//		  - : 음수부호 , 음수의 표현 위치 결정
//		  , : 단위구분자
//		  % : 백분율
//		  ; : 양수, 음수 구분

		double[] ddArr = { 1234, -1234, 123.45678, -123.45678, 987654.3210, -987654.3210, .45678, -.45678, .43210,
				-.43210, 0 };

		String[] ppArr = { "0", "#", "00000000", "#########", "#####00000",
				// "00000#####", // Error
				// "#0#0#0#0#0", // 섞어서 사용 불가
				".000", ".00000000", ".###", ".#######", "#,###", "#,##0.000", "#@##0.00", "#@###", "하하하#", "##0",
				"#,##0흐흐흐흐", "#,###.00%", // 곱하기 100이되어 들어옴
				"양수#,##0;음수0.000", // 숫자 표기방법은 양수의 것을 따라 갑니다.
				"▲0.00;▼" };

		for (String string : ppArr) {

			System.out.println(string + " >>>>>>>>>>>>>");

			df = new DecimalFormat(string);

			for (double d : ddArr) {
				System.out.println(d + " => " + df.format(d)); // 글자로 변경 // 소수점 아래로 짤라져 문자로 표현됩니다.
			}
			System.out.println();

		}

		int[] jum = { 67, 78, 89, 98, 76, 54, 78, 77, 90, 56 };

		//// 점수가 70점 이상이면 ▲, 미만이면 ▼ 로 표시해 주세요

		/// if, switch, 3항연산자등 조건문 사용하지 말것

		String pattern = "▲!#;▼!-";
		DecimalFormat format = new DecimalFormat(pattern);
		for (int i : jum) {
			int temp = i - 70;
			String buf = format.format(temp);
			String buff = buf.split("[!]")[0] + (Integer.parseInt(buf.split("[!]")[1]) + 70);
			System.out.println(buff);
		}

		System.out.println();

		for (int i : jum) {
			format = new DecimalFormat("▲0;▼");
			System.out.println("" + format.format(i - 67).charAt(0) + i);
		}

		System.out.println();

		for (int i : jum) {
			format = new DecimalFormat("▲0;▼");
			System.out.println(format.format(i - 67).substring(0, 1) + i);
		}

	}

}
