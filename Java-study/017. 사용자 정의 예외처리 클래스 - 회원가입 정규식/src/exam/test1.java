package exam;

import java.util.Scanner;
import java.util.regex.Pattern;

public class test1 {

	static String inspect(String pattern, String element) {

		Scanner sc = new Scanner(System.in);
		String result;

		while (true) {
			System.out.print(element + " : ");
			result = sc.nextLine();
			try {
				if (!Pattern.matches(pattern, result))
					throw new Exception(element + " 유효성 탈락 "); // 예외클래스 생성해서 변경
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}

			break;
		}
		return result;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String id, pw, pwCheck, name, gender, email, hobby, ability, tell, phone, birthday;

		while (true) {

			String idPattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]*$"; // 아이디 유효성 검사
			id = inspect(idPattern, "아이디(영문과 숫자로만 가능)");

			String pwPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*?,./\\\\<>|_-[+]=\\`~\\(\\)\\[\\]\\{\\}])[A-Za-z[0-9]!@#$%^&*?,./\\\\<>|_-[+]=\\`~\\(\\)\\[\\]\\{\\}]{8,20}$";
			pw = inspect(pwPattern, "비밀번호(특수문자 1개, 대문자)");

			while (true) {
				System.out.print("비밀번호 재입력 : ");
				pwCheck = sc.nextLine();
				try {
					if (!pw.equals(pwCheck))
						throw new Exception("비번이 틀렸습니다."); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
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
					String birthdayPattern = "^[0-9]{6}$"; // 생년월일 유효성 검사
					if (!Pattern.matches(birthdayPattern, birthday))
						throw new Exception("아이디 유효성 탈락");
					if (Integer.parseInt(birthday.substring(2, 1)) >= 8)
						throw new Exception("1980년 이전 출생자만 가입가능");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			sc.close();
			System.out.println(name + " 님 회원 가입 되었습니다.");
			break;
		}

	}

}
