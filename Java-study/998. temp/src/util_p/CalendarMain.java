package util_p;

import java.util.Calendar;

public class CalendarMain {

	static void ppp(String ttt, Calendar day) {
		ttt += ":" + day.get(Calendar.YEAR) + "년 " + (day.get(Calendar.MONTH) + 1) + "월 " + day.get(Calendar.DATE)
				+ "일 (" + " 일월화수목금토".charAt(day.get(Calendar.DAY_OF_WEEK)) + "요일) " + day.get(Calendar.HOUR_OF_DAY)
				+ ":" + day.get(Calendar.MINUTE) + ":" + day.get(Calendar.SECOND) + "." + day.get(Calendar.MILLISECOND);

		System.out.println(ttt);
	}

	public static void main(String[] args) {

		// 시간, 날짜는 숫자다
		Calendar now = Calendar.getInstance(); // new가 아님
		// 싱글톤

		System.out.println(now);
		System.out.println(now.get(0));
		System.out.println(now.get(1));
		System.out.println(now.get(2));
		System.out.println(now.get(3));
		System.out.println(now.get(Calendar.YEAR)); // static final 2020
		System.out.println(now.get(Calendar.MONTH) + 1); // 6
		// 배열의 인덱스로 나옴 제대로 볼 때 + 1 해주면 됨
		System.out.println(now.get(Calendar.DATE)); // 4
		System.out.println(now.get(Calendar.DAY_OF_YEAR)); // 156
		System.out.println(now.get(Calendar.DAY_OF_WEEK)); // 5
		System.out.println(now.get(Calendar.WEEK_OF_YEAR)); // 올해들어 몇번째 주 23
		System.out.println(now.get(Calendar.WEEK_OF_MONTH)); // 1
		System.out.println(now.get(Calendar.AM_PM)); // 1 = pm
		System.out.println(now.get(Calendar.HOUR)); // 8
		System.out.println(now.get(Calendar.HOUR_OF_DAY)); // 20
		System.out.println(now.get(Calendar.MINUTE)); // 57
		System.out.println(now.get(Calendar.SECOND)); // 5
		System.out.println(now.get(Calendar.MILLISECOND)); // 185
		System.out.println(now.get(Calendar.ZONE_OFFSET)); // UTC 를 기준으로 몇시인지 32400000

		System.out.println();

		String[] 요일 = { "", "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };

		System.out.println(now.get(Calendar.YEAR) + " 년 " + (now.get(Calendar.MONTH) + 1) + " 월 "
				+ now.get(Calendar.DAY_OF_MONTH) + "일 " + "(" + 요일[now.get(Calendar.DAY_OF_WEEK)] + ") "
				+ now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + "."
				+ now.get(Calendar.MILLISECOND));

		// 년 월 일 시 분 초
		ppp("출력", now);
		now.set(1992, 3 - 1, 20, 16, 23, 45);
		now.set(1992, 19 - 1, 182, 516, 123, 45);
		now.set(1992, 19 - 1, 182, 516, 123);
		now.set(1998, 6 - 1, 2);
		now.set(Calendar.YEAR, 2017); // set은 정확한 날을 입력 받음
		ppp("출력", now);

		now.add(Calendar.YEAR, 5); // 추가적으로 더해줌 예 2017 년 + 5년
		ppp("출력", now);

		System.out.println(now.getTimeInMillis()); // 오늘 날짜에 따를 밀리세컨드
		System.out.println(now.getTimeInMillis() / (1000 * 60 * 60 * 24) / 365 + 1970); // 1970년 기준

		now.setTimeInMillis(0);
		ppp("출력", now); // => 한국 시간과 UTC 기준 9시간 차이나서 9시가됨

		now.set(1998, 7 - 1, 0);
		ppp("출력", now); // 6월의 마지막 날 구하기

		System.out.println(now.getActualMaximum(Calendar.MONDAY));
		System.out.println(now.getActualMaximum(Calendar.DATE));

//		while (true) {
//			int year = now.get(Calendar.YEAR);
//			int second = now.get(Calendar.SECOND);
//
//			try {
//				Thread.sleep(1000);
//				System.out.println(now.get(Calendar.YEAR) + " 년 " + (now.get(Calendar.MONTH) + 1) + " 월 "
//						+ now.get(Calendar.DAY_OF_MONTH) + "일 " + "(" + 요일[now.get(Calendar.DAY_OF_WEEK)] + ") "
//						+ now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":"
//						+ now.get(Calendar.SECOND) + "." + now.get(Calendar.MILLISECOND));
//				System.out.println(second + " 초");
//			} catch (Exception e) {
//				break;
//			}
//
//		}

		Calendar today = Calendar.getInstance();

		System.out.println(now.after(today)); // false
		// now 가 today보다 앞에 있니 ? false
		System.out.println(now.before(today)); // true
		// now 가 today보다 뒤에 있니 ? false

//		System.out.println(now.getTime());
//		System.out.println(now.getWeekYear());
//		System.out.println(now.getTime());

	}

}
