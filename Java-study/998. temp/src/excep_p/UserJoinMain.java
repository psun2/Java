package excep_p;

import java.util.Scanner;

//과제
//회원가입 프로그램을 작성하세요
//입력사항 - id, pw, pw확인, 성명,생년월일, 이메일, 취미, 특기, 전화번호, 핸드폰, 성별
//필수 입력사항 - id, pw, pw확인, 성명, 전화번호, 성별 (값이 없으면 에러 발생)
//유효성 검사 - 생년 : 1980 이전 출생자만 가입 가능
//       비밀번호, 비밀번호 확인은 값이 같아야만 가능
//사용자 정의 예외 클래스를 활용하여 작업할 것
//===================================================================
//회원가입 프로그램을 작성하세요
//입력사항 - id, pw, pw확인, 성명, 이메일, 취미, 특기, 전화번호, 핸드폰
//필수 입력사항 - id, pw, pw확인, 성명, 전화번호
//       비밀번호, 비밀번호 확인은 값이 같아야만 가능
//사용자 정의 예외 클래스를 활용하여 작업할 것

class UserData {
	String[][] data;

	public UserData() {
		String[] index = { "id", "pw", "성명", "생년월일", "이메일", "취미", "특기", "전화번호", "핸드폰", "성별" };

		data = new String[][] { index, new String[index.length] };
	}

	void input(UserQQ qq) {

		// System.out.println(qq.qq+">>"+qq.answer);
		for (int i = 0; i < data[0].length; i++) {

			if (qq.qq.equals(data[0][i])) {
				data[1][i] = qq.answer;
				// System.out.println(data[0][i]+">>"+qq.qq+":"+data[1][i]);
			}
		}
	}

	void ppp() {
		System.out.println("\t<<<< 회원가입정보 >>>>");
		for (int i = 0; i < data[0].length; i++) {
			System.out.println(data[0][i] + ":" + data[1][i]);
		}
	}

}

class UserQQ {
	UserData data;

	String qq, answer;
	boolean notNull = false;
	int eqNum = -1, year = 0, birth;

	public UserQQ(UserData data, String qq, boolean notNull) {
		super();
		this.data = data;
		this.qq = qq;
		this.notNull = notNull;
	}

	public UserQQ(UserData data, String qq, int year) {
		super();
		this.data = data;
		this.qq = qq;
		this.year = year;
	}

	public UserQQ(UserData data, String qq, boolean notNull, int eqNum) {
		this(data, qq, notNull);
		this.eqNum = eqNum;
	}

	void input() throws MyMyExcep {
		Scanner sc = new Scanner(System.in);

		System.out.print(qq + ":");
		if (year > 0) {
			birth = sc.nextInt();
			if (birth >= year)
				throw new MyMyExcep(qq, 2);

			answer = "" + birth;
		} else {
			answer = sc.nextLine();

			if (notNull && answer.equals(""))
				throw new MyMyExcep(qq, 0);

			if (eqNum >= 0 && !data.data[1][eqNum].equals(answer)) {
				throw new MyMyExcep(qq, 1);
			}
		}

	}

}

class MyMyExcep extends Exception {
	String name;
	int i;

	public MyMyExcep(String name, int i) {
		super();
		this.name = name;
		this.i = i;
	}

	void ppp() {

		String[] arr = { "는 필수입니다.", "이 일치하지 않습니다.", "유효하지 않는 값입니다." };

		System.out.println(name + " " + arr[i]);
	}
}

public class UserJoinMain {

	public static void main(String[] args) {
		UserData data;

		while (true) {

			data = new UserData();
			// id, pw, pw확인, 성명, 전화번호, 성별 (값이 없으면 에러 발생)
			UserQQ[] arr = { new UserQQ(data, "id", true), new UserQQ(data, "pw", true),
					new UserQQ(data, "pw확인", true, 1), new UserQQ(data, "성명", true), new UserQQ(data, "생년월일", 19800000),
					new UserQQ(data, "이메일", false), new UserQQ(data, "취미", false), new UserQQ(data, "특기", false),
					new UserQQ(data, "전화번호", true), new UserQQ(data, "핸드폰", false), new UserQQ(data, "성별", true) };
			try {

				for (UserQQ qq : arr) {
					qq.input();

					data.input(qq);
				}
				data.ppp();
				break;
			} catch (MyMyExcep e) {
				e.ppp();
			}

		}

	}

}
