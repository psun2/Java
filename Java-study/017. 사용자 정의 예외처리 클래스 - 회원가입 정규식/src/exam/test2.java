package exam;

import java.util.Scanner;
import java.util.regex.Pattern;

public class test2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // 특수문자 No
		String id, pw, pwCheck, name, gender, email, hobby, ability, tell, phone, birthday;
		while (true) { // 전체 적인

			while (true) {

				System.out.print("아이디 : ");
				id = sc.nextLine();
				try {
					String idPattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]*$"; // 아이디 유효성 검사
					if (!Pattern.matches(idPattern, id))
						throw new Exception("아이디 유효성 탈락"); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}

				break;
			}

			while (true) {

				System.out.print("비밀번호 : ");
				pw = sc.nextLine();
				try {
					String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*?,./\\\\<>|_-[+]=\\`~\\(\\)\\[\\]\\{\\}])[A-Za-z[0-9]!@#$%^&*?,./\\\\<>|_-[+]=\\`~\\(\\)\\[\\]\\{\\}]{8,20}$";
					; // 비밀번호 유효성 검사
					if (!Pattern.matches(pwPattern, pw))
						throw new Exception("비번 유효성 탈락"); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}

				break;
			}

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

			while (true) {
				System.out.print("이름 : ");
				name = sc.nextLine();
				try {
					String namePattern = "^[가-힣]{2,20}$"; // 이름 유효성 검사 (한글로만 입력 받음)
					if (!Pattern.matches(namePattern, name))
						throw new Exception("이름은 한글로만 입력 됩니다."); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			while (true) {
				System.out.print("성별 (남 or 여) : ");
				gender = sc.nextLine();
				try {
					String genderPattern = "^남|여{1}$"; // 이름 유효성 검사 (한글로만 입력 받음)
					if (!Pattern.matches(genderPattern, gender))
						throw new Exception("성별 유효성에 통과하지 못했음."); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			while (true) {
				System.out.print("email : ");
				email = sc.nextLine();
				try {
					// String emailPattern =
					// "^[a-zA-Z0-9]([-_.]?[0-9a-zA-Z])*@[a-zA-Z0-9]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i";
					String emailPattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]*@[a-zA-Z]{1}[a-zA-Z0-9_]*.[a-zA-Z]{1,10}$";
					// email 유효성 검사
					if (!Pattern.matches(emailPattern, email))
						throw new Exception("이메일 형식이 올바르지 않습니다."); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			System.out.print("취미 : ");
			hobby = sc.nextLine();

			System.out.print("특기 : ");
			ability = sc.nextLine();

			while (true) {
				System.out.print("전화번호(-(하이픈)포함하여 입력) : ");
				tell = sc.nextLine();
				try {
					String tellPattern = "^0[0-9]{1,2}-[0-9]{3,4}-[0-9]{4}$";
					// email 유효성 검사
					if (!Pattern.matches(tellPattern, tell))
						throw new Exception("전화번호 형식이 올바르지 않습니다."); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			while (true) {
				System.out.print("핸드폰(-(하이픈)포함하여 입력) : ");
				phone = sc.nextLine();
				try {
					String phonePattern = "^010-[0-9]{3,4}-[0-9]{4}$";
					// email 유효성 검사
					if (!Pattern.matches(phonePattern, phone))
						throw new Exception("핸드폰 번호 형식이 올바르지 않습니다."); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			while (true) {
				System.out.print("생년월일(주민번호 앞자리) : ");
				birthday = sc.nextLine();
				try {
					String birthdayPattern = "^[0-9]{6}$"; // 아이디 유효성 검사
					if (!Pattern.matches(birthdayPattern, birthday))
						throw new Exception("아이디 유효성 탈락"); // 예외클래스 생성해서 변경
					if (Integer.parseInt(birthday.substring(0, 1)) >= 8)
						throw new Exception("1980년 이전 출생자만 가입가능"); // 예외클래스 생성해서 변경
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			}

			sc.close();
			System.out.println(name + "가입을 환영함");
			break;
		}

	}
}
