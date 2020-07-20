package util_p;

import java.util.Scanner;
import java.util.regex.Pattern;

class RegexMo {

	String[] pp;
	String qq, an;
	RegexMem mem;

	int error;

	public RegexMo(RegexMem mem, String qq, int error, String... pp) {
		super();
		this.mem = mem;
		this.qq = qq;
		this.pp = pp;
		this.error = error;
	}

	void inputData() throws MyExcep {

		Scanner sc = new Scanner(System.in);

		System.out.print(qq + ":");

		an = sc.nextLine();

		for (String ss : pp) {
			if (!Pattern.matches(ss, an.toLowerCase()))
				throw new MyExcep(qq, error);
		}

		mem.input(qq, an);
	}

}

class MyExcep extends Exception {

	int i;
	String str;

	String[] arr = { "형식 불일치", "내용 불일치" };

	public MyExcep(String str, int i) {
		super();
		this.str = str;
		this.i = i;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		// return super.getMessage();
		return str + " " + arr[i];
	}

}

class RegexMem {

	String[] title = { "id", "pw", "email", "tel", "name", "jumin", "img", "zipcode" };

	String[] data = new String[title.length];

	void input(String name, String data) {
		for (int i = 0; i < this.title.length; i++) {
			if (title[i].equals(name)) {
				this.data[i] = data;
			}
		}
	}

	void zipSch() {

		String[] seoul = { "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구",
				"서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };
		
		String[] jaeum = { "[가-깋]", "[나-닣]", "[다-딯]", "[라-맇]", "[마-밓]", "[바-빟]", "[사-싷]", "[아-잏]", "[자-짛]", "[차-칳]",
				"[카-킿]", "[타-팋]", "[파-핗]", "[하-힣]", "[빠-삫]", "[짜-찧]", "[따-띻]", "[까-낗]", "[싸-앃]" };

		String index = "ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎㅃㅉㄸㄲㅆ";

		String pp = ".*";
		for (char ch : data[7].toCharArray()) {

			int pos = index.indexOf(ch);
			if (pos >= 0) {
				pp += jaeum[pos];
			} else {
				pp += ch;
			}
		}
		pp += ".*";

		String res = "";

		for (String str : seoul) {
			if (Pattern.matches(pp, str)) {
				res += str + ",";
			}
		}

		data[7] += ":" + res;
	}

	void ppp() {
		zipSch();

		System.out.println("회원가입 정보 >>>>>>>>>>>>");
		for (int i = 0; i < this.title.length; i++) {
			System.out.println(title[i] + ":" + data[i]);
		}
	}
}

public class RegexJoinMain {

	public static void main(String[] args) {

		RegexMem mem;

		/*
		 * //// 회원 가입 유효성 검사를 실시하세요 1. 아이디 : 영문 숫자 조합 2. 비번 , 비번확인 3. 이메일 - 아이디:영문,숫자 @
		 * 도메인 : 영문 점 4. 전화번호 - 숫자 : ###-####-####, ##(#)-###-#### 5. 이름 (한글만 가능) 6.
		 * 주민번호 7. 사진 첨부-->영문,숫자.이미지 확장자 이미지(jpg, jpeg, bmp, png, gif) -- 대소문자 구분없음 8.
		 * 우편번호 검색 - 구단위 (초성검색) 예외처리로 처리할 것
		 */

		while (true) {

			mem = new RegexMem(); // while 문을 돌때마다 새로 생성 찌꺼기 .....
			
			RegexMo[] arr = { 
					new RegexMo(mem, "id", 0, "[0-9a-zA-Z]{2,}", ".*[0-9].*", ".*[a-zA-Z].*"),
					new RegexMo(mem, "pw", 0, ".{2,}"), 
					new RegexMo(mem, "pw확인", 1, ".{2,}"),
					new RegexMo(mem, "email", 0,
							"[0-9a-zA-Z]{2,}@[a-zA-Z]{2,}.(([a-zA-Z]{2,})|([a-zA-Z]{2,}.[a-zA-Z]{2,}))"),
					new RegexMo(mem, "tel", 0, "((\\d{3}-\\d{4})|(\\d{2,3}-\\d{3}))-\\d{4}"),
					new RegexMo(mem, "name", 0, "[가-힣]{2,}"), 
					new RegexMo(mem, "jumin", 0, "\\d{6}-\\d{7}"),
					new RegexMo(mem, "img", 0, "[0-9a-zA-Z]{1,}.(jpg|jpeg|bmp|png|gif)"),
					new RegexMo(mem, "zipcode", 0, "[가-힣ㄱ-ㅎ]{1,}") };

			try {
				for (RegexMo regexMo : arr) {
					regexMo.inputData();

					if (regexMo.qq.equals("pw확인") && !mem.data[1].equals(regexMo.an)) {
						throw new MyExcep(regexMo.qq, 1);
					}
				}

				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		mem.ppp();

	}

}
