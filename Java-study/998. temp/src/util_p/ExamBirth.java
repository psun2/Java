package util_p;

import java.util.Calendar;
import java.util.Scanner;

public class ExamBirth {

	public static void main(String[] args) {

//		Scanner sc = new Scanner(System.in);

//		System.out.println("주민번호 입력 (-) 포함  : ");

//		String jumin = sc.nextLine();
		String jumin = "900512-1203316";

		int frontNum = Integer.parseInt(jumin.split("-")[0]);
		int backNum = Integer.parseInt(jumin.split("-")[1]);
		int birthMonth = Integer.parseInt(jumin.split("-")[0].substring(2, 4));
		int birthDay = Integer.parseInt(jumin.split("-")[0].substring(4, 6));

//		System.out.println(frontNum + ", " + backNum + ", " + birthMonth + ", " + birthDay);

		Calendar myBirthDay = Calendar.getInstance();

//		System.out.println(myBirthDay.get(Calendar.YEAR));
		// System.out.println(myBirthDay.get(Calendar.MONTH));
		// System.out.println(myBirthDay.get(Calendar.DATE));

		myBirthDay.set(myBirthDay.get(Calendar.YEAR), birthMonth, birthDay);

//		System.out.println(myBirthDay.get(Calendar.YEAR));
		// System.out.println(myBirthDay.get(Calendar.MONTH));
		// System.out.println(myBirthDay.get(Calendar.DATE));
////////////////////////////////////////////////////////////////////////////////////////////////////////

		Calendar today = Calendar.getInstance();

		int todayYear = today.get(Calendar.YEAR);
		int todayMonth = today.get(Calendar.MONTH) + 1;
		int todayDate = today.get(Calendar.DATE);

		System.out.println(todayYear + ", " + todayMonth + ", " + todayDate);

		System.out.println(myBirthDay.after(today));
		// 내 생일이 투데이보다 앞에 있니? 지났을시 true
		// 안지 났을시 false

		String[] 요일 = { "", "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };

		Calendar basic = Calendar.getInstance();
		basic.set(basic.get(Calendar.YEAR), 1, 1);
		System.out.println(basic.get(Calendar.YEAR));
		System.out.println(basic.get(Calendar.MONTH));
		System.out.println(basic.get(Calendar.DATE));

		if (myBirthDay.after(today)) {// 생일지 지났을시 내년 나의 생일로 셋팅
			// 올해의 말일로 시간 셋팅
			basic.set(basic.get(Calendar.YEAR), basic.getActualMaximum(Calendar.MONTH),
					basic.getActualMaximum(Calendar.DATE));
			// 나의 생일에서 ~ 올해 말까지
			// 올해 즉 365 - 나의 생일까지 지난 일수
//			System.out.println(basic.get(Calendar.DAY_OF_YEAR) - myBirthDay.get(Calendar.DAY_OF_YEAR)); // 200 일
			int a = basic.get(Calendar.DAY_OF_YEAR) - myBirthDay.get(Calendar.DAY_OF_YEAR);

			myBirthDay.set(myBirthDay.get(Calendar.YEAR) + 1, birthMonth, birthDay);
			// 내년의 1월 1일로 시간 셋팅
			basic.set(basic.get(Calendar.YEAR) + 1, 1, 1);

			// 내년 나의 생일 - 내년 1월 1일 까지의 일수
//			System.out.println(myBirthDay.get(Calendar.DAY_OF_YEAR) - basic.get(Calendar.DAY_OF_YEAR));
			int b = myBirthDay.get(Calendar.DAY_OF_YEAR) - basic.get(Calendar.DAY_OF_YEAR);

			if (myBirthDay.get(Calendar.DAY_OF_WEEK) == 7)
//				System.out.println("원래 Dday : " + (a + b) + "회사 Dday : " + ((a + b) - 1) + "내년 "
//						+ 요일[myBirthDay.get(Calendar.DAY_OF_WEEK) - 1] + " 에 생일 파티를 해요");
			System.out.println("원래 Dday : " + (a + b) + "회사 Dday : " + ((a + b) - 1) + "내년 "
					+ 요일[myBirthDay.get(Calendar.DAY_OF_WEEK) - 1] + " 에 생일 파티를 해요");
			if (myBirthDay.get(Calendar.DAY_OF_WEEK) == 1)
				System.out.println("원래 Dday : " + (a + b) + "회사 Dday : " + ((a + b) - 2) + "내년 "
						+ 요일[myBirthDay.get(Calendar.DAY_OF_WEEK) - 2] + " 에 생일 파티를 해요");
//				System.out.println("원래 Dday : " + (a + b) + "회사 Dday : " + ((a + b) - 2));

			System.out.println(a + b);
		} else {

		}
	}

}

// Day 랑 요일