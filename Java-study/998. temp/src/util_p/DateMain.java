package util_p;

import java.util.Calendar;
import java.util.Date;

public class DateMain {

	public static void main(String[] args) {

		Date today = new Date();

		System.out.println("today : " + today);
		// Wed Jun 10 17:48:56 KST 2020

//		 Date date1 = new Date(2016, 5, 5);
//		 System.out.println(date1);
		// Mon Jun 05 00:00:00 KST 3916

		System.out.println();
		// 년, 월, 일 설정
		Date date1 = new Date(2016 - 1900, 5 - 1, 5);
		System.out.println("date1 : " + date1);
		// Thu May 05 00:00:00 KST 2016

		System.out.println();
		// 시간 설정 : 환산시켜 줍니다.
		Date date2 = new Date(2016 - 1900, 123 - 1, 95, 456, -213, 3124);
		System.out.println("date2 : " + date2);
		// Thu May 05 10:23:45 KST 2016
		// date2 : Sun Jun 21 21:19:04 KST 2026

		System.out.println();
		// 초기 설정 값
		Date date3 = new Date(0);
		System.out.println("date3 : " + date3);
		// Thu Jan 01 09:00:00 KST 1970
		// 9시 인 이유 : 대한민국은 세계 시각과 시차가 9시간

		System.out.println();
		// 정보 get
		System.out.println(today.getTimezoneOffset());
		System.out.println(today.getYear() + 1900);
		System.out.println(today.getMonth() + 1);
		System.out.println(today.getDate());
		System.out.println(today.getDay()); // 요일 int 형
		System.out.println(today.getHours());
		System.out.println(today.getMinutes());
		System.out.println(today.getSeconds());
		System.out.println(today.getTime() / (1000 * 60 * 60 * 24) / 365 + 1970);

		System.out.println();
		// set
		date1.setYear(1996 - 1900);
		date1.setMonth(9 - 1);
		date1.setDate(13);
		date1.setHours(17);
		date1.setMinutes(23);
		date1.setSeconds(41);
		System.out.println(date1);
		// Fri Sep 13 17:23:41 KST 1996
		date1.setTime(0);
		System.out.println(date1);
		// Thu Jan 01 09:00:00 KST 1970

		System.out.println();
		// after, before
		System.out.println(today.after(date1));
		// true
		System.out.println(today.before(date1));
		// false

		System.out.println();

		Calendar cc = Calendar.getInstance();
		CalendarMain.ppp("cc", cc);
		// cc:2020년 6월 10일 (수요일) 18:11:2.910

		cc.setTime(date1);
		CalendarMain.ppp("cc", cc);
		// cc:1970년 1월 1일 (목요일) 9:0:0.0

		cc.add(Calendar.YEAR, 15);
		System.out.println(date1);
		// Thu Jan 01 09:00:00 KST 1970

		date1 = cc.getTime();
		System.out.println(date1);
		// Tue Jan 01 09:00:00 KST 1985

		Date today1 = new Date();

		int todaydate = today1.getDate();

		today1.setDate(1); // 날짜를 1일로 설정

		today1.setMonth(today1.getMonth() + 2); // 6월의 마지막 날을 구하기 위해 7월달 설정
//		System.out.println(today1.getMonth()); // 7

		today1.setDate(today1.getDate() - 1); // 6월의 마지막 날을 받아오기 위해야 7월 1일에서 1을 빼줌
//		System.out.println(today1.getDate() - 1); // 30

		int last = today1.getDate() - 1;

		for (int i = 1; i <= last; i++) {

			String ttt = "";

			today1.setDate(i);

			if (today1.getDate() == 1) {
				for (int j = 1; j <= today1.getDay(); j++) {
					ttt += "\t";
				}
			}

			if (todaydate == today1.getDate()) {
				ttt += "[" + today1.getDate() + "]\t";
			} else {
				ttt += today1.getDate() + "\t";
			}

//			System.out.println(today1.getDate());

			int week = today1.getDay();
			if (week == 6)
				ttt += "\n";

			System.out.print(ttt);

		}

	}

}
