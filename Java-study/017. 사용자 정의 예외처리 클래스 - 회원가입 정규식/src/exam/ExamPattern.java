package exam;

import java.util.regex.Pattern;

public class ExamPattern {

//	final static String email = "(^[a-zA-Z] {1}[a-zA-Z0-9_\.-]+)@";

	public static void main(String[] args) {

		String idPattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]*$"; // 아이디
		String id = "tjddjs90";

		String emailPattern = "[_a-zA-z0-9-\\.]+@[\\.a-zA-z0-9-]+\\.[a-zA-z]+$";
		String em = "tjddjs90@naver.com";

		if (Pattern.matches(idPattern, id))
			System.out.println("true");
		else
			System.out.println("false");

		if (Pattern.matches(emailPattern, em))
			System.out.println("true");
		else
			System.out.println("false");

	}

}
