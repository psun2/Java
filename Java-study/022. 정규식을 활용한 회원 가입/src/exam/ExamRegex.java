package exam;

import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class ExamRegex {

	static void inspect(String[] info, String sabun) {

		Calendar today = Calendar.getInstance();

		String companyNum = sabun;

		info[2] = companyNum.substring(1, 9); // 입사일

		int 입사년 = Integer.parseInt(companyNum.substring(1, 5));

		if (today.get(Calendar.YEAR) - 입사년 == 10)
			System.out.println("10주년 ");
		else if (today.get(Calendar.YEAR) - 입사년 > 10) {
			System.out.println((today.get(Calendar.YEAR) - 입사년) + "년 전에 입사 10주년이 였습니다.");
		} else {
			System.out.println("10주년이 " + (입사년 - today.get(Calendar.YEAR)) + "년 남았습니다.");
		}

		String[] week = { "", "일", "월", "화", "수", "목", "금", "토" };

		Calendar myBirth = Calendar.getInstance();

		myBirth.set(today.get(Calendar.YEAR), Integer.parseInt(info[0].substring(0, 2)) - 1,
				Integer.parseInt(info[0].substring(2, 4)));

		// ❗️ Calendar 의 달은 index의 번호처럼 뜨기 때문에 set 할땐 반듯이 -1 을 해 set을 해줍니다.

		if (today.after(myBirth)) { // today 가 myBirth 보다 앞에있니 ? 5월이므로 false
			System.out.println(today.after(myBirth));
			System.out.println("올해 생일은 지났어요.");
		} else {
			int myDate = myBirth.get(Calendar.DAY_OF_WEEK);
			if (myDate == 7)
				myDate -= 1;
			if (myDate == 1)
				myDate += 5;
			System.out.println("올해 생일파티는 " + week[myDate] + "요일 에 해요");
		}
	}

	public static void main(String[] args) {

		String[] info = new String[3];

//		String companyNum = "a20020122_lsh";
		String companyNum = "a20100122_lsh";

		String companyNumPattern = "[a-d][0-9]{8}_[a-z]{2,4}";

//		System.out.println(Pattern.matches(부서, companyNum));

		try {
			if (Pattern.matches(companyNumPattern, companyNum)) {
				switch (companyNum.substring(0, 1)) {
				case "a":
					info[0] = "0512"; // 생일
					info[1] = "영업부"; // 부서
					inspect(info, companyNum);
					break;
				case "b":
					info[0] = "0512"; // 생일
					info[1] = "인사부";
					inspect(info, companyNum);
					break;
				case "c":
					info[0] = "0512"; // 생일
					info[1] = "두부";
					inspect(info, companyNum);
					break;
				case "d":
					info[0] = "0512"; // 생일
					info[1] = "부부";
					inspect(info, companyNum);
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