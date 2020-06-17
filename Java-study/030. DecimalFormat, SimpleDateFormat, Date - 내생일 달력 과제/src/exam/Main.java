package exam;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception {

		String input = "900512-0000000";

		Date birth = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");

		birth = format.parse(input.split("[-]")[0]);

		int myday = birth.getDate();

		birth = new Date(birth.getYear(), (birth.getMonth() + 1), 0);
		// 해당월의 마지막 날을 알 수 있음

		System.out.println(birth.getMonth() + 1); // 5
		System.out.println(birth.getDate()); // 31

		int last = birth.getDate(); // 달의 마지막 날까지 반복해야 함으로 저장

		format = new SimpleDateFormat("yyyy년 MM월");

		System.out.println(format.format(birth)); // 1990년 05월

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
