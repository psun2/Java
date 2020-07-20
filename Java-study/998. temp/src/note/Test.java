package note;

import java.util.Calendar;

public class Test {

	public static void main(String[] args) {

		Calendar now = Calendar.getInstance();

		int todayMonth = now.get(Calendar.MONTH) + 1; // 이번달
		int today = now.get(Calendar.DATE); // 이번달의 오늘

		for (int i = 0; i < now.getActualMaximum(Calendar.MONTH); i += 3) {
			for (int j = i; j < i + 3; j++) {
				now.set(Calendar.MONTH, j);

				System.out.print(now.get(Calendar.MONTH) + 1 + " 월\t\t\t\t\t\t\t");
			}
			System.out.println();
			for (int j = i; j < i + 3; j++) {
				System.out.print("일요일\t월요일\t화요일\t수요일\t목요일\t금요일\t토요일\t");
			}
			System.out.println();

		}
	}

}
