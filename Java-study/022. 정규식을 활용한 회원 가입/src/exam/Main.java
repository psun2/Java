package exam;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

class MyException extends Exception {

	String title;

	public MyException(String title) {
		super();
		this.title = title;
	}

	void printMsg() {
		System.out.println(title + " 유효성 검사 탈락 다시 확인해 주세요");
	}

}

class User {

	private String userID, userPW, userEmail, userTell, userName, userIdNum, userPhoto, userPostcode, date;

	public User(String userID, String userPW, String userEmail, String userTell, String userName, String userIdNum,
			String userPhoto, String userPostcode, String date) {
		super();
		this.userID = userID;
		this.userPW = userPW;
		this.userEmail = userEmail;
		this.userTell = userTell;
		this.userName = userName;
		this.userIdNum = userIdNum;
		this.userPhoto = userPhoto;
		this.userPostcode = userPostcode;
		this.date = date;
	}

	public String getUserID() {
		return userID;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userPW=" + userPW + ", userEmail=" + userEmail + ", userTell=" + userTell
				+ ", userName=" + userName + ", userIdNum=" + userIdNum + ", userPhoto=" + userPhoto + ", userPostcode="
				+ userPostcode + ", date=" + date + "]";
	}

}

class Regex {

	private String title, pattern, input;

	public Regex(String title, String pattern, String input) {
		this.title = title;
		this.pattern = pattern;
		this.input = input;
	}

	String inspect() {

		try {

			if (!Pattern.matches(pattern, input))
				throw new MyException(title);

			return input;

		} catch (MyException e) {
			e.printMsg();

			return null;
		}

	}

}

class Input {
	String title, pattern;

	public Input(String title, String pattern) {
		super();
		this.title = title;
		this.pattern = pattern;
	}

	String inputInspect() {

		Scanner sc = new Scanner(System.in);

		while (true) {

			try {
				System.out.print(title + " 입력: ");
				String userInput = sc.nextLine();
//				if (this.title.equals("사진(.확장자)")) // 대문자로 입력받을시 소문자로 바꾸어줌
//					userInput = userInput.split("\\.")[0] + "." + userInput.split("\\.")[1].toLowerCase();
//				정규식에서 (?i)로 대소문자 구분하는 방법을 사용 
				userInput = new Regex(title, pattern, userInput).inspect();
				if (userInput == null)
					throw new Exception();
				if (this.title.equals("비밀번호"))
					userInput = pwChk(userInput);
				if (this.title.equals("구 초성 또는 풀네임"))
					userInput = inputGuInspect(userInput);
				return userInput;

			} catch (Exception e) {
				continue;
			}

		}

	}

	String pwChk(String userInput) {

		Scanner sc = new Scanner(System.in);

		System.out.print("비밀번호 확인을 위한 재입력 : ");
		String userPWChk = sc.nextLine();

		try {
			if (!userInput.equals(userPWChk))
				throw new Exception(" 비밀먼호가 일치 하지 않습니다. ");
			return userPWChk;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			userPWChk = inputInspect();
			return userPWChk;
		}

	}

	String inputGuInspect(String userInput) {

		String[] seoulGu = { "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구",
				"마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강동구" };
		String[] pattern = { "[가-깋ㄱ]", "[나-닣ㄴ]", "[다-딯ㄷ]", "[라-맇ㄹ]", "[마-밓ㅁ]", "[바-빟ㅂ]", "[사-싷ㅅ]", "[아-잏ㅇ]", "[자-짛ㅈ]",
				"[차-칳ㅊ]", "[카-킿ㅋ]", "[타-팋ㅌ]", "[파-핗ㅍ]", "[하-힣ㅎ]", };
		String resultPattern = "";
		String result = "";
		String tempPattern = "[가 - 힣]";

		if (Pattern.matches(tempPattern, userInput)) { // 초성이 아니라면 구 배열을 돌면서 같은 값을 반환
			for (String gu : seoulGu) {
				if (gu.equals(userInput))
					return gu;
			}
		} else { // 초성이라면 정규식 배열을 돌면서 초성과 같은 정규식을 가져와서 result에 더함
			for (int i = 0; i < userInput.length(); i++) {
				String temp = userInput.substring(i, i + 1);
				for (int j = 0; j < pattern.length; j++) {
					if (Pattern.matches(pattern[j], temp))
						resultPattern += pattern[j];
				}
			}
			int cnt = -1;
			for (int i = 0; i < seoulGu.length; i++) { // result에 담긴 패턴을 다시 구 배열와 match 시켜서 해당 패턴에 맞는 구 를 반환
				if (Pattern.matches(resultPattern, seoulGu[i])) {
					result += seoulGu[i] + "\t";
					cnt++;
				}

			}
			if (cnt > 0 || cnt == -1) {
				System.out.println("해당 지역이 존재하지 않거나, 결과 값이 많습니다. 정확한 지역을 입력해주세요.");
				System.out.println(result);
				result = inputInspect();

			}
		}
		return result;

	}

}

class ChangeTime {

	Calendar today;

	public ChangeTime(Calendar today) {
		this.today = today;
	}

	@Override
	public String toString() {

		String[] week = { "", "일", "월", "화", "수", "목", "금", "토" };

		String result = "";

		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		int weekNum = today.get(Calendar.DAY_OF_WEEK);
		int houre = today.get(Calendar.HOUR);

		result = "[" + year + "-" + month + "-" + date + " (" + week[weekNum] + ") " + houre + "시" + "]";

		return result;
	}

}

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

//		 회원 가입 유효성 검사를 실시하세요
//		예외처리로 처리할 것 과제입니다

		User[] testUser = { new User("testID", "testPW", "test@test.test", "010-0000-0000", "testName",
				"000000-0000000", "test.jpg", "강남구", "[2020-6-6 (토) 3시]") };

//		1. 아이디 : 영문 숫자 조합
//		아이디는 숫자로 시작할수 없습니다.
		String idPattern = "[a-zA-Z]{1}[a-zA-Z0-9]*";
		String userID = new Input("아이디", idPattern).inputInspect();

		try {
			for (int i = 0; i < testUser.length; i++) {
				if (testUser[i].getUserID().equals(userID))
					throw new Exception("아이디가 중복 되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			userID = new Input("아이디", idPattern).inputInspect();
		}

//		2. 비번 , 비번확인 
		String pwPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+?])[A-Za-z0-9!@#$%^&*()\\-_=+?]{8,20}";
		String userPW = new Input("비밀번호", pwPattern).inputInspect();

//		3. 이메일  -  아이디:영문,숫자   @  도메인 : 영문 점
		String emailPattern = "[a-zA-Z0-9]{1,}@[a-zA-Z]{1,}.[a-zA-Z]{1,}";
		String userEmail = new Input("이메일", emailPattern).inputInspect();

//		4. 전화번호 - 숫자 :  ###-####-####,   ##(#)-###-####
		String tellPattern = "0[0-9]{1,2}-[0-9]{3,4}-[0-9]{4}";
		String userTell = new Input("전화번호(- 포함)", tellPattern).inputInspect();

//		5. 이름 (한글만 가능)
		String namePattern = "[가-힣]*";
		String userName = new Input("이름", namePattern).inputInspect();

//		6. 주민번호 
		String idNumPattern = "[0-9]{2}(12|11|10|(0[1-9]))((0[1-9]|[1-2][0-9])|30|31)-[0-9]{7}";
		String userIdNum = new Input("주민번호(- 포함)", idNumPattern).inputInspect();

//		7. 사진 첨부-->영문,숫자.이미지 확장자
//		이미지(jpg, jpeg, bmp, png, gif)  --  대소문자 구분없음
		String extenderPattern = "[a-zA-Z0-9].*(?i)(.jpg|.jpeg|.bmp|.png|.gif)"; // (?i) 를 써서 대소문자를 무시합니다.
		String userPhoto = new Input("사진(.확장자)", extenderPattern).inputInspect();

//		8. 우편번호 검색 - 구단위 (초성검색)
		String postcodePattern = "[가-힣ㄱ-ㅎ]*";
		String userPostcode = new Input("구 초성 또는 풀네임", postcodePattern).inputInspect();

		Calendar today = Calendar.getInstance();

		User user = new User(userID, userPW, userEmail, userTell, userName, userIdNum, userPhoto, userPostcode,
				new ChangeTime(today).toString());

		System.out.println(user.toString());

//		.(임의의문자)*(글자수)
	}

}

//아이디 입력: testID
//아이디가 중복 되었습니다.
//아이디 입력: 아이디
//아이디 유효성 검사 탈락 다시 확인해 주세요
//아이디 입력: ID
//비밀번호 입력: q7q7q7q7q7q7q7
//비밀번호 유효성 검사 탈락 다시 확인해 주세요
//비밀번호 입력: !Qq123456
//비밀번호 확인을 위한 재입력 : 123456
// 비밀먼호가 일치 하지 않습니다. 
//비밀번호 입력: !Qq123456
//비밀번호 확인을 위한 재입력 : !Qq123456
//이메일 입력: test
//이메일 유효성 검사 탈락 다시 확인해 주세요
//이메일 입력: test@.
//이메일 유효성 검사 탈락 다시 확인해 주세요
//이메일 입력: test@test.test
//전화번호(- 포함) 입력: 01201-5784-0000
//전화번호(- 포함) 유효성 검사 탈락 다시 확인해 주세요
//전화번호(- 포함) 입력: 010-0000-0000
//이름 입력: name
//이름 유효성 검사 탈락 다시 확인해 주세요
//이름 입력: 이름
//주민번호(- 포함) 입력: 000000-0000000
//사진(.확장자) 입력: profile.bmq
//사진(.확장자) 유효성 검사 탈락 다시 확인해 주세요
//사진(.확장자) 입력: profile.bmp
//구 초성 또는 풀네임 입력: rer
//구 초성 또는 풀네임 유효성 검사 탈락 다시 확인해 주세요
//구 초성 또는 풀네임 입력: ㄱㄷㄱ
//User [userID=ID, userPW=!Qq123456, userEmail=test@test.test, 
//userTell=010-0000-0000, userName=이름, userIdNum=000000-0000000, 
//userPhoto=profile.bmp, userPostcode=강동구	, date=[2020-6-6 (토) 3시]]
