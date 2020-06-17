package theory;

import java.text.DecimalFormat;

public class DecimalFormatMain {

	public static void main(String[] args) throws Exception {

		DecimalFormat df = new DecimalFormat();

//		  0 : 자릿수 표현, 값이 없으면 0으로 처리
//		  # : 자릿수 표현, 값이 없으면 생략
//		  - : 음수부호 , 음수의 표현 위치 결정
//		  , : 단위구분자
//		  % : 백분율
//		  ; : 양수, 음수 구분

		double dd = (double) df.parse("123,456.789");

		System.out.println(dd); // 123456.789
		System.out.println(dd + 100000); // 223456.789
		// number 형 data type임을 확인

		System.out.println(df.parse("213,.23")); // 213.23

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

	}

}
