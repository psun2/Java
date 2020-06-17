package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {

	public static void main(String[] args) throws Exception {

		String input = "900512-0000000";

		Date birth = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

		birth = sdf.parse(input.split("[-]")[0]);

		int myday = birth.getDate();

		System.out.println(sdf.format(birth)); // 900512

		System.out.println(birth); // Sat May 12 00:00:00 KST 1990

		sdf = new SimpleDateFormat("yyyy-MM-dd (E)", new Locale("ko"));

		System.out.println(sdf.format(birth)); // 1990-05-12 (토)

//		System.out.println("dd : " + birth); // dd : Sat May 12 00:00:00 KST 1990

		birth = new Date(birth.getYear(), (birth.getMonth() + 1), 0);

//		System.out.println("dd2 : " + birth); // dd2 : Thu May 31 00:00:00 KST 1990

		int last = birth.getDate(); // 달의 마지막 날까지 반복해야 함으로 저장

//		System.out.println(last); // 31

		System.out.println(sdf.format(birth)); // 1990년 05월

		System.out.println("일\t월\t화\t수\t목\t금\t토");
		for (int i = 1; i <= last; i++) {

			birth.setDate(i); // 날짜를 1일로 다시 설정

			String result = "";

			if (i == 1) {
				int one = birth.getDay();
				for (int j = i; j <= one; j++) {
					result += "\t";
				}
			}

			if (i < 10)
				result += 0;

			if (birth.getDate() == myday)
				result += "[" + birth.getDate() + "]\t";
			else
				result += birth.getDate() + "\t";

			System.out.print(result);

			if (birth.getDay() == 6)
				System.out.println();

		}

	}

}
