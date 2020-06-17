package theory;

import java.util.regex.Pattern;

public class Sabun {

	public static void main(String[] args) {

		String sabun = "a20020122_lsh";
		String sabun2 = "ab20020122lsh";

		if (!Pattern.matches("[a-d][0-9]{8}_[a-z]{2,4}", sabun)) {
			System.out.println("에러");
		} else {
			System.out.println("정상");
		}
		
		if (!Pattern.matches("[a-d][0-9]{8}_[a-z]{2,4}", sabun2)) {
			System.out.println("에러");
		} else {
			System.out.println("정상");
		}

	}

}
