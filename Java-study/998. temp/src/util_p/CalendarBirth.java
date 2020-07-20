package util_p;

import java.util.Calendar;

public class CalendarBirth {

	static void ppp(String ttt, Calendar day) {
		ttt += ":" + day.get(Calendar.YEAR) + "년 " + (day.get(Calendar.MONTH) + 1) + "월 " + day.get(Calendar.DATE)
				+ "일 (" + " 일월화수목금토".charAt(day.get(Calendar.DAY_OF_WEEK)) + "요일) " + day.get(Calendar.HOUR_OF_DAY)
				+ ":" + day.get(Calendar.MINUTE) + ":" + day.get(Calendar.SECOND) + "." + day.get(Calendar.MILLISECOND);

		System.out.println(ttt);
	}

	public static void main(String[] args) {

		Calendar today = Calendar.getInstance();

		Calendar birth = Calendar.getInstance();

		birth.set(today.get(Calendar.YEAR), 3 - 1, 20);

		// 3 - 1 = Month 로 따졋을때 3

		ppp("올해생일", birth);
		if (today.after(birth))
			birth.add(Calendar.YEAR, 1);

		ppp("다가올생일", birth);

		int birthWW = birth.get(Calendar.DAY_OF_WEEK);
		int mnt = 0;
		if (birthWW == 7)
			mnt = -1;
		if (birthWW == 1)
			mnt = -2;

		birth.add(Calendar.DATE, mnt);
		ppp("생일파티", birth);

		System.out.println("dday:" + (birth.getTimeInMillis() - today.getTimeInMillis()) / 1000 / 60 / 60 / 24);

	}

}
