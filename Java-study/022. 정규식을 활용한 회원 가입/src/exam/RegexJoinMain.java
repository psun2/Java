package exam;

import java.util.Scanner;
import java.util.regex.Pattern;

class RegexMo { // 데이터를 입력받고, Pattern.matches 를 하 는 클래스

	String[] pp;
	// pp = 답
	String qq, an;
	// qq = 질문, an = 답
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

class MyExcep extends Exception { // 예외처리 클래스

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

class RegexMem { // user의 정보를 가지고 있는 클래스

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

		String pp = ".*"; // 앞에 임의의 문자열이 있을 수 도 없을 수 도 있습니다.

		for (char ch : data[7].toCharArray()) { // 사용자가 입력한 초성 또는 풀네임의 문자열을 문자 배열로 만듭니다.

			// ex) 강 ㄴ

			int pos = index.indexOf(ch);
			// 강은 index에 없음, ㄴ 은 1번이 됨.

			if (pos >= 0) { // index 번호를 찾으므로, 최소 0과 같거나 0보다 큽니다.
				pp += jaeum[pos];
			} else { // 강같은 경우 else로 나와 pp 에 더해 집니다.
				pp += ch;
			}
		}

		pp += ".*"; // 더해진 pp에 뒤에 오는 문자열이 있을 수도 없을 수 도 있습니다.
		// 이때 pp 는 .*강[나-닣].* 이 됩니다.

		String res = ""; // Pattern 이 매치되는 구들이 더해집니다.

		for (String str : seoul) {
			if (Pattern.matches(pp, str)) { // 구 배열에서 .*강[나-닣].* 와 일치되는 패턴을 찾아 반환합니다.
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

		RegexMem mem; // 유저의 정보를 가질 수 있게 정의되어 있는 클래스를 선언 합니다.

		/*
		 * //// 회원 가입 유효성 검사를 실시하세요 1. 아이디 : 영문 숫자 조합 2. 비번 , 비번확인 3. 이메일 - 아이디:영문,숫자 @
		 * 도메인 : 영문 점 4. 전화번호 - 숫자 : ###-####-####, ##(#)-###-#### 5. 이름 (한글만 가능) 6.
		 * 주민번호 7. 사진 첨부-->영문,숫자.이미지 확장자 이미지(jpg, jpeg, bmp, png, gif) -- 대소문자 구분없음 8.
		 * 우편번호 검색 - 구단위 (초성검색) 예외처리로 처리할 것
		 */

		while (true) {

			mem = new RegexMem(); // while 문을 돌때마다 새로 생성 찌꺼기가 남지 않습니다. 즉 보안이 좋아 지게 됩니다.

			RegexMo[] arr = { new RegexMo(mem, "id", 0, "[0-9a-zA-Z]{2,}", ".*[0-9].*", ".*[a-zA-Z].*"),
					// RegexMem mem, String qq, int error, String... pp
					// 생성자의 인자로 정보를 가질수 있는 클래스와, title, errorNum 과 정규식을 보내 줍니다.
					// 정규식을 여러개를 보내줌으로 써 모든 정규식을 통과해야 Pattern 이 매치에 성공됩니다.
					// 그래서 (?=.) 와 같은 역할을 합니다.
					new RegexMo(mem, "pw", 0, ".{2,}"), new RegexMo(mem, "pw확인", 1, ".{2,}"),
					new RegexMo(mem, "email", 0,
							"[0-9a-zA-Z]{2,}@[a-zA-Z]{2,}.(([a-zA-Z]{2,})|([a-zA-Z]{2,}.[a-zA-Z]{2,}))"),
					new RegexMo(mem, "tel", 0, "((\\d{3}-\\d{4})|(\\d{2,3}-\\d{3}))-\\d{4}"),
					new RegexMo(mem, "name", 0, "[가-힣]{2,}"), new RegexMo(mem, "jumin", 0, "\\d{6}-\\d{7}"),
					new RegexMo(mem, "img", 0, "[0-9a-zA-Z]{1,}.(jpg|jpeg|bmp|png|gif)"),
					new RegexMo(mem, "zipcode", 0, "[가-힣ㄱ-ㅎ]{1,}") };

			try {
				for (RegexMo regexMo : arr) {
					regexMo.inputData(); // 입력받은 data를 가지고 정규식을 매치할 수 있게 정규식이 매치되어, data화 되는 함수를 작동합니다.

					if (regexMo.qq.equals("pw확인") && !mem.data[1].equals(regexMo.an)) {
						throw new MyExcep(regexMo.qq, 1); // 비밀번호가 일치 하지 않을시 예외처리 클래스에 질문과 해당 에러 메세지 출력의 번호를 줍니다.
					}
				}

				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		mem.ppp(); // 위에는 생성 전이지만 while 문에서 생성을 해주어 값은 가지고 있게됨 그래서 접근이 가능하게 됩니다.
		System.out.println(mem); // exam.RegexMem@7d4991ad

	}

}