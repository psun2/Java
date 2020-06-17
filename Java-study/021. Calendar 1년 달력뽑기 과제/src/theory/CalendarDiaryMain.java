package theory;

import java.util.Calendar;

public class CalendarDiaryMain {

	public static void main(String[] args) {

		Calendar now = Calendar.getInstance();
		int dd = now.get(Calendar.DATE);
		// now.set(2020, 8-1, 15);

		int last = now.getActualMaximum(Calendar.DATE); // 달의 맨 마지막 날을 get

		now.set(Calendar.DATE, 1); // 달의 1일은 항상 일요일이 아니다.
		int first = now.get(Calendar.DAY_OF_WEEK); // 1일은 이번주에서 무슨 요일 입니까?

		for (int i = 1; i < first; i++) { // 1부터 해당 요일전까지 반복을 돌면서 칸을 맞춰줍니다.
			System.out.print("\t");
		}

		for (int i = 1; i <= last; i++) {

			now.set(Calendar.DATE, i); // 달의 마지막날까지 돌면서 계속 set을 해줍니다.

			int ww = now.get(Calendar.DAY_OF_WEEK); // 일이 셋팅 될때마다 해당요일을 get 합니다.

			String tt = " ";

			if (dd == i)
				tt = "[";

			if (i < 10)
				tt += " ";

			tt += i;

			if (dd == i)
				tt += " ]";

			System.out.print(tt + "\t");

			if (ww == 7) // 토요일이면 즉 해당주에 마지막 날이면 출력후 줄바꿈을 출력합니다.
				System.out.println();
		}

	}

}