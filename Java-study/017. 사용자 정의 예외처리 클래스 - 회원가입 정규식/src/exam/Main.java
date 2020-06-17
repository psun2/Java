package exam;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

interface CreateFunction {

	UserData add(UserData[] data);

}

class MyException extends Exception {

	String massage;

	public MyException(String massage) {
		super();
		this.massage = massage;
	}

	void printMsg() {
		System.out.println(massage + " 유효성 검사 탈락 다시 확인해 주세요");
	}

}

class UserData { // 회원 정보를 담고 있는 클래스

	private String[] info; // userID, userPW, userName, userGender, userEmail, uerHobby, userAbility,
	// userTell, userPhone, birthday;

	public UserData(String... info) {
		this.info = info;
	}

	public String[] getInfo() {
		String[] cloneArr = this.info.clone();
		return cloneArr;
	}

}

class CreateUser implements CreateFunction { // 회원 추가 클래스

	@Override
	public UserData add(UserData[] data) {

		Scanner sc = new Scanner(System.in);
		String id, pw, pwCheck, name, gender, email, hobby, ability, tell, phone, birthday;

		while (true) {

			String idPattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]*$"; // 아이디 유효성 검사
			id = inspect(idPattern, "아이디(영문과 숫자로만 가능)");

			try {
				for (int i = 0; i < data.length; i++) {
					if (data[i].getInfo()[0].equals(id))
						throw new Exception("아이디 중복");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}

			String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*?,./\\\\<>|_-[+]=\\`~\\(\\)\\[\\]\\{\\}])[A-Za-z[0-9]!@#$%^&*?,./\\\\<>|_-[+]=\\`~\\(\\)\\[\\]\\{\\}]{8,20}$";
			pw = inspect(pwPattern, "비밀번호(특수문자 1개, 대문자1, 소문자 1개 포함 필수)");

			while (true) {
				System.out.print("비밀번호 재입력 : ");
				pwCheck = sc.nextLine();
				try {
					if (!pw.equals(pwCheck))
						throw new MyException("비밀번호 확인"); // 예외클래스 생성해서 변경
				} catch (MyException e) {
					e.printMsg();
					continue;
				}
				break;
			}

			String namePattern = "^[가-힣]{2,20}$"; // 이름 유효성 검사 한글만 최소 2 자에서 20자 까지
			name = inspect(namePattern, "이름");

			String genderPattern = "^남|여{1}$"; // 성별 유효성 검사 남 또는 여 로만 입력 받음
			gender = inspect(genderPattern, "성별 (남 or 여)");

			String emailPattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]*@[a-zA-Z]{1}[a-zA-Z0-9_]*.[a-zA-Z]{1,10}$";
			email = inspect(emailPattern, "email");

			System.out.print("취미 : ");
			hobby = sc.nextLine();

			System.out.print("특기 : ");
			ability = sc.nextLine();

			String tellPattern = "^0[1-9]{1,2}-[0-9]{3,4}-[0-9]{4}$"; // 전화번호 유효성 검사
			tell = inspect(tellPattern, "전화번호(-(하이픈)포함하여 입력)");

			String phonePattern = "^010-[0-9]{3,4}-[0-9]{4}$"; // 핸드폰 번호 유효성 검사
			phone = inspect(phonePattern, "핸드폰(-(하이픈)포함하여 입력)");

			while (true) {
				System.out.print("출생년도 : ");
				birthday = sc.nextLine();
				try {
					String birthdayPattern = "^[0-9]{4}$"; // 생년월일 유효성 검사
					if (!Pattern.matches(birthdayPattern, birthday))
						throw new MyException("출생년도 (ex)1990)");
					try {
						if (Integer.parseInt(birthday.substring(0, 3)) >= 198) // 1980 년 이전 출생자만 받는 조건
//						if (Integer.parseInt(birthday.substring(0, 3)) >= 198 // 1980 년 이전 출생자만 받는 조건
//								&& Integer.parseInt(birthday.substring(0, 1)) != 1) // 2000년대 생 제외 
							throw new Exception("가입실패 : 1980년 이전 출생자만 가입가능");
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return null;
					}
				} catch (MyException e) {
					e.printMsg();
					continue;
				}
				break;
			}

			sc.close(); // inspect 함수에서 close() Error 메인 에서 한번만 close함
			System.out.println(name + " 님 회원 가입 되었습니다.");
			break;
		}

		return new UserData(id, pw, pwCheck, name, gender, email, hobby, ability, tell, phone, birthday);
	}

	private String inspect(String pattern, String element) {

		Scanner sc = new Scanner(System.in);
		String result;

		while (true) {
			System.out.print(element + " : ");
			result = sc.nextLine();
			try {
				if (!Pattern.matches(pattern, result))
					throw new MyException(element);
			} catch (MyException e) {
				e.printMsg();
				continue;
			}

			break;
		}
//		sc.close(); // id => pw 로 넘어갈때 scanner 가 닫혀 있어 예외 에러 발생 
		return result;
	}

}

public class Main {

	static UserData[] add(UserData[] data, CreateUser create) {

		UserData[] temp = new UserData[data.length + 1];

		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}

		temp[data.length] = create.add(data); // 회원가입시 아이디 중복 확인을 위해 회원 배열 을 인자로 줌

		data = temp;

		return data;

	}

	public static void main(String[] args) {

		// 회원가입 프로그램을 작성하세요
		// 입력사항 - id, pw, pw확인, 성명, 이메일, 취미, 특기, 전화번호, 핸드폰, 성별
		// 필수 입력사항 - id, pw, pw확인, 성명, 전화번호, 성별 (값이 없으면 에러 발생)
		// 유효성 검사 - 생년 : 1980 이전 출생자만 가입 가능
		// 비밀번호, 비밀번호 확인은 값이 같아야만 가능
		// 사용자 정의 예외 클래스를 활용하여 작업할 것

		CreateUser create = new CreateUser();
		UserData[] data = { new UserData("Dog", "!Qe123456", "!Qe123456", "아무개", "여", "Dog@naver.com", "물기", "깨물기",
				"02-0000-0000", "010-0000-0000") };

		data = add(data, create); // 회원가입 기능

		try {
			if (data[data.length - 1] == null) // 반환된 데이터의 정보가 null 일시
				throw new Exception("null 요소 제거");

			System.out.println(Arrays.toString(data));

			String[] info = data[data.length - 1].getInfo();

			for (String string : info) {
				System.out.print(string + "\t");
			}

		} catch (Exception e) { // 반환된 데이터의 정보가 null 일시 배열에서 null 제거

			System.out.println(Arrays.toString(data));

			UserData[] temp = new UserData[data.length - 1];

			for (int i = 0; i < data.length; i++) {
				if (data[i] != null) {
					temp[i] = data[i];
				}
			}

			data = temp;
			System.out.println(Arrays.toString(data));
			System.out.println(e.getMessage());
		}

//		아이디(영문과 숫자로만 가능) : HongGilDong
//		비밀번호(특수문자 1개, 대문자1, 소문자 1개 포함 필수) : !Qa123456
//		비밀번호 재입력 : !Qa123456
//		이름 : ghdrlfehd
//		이름 유효성 검사 탈락 다시 확인해 주세요
//		이름 : 홍길동
//		성별 (남 or 여) : 남성
//		성별 (남 or 여) 유효성 검사 탈락 다시 확인해 주세요
//		성별 (남 or 여) : 남
//		email : HongGilDong@gmail.com
//		취미 : 싸움
//		특기 : 축지법
//		전화번호(-(하이픈)포함하여 입력) : 02-0000-0000
//		핸드폰(-(하이픈)포함하여 입력) : 010-0000-0000
//		출생년도 : 1990
//		1980년 이전 출생자만 가입가능

//		홍길동 님 회원 가입 되었습니다.
//		HongGilDong	!Qa123456	!Qa123456	홍길동	남	HongGilDong@gmail.com	싸움	축지법	02-0000-0000	010-0000-0000	1443	

	}

}
