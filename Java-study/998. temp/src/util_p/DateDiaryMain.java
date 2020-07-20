package util_p;

import java.util.Date;

public class DateDiaryMain {

	public static void main(String[] args) {
		Date today = new Date();

		today = new Date(today.getYear(), today.getMonth() + 1, 0);

		int last = today.getDate();

		today.setDate(1);

		for (int i = 0; i < today.getDay(); i++) {
			System.out.print("\t");
		}

		for (int i = 1; i <= last; i++) {
			System.out.print(i + "\t");
			today.setDate(i);

			if (today.getDay() == 6)
				System.out.println();
		}

	}

}
