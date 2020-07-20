package excep_p;

import java.util.Scanner;

interface Login {
	boolean loginID(String ID);

	boolean loginPW(String PW);

}

class Info implements Login {

	private String userName, userID, userPW;

	public Info(String userID, String userPW, String userName) {
		this.userID = userID;
		this.userPW = userPW;
		this.userName = userName;
	}

	@Override
	public boolean loginID(String ID) {

		if (userID.equals(ID))
			return true;

		return false;

	}

	@Override
	public boolean loginPW(String PW) {

		if (userPW.equals(PW))
			return true;

		return false;
	}

	void show() {
		System.out.println(this.userName + " 님 환영합니다.");
	}

}

public class ExamLoginMain {

	public static void main(String[] args) {

		Info[] arr = { new Info("aa", "1111", "이효리"), new Info("bb", "2222", "삼효리"), new Info("cc", "3333", "사효리"),
				new Info("dd", "4444", "오효리"), new Info("ee", "5555", "육효리"), new Info("tjddjs90", "1234", "박성언") };

		Scanner sc = new Scanner(System.in);

//		form: while (true) {
		while (true) {

			System.out.print("아이디를 입력하세요 : ");
			String ID = sc.nextLine();
			System.out.print("비밀번호를 입력하세요 : ");
			String PW = sc.nextLine();
			try {

				boolean bo = false;

				for (Info info : arr) {
					if (info.loginID(ID)) {
						if (bo = info.loginPW(PW)) {
							info.show();
//							break form;
						} else {
							throw new Exception("비밀번호 오류");
						}
					}
				}

				if (!bo)
					throw new Exception("아이디 오류");

				break;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		sc.close();

	}
}
