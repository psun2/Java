package lang_p;

import java.util.Arrays;

public class StringMethodMain {

	public static void main(String[] args) {

		String a = "선생님 귀여워요 AbcD eF Java 만세 12a3a4";

		System.out.println("String.length() => 문자열의 길이");
		System.out.println(a.length()); // 31

		System.out.println();
		System.out.println("String.charAt(num) => 문자열의  해당 index 추출");
		System.out.println(a.charAt(0)); // 선
		char ch = a.charAt(30);
		System.out.println(ch); // 4

		System.out.println();
		System.out.println("String.subString(시작index, 끝index 전) => 시작index ~ 끝index 전 문자열 추출");
		String str1 = a.substring(2, 7);
		System.out.println(str1); // 님 귀여워
		System.out.println("String.subString(시작index) => 시작index 부터 끝까지 문자열 추출");
		System.out.println(a.substring(2)); // 님 귀여워요 AbcD eF Java 만세 12a3a4

		System.out.println();
		System.out.println("String.indexOf(\"문자 or 문자열\", 시작index위치) => 해당 글자의 index 반환(대/소문자를 구분 합니다.)");
		System.out.println(a.indexOf("a")); // 18
		System.out.println(a.indexOf("k")); // 없는 경우 -1 반환
		System.out.println(a.indexOf("2a")); // 26
		System.out.println(a.indexOf("a", 19)); // 20
		System.out.println(a.indexOf("a", 21)); // 27

		System.out.println();
		System.out.println(
				"String.lastIndexOf(\"문자 or 문자열\", 시작index위치) => 해당문자열의 마지막으로 부터 해당 글자의 index 반환(대/소문자를 구분 합니다.)");
		System.out.println(a.lastIndexOf("a")); // 29
		System.out.println(a.lastIndexOf("a", 25)); // 20 => 문자열의 25번째 index에서 부터 앞으로 한칸 씩 오면서 찾습니다.
		System.out.println(a.lastIndexOf("a", 17)); // 값이 없는 경우 -1 반환

		System.out.println();
		System.out.println("확장자 찾기");
		String file = "aSAdf.asdJKYdasd.aASDRasdas.asd.txt";
		System.out.println(file);
//		System.out.println(file.length()); // 31
//		System.out.println(file.lastIndexOf(".")); // 27
//		System.out.println(file.substring(27, 31)); // .txt
		System.out.println(file.substring((file.lastIndexOf(".") + 1), file.length())); // txt
		System.out.println(file.substring((file.lastIndexOf(".") + 1))); // txt

		System.out.println();
		System.out.println("String.contains(\"문자 or 문자열\") => 해당 문자열이 존재하는지 true or false 반환");
		System.out.println(a.contains("a")); // true
		System.out.println(a.contains("Korea")); // false

		System.out.println();
		System.out.println("String.startsWith(\"시작 문자열\") => 해당 문자열의 시작 문자열이 맞으면 true or false 반환");
		System.out.println(a.startsWith("선생님")); // true
		System.out.println(a.startsWith("강사님")); // false

		System.out.println();
		System.out.println("String.endsWith(\"끝 문자열\") => 해당 문자열의 끝 문자열이 맞으면 true or false 반환");
		System.out.println(a.endsWith("a4")); // true

		System.out.println();
		System.out.println("String.split(\"구분자\") => 구분자를 통해 배열을 자릅니다.");
		String name = "박시현,이주혁.김연섭-오민석,장정호,이호인,김영재";
		String[] arr = name.split("[.,-]"); // 정규식 사용
		for (String string : arr) {
			System.out.println(string);
		}

		System.out.println();
		System.out.println("String.toLowerCase() => 소문자 변환");
		System.out.println(file.toLowerCase());
		// asadf.asdjkydasd.aasdrasdas.asd.txt

		System.out.println();
		System.out.println("String.toUpperCase() => 대문자 변환");
		System.out.println(file.toUpperCase());
		// ASADF.ASDJKYDASD.AASDRASDAS.ASD.TXT

		System.out.println();
		System.out.println("String.concat(\"문자 or 문자열\") => 문자열 이어 붙이기");
		System.out.println(a.concat(" 여긴 concat"));
		// 선생님 귀여워요 AbcD eF Java 만세 12a3a4 여긴 concat

		System.out.println();
		// 단어의 첫글자는 대문자 나머지는 소문자
		String c = "";

		for (int i = 0; i < a.split(" ").length; i++) {
			c += a.split(" ")[i].toLowerCase().substring(0, 1).toUpperCase()
					+ a.split(" ")[i].substring(1).toLowerCase() + " ";
		}
		System.out.println(a.length());
		System.out.println(c.length());
		System.out.println("원본 => : " + a);
		c = c.substring(0, (c.length() - 1)); // 마지막 공백 제거
		System.out.println("변경 => : " + c);
		System.out.println(a.length());
		System.out.println(c.length());

		// 답
		String aaa = "";
		for (String s : a.toLowerCase().split(" ")) {
			aaa += s.substring(0, 1).toUpperCase() + s.substring(1) + " ";
		}
		aaa = aaa.substring(0, aaa.length() - 1);
		System.out.println(aaa);

		// replace
		System.out.println();
		System.out.println("String.replace(\"변경전 문자\", \"변경후 문자\") => 문자열 변경 메소드");
		System.out.println(a.replace("a", "에이"));

		// toCharArray
		System.out.println();
		System.out.println("String.toCharArray() => String 을 char 형태의 배열로 받아옵니다.");
		char[] chArr = a.toCharArray();
		for (int i = 0; i < chArr.length; i++) {
			System.out.println(i + " : " + chArr[i]);
		}

		// trim
		System.out.println();
		System.out.println("String.trim() => 문장열의 띄어쓰기를 제외한 앞뒤로 공백이 있다면 공백을 제거 합니다.");
		String b = "     korea aca     ";
		System.out.println(b + " : " + b.length());
		System.out.println(b.trim() + " : " + b.trim().length());

		//
		System.out.println();
		System.out.println("String.concat(\"문자열에 더해줄 문자\")");
		System.out.println(b.concat("배고팡"));
		System.out.println(b.trim().concat("배고팡"));

		// 확장자 찾기
		System.out.println();
		String extend = "asdjkasjdklas.asdkjaskldjas.djdjkas.TXT";
		System.out.println(extend.toLowerCase().substring(extend.toLowerCase().lastIndexOf(".")));

		// a이 계속 찾기
		System.out.println();
		String result = "asdkalsdgasd";
		System.out.println(result.contains("asd"));
		System.out.println(result.indexOf("a")); // 0
		System.out.println(result.substring(0, 3));
		System.out.println(result.indexOf("a", 1));
		System.out.println(result.substring(2, 5));
		System.out.println(result.indexOf("a", 5));
	}

}
