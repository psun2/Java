package util_p;

import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class ExamRegex {

	public static void main(String[] args) {

		Calendar today = Calendar.getInstance();

//		String companyNum = "a20020122_lsh";
		String companyNum = "a20100122_lsh";

		String[] info = new String[5];

		String 부서 = "[a-d][0-9]{8}_[a-z]{2,4}";

//		System.out.println(Pattern.matches(부서, companyNum));

		try {
			if (Pattern.matches(부서, companyNum)) {
				switch (companyNum.substring(0, 1)) {
				case "a":
					info[0] = "영업부"; // 부서
					info[1] = companyNum.substring(1, 9); // 입사일
					int 입사년 = Integer.parseInt(companyNum.substring(1, 5));

					if (today.get(Calendar.YEAR) - 입사년 == 10)
						System.out.println("10주년 ");

					System.out.println(입사년);
					break;
				case "b":
					info[0] = "인사부";
					break;
				case "c":
					info[0] = "두부";
					break;
				case "d":
					info[0] = "부부";
					break;
				}

			} else {
				throw new Exception("우리 회사 부서 코드가 아닙니다.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(Arrays.toString(info));

	}

}
