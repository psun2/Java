package theory;

import java.util.Calendar;
import java.util.Date;

public class DateMain {

	public static void main(String[] args) {

//		✔✔  버전업으로 인한 삭제 예정으로 Calendar 사용

		Date today = new Date();

		System.out.println("today : " + today);
		// today : Thu Jun 11 04:26:57 KST 2020

		Date error = new Date(2020, 06, 11);
		System.out.println("errorDate : " + error);
		// errorDate : Sun Jul 11 00:00:00 KST 3920
		// error 의 이유 : 설정한 날짜가 나오지 않습니다.

		System.out.println();
		System.out.println("년 월일 설정 ---------------------");
		Date date1 = new Date(2020 - 1900, 6 - 1, 11);
		System.out.println("date1 : " + date1);
		// date1 : Thu Jun 11 00:00:00 KST 2020

		System.out.println();
		System.out.println("시간 설정 ---------------------");
		Date date2 = new Date(2020 - 1900, 6 - 1, 11, 4, 30, 59);
		System.out.println("date2 : " + date2);
		// date2 : Thu Jun 11 04:30:59 KST 2020

		System.out.println();
		System.out.println("년 월 일 시간 환산 ---------------------");
		Date conversion = new Date((today.getYear() + 2020202), (today.getMonth() + 78), -1, 4567895, 1203345, 777);
		System.out.println("환산  date : " + conversion);
		// 환산 date : Sun Apr 20 14:57:57 KST 2022752

		System.out.println();
		System.out.println("초기설정 값 --------Calendar 와 동일-------------");
		Date date3 = new Date(0);
		System.out.println("date3 : " + date3);
		// date3 : Thu Jan 01 09:00:00 KST 1970
		// Calendar 와 동일 시간 역시 세계 표준 시각을 기준으로 의 시차인 9 시로 나옴

		System.out.println();
		System.out.println("set -------------");
		date1.setYear(1996 - 1900);
		date1.setMonth(9 - 1);
		date1.setDate(13);
		date1.setHours(17);
		date1.setMinutes(23);
		date1.setSeconds(41);
		System.out.println("set date1 : " + date1);
		// set date1 : Fri Sep 13 17:23:41 KST 1996

		System.out.println();
		System.out.println("초기값으로 변경 -------------");
		date1.setTime(0);
		System.out.println("setTime date1 : " + date1);

		System.out.println();
		System.out.println("Calendar 와 연동 -------------");
		Calendar cc = Calendar.getInstance();
		System.out.println("Calendar : " + cc);
		// Calendar :
		// java.util.GregorianCalendar[time=1591818197441,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Seoul",offset=32400000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2020,MONTH=5,WEEK_OF_YEAR=24,WEEK_OF_MONTH=2,DAY_OF_MONTH=11,DAY_OF_YEAR=163,DAY_OF_WEEK=5,DAY_OF_WEEK_IN_MONTH=2,AM_PM=0,HOUR=4,HOUR_OF_DAY=4,MINUTE=43,SECOND=17,MILLISECOND=441,ZONE_OFFSET=32400000,DST_OFFSET=0]
		System.out.println("Calendar.getTime() : " + cc.getTime());
		// Calendar.getTime() : Thu Jun 11 04:42:33 KST 2020
		date1 = cc.getTime();
		System.out.println("Calendar 와 연동 date1 : " + date1);
		// Calendar 와 연동 date1 : Thu Jun 11 04:41:36 KST 2020

		System.out.println();
		System.out.println("달력 -------------------------");

		Date today1 = new Date();

		int todaydate = today1.getDate();

//		System.out.println(todaydate); // 11 오늘 날짜

		today1.setDate(1); // 날짜를 1일로 설정

		today1.setMonth(today1.getMonth() + 2); // 6월의 마지막 날을 구하기 위해 7월달 설정
//		System.out.println(today1.getMonth()); // 7

		today1.setDate(today1.getDate() - 1); // 6월의 마지막 날을 받아오기 위해야 7월 1일에서 1을 빼줌
//		System.out.println(today1.getDate() - 1); // 30

		int last = today1.getDate() - 1; // 6월의 마지막 날까지 반복하기 위하여 설정값을 잡아줌

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
