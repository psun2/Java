package test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class IdNumCalendarByDateFormat {
	Date birthDay = new Date();

	public IdNumCalendarByDateFormat(String idNum) {
		SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
		try {
			birthDay = yyMMdd.parse(idNum.substring(0, 6));
		} catch (Exception e) {
			System.out.println("파싱 에러!");
		}
		readBirthDayCalender();
	}

	void readBirthDayCalender() {
		MakeCalender birthDayCalender = new MakeCalender(birthDay);
		System.out.println(birthDayCalender);
	}
}

class MakeCalender {
	ArrayList<String> birthDayMonthCalender;
	Date dDay;
	Date startingDate;

	MakeCalender(Date dDay) {
		this.dDay = dDay;
		startingDate = new Date(dDay.getYear(), dDay.getMonth(), 1);
		int lastDateInMonth = new Date(startingDate.getYear(), startingDate.getMonth() + 1, 0).getDate();
		birthDayMonthCalender = new ArrayList<String>();
		for (int i = 0; i < startingDate.getDay(); i++) {
			birthDayMonthCalender.add("   ");
		}
		for (int i = 1; i < lastDateInMonth + 1; i++) {
			birthDayMonthCalender.add(new String() + i);
		}
	}

	@Override
	public String toString() {

		System.out.println();
		System.out.println(birthDayMonthCalender); // ?
		System.out.println();

		String str = "";
		int i = 0;
		str += "\t<<<" + (dDay.getMonth() + 1) + "월>>>\n";
		for (String s : birthDayMonthCalender) {
			str += " ";
			try {
				if (Integer.parseInt(s) < 10) {
					str += " ";
				}
				if (Integer.parseInt(s) == dDay.getDate()) {
					str += "*";
					throw new Exception();
				}
				str += " ";
			} catch (Exception e) {
			}
			str += s;
			if (i++ == 6) {
				i = 0;
				str += "\n";
			}
		}
		return str;
	}
}

public class Hongs {

	public static void main(String[] args) {
		String idNum = "930409-1010049";
		IdNumCalendarByDateFormat a = new IdNumCalendarByDateFormat(idNum);
	}

}
