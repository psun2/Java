package exam;

import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		
		Calendar now = Calendar.getInstance();
		int dd = now.get(Calendar.DATE);
		// now.set(2020, 8-1, 15);

		int last = now.getActualMaximum(Calendar.DATE);

		now.set(Calendar.DATE, 1);
		int first = now.get(Calendar.DAY_OF_WEEK);
		
		System.out.println(first);

		for (int i = 1; i < first; i++) {
			System.out.print("\t 여기가 멀 찍누");
		}

		for (int i = 1; i <= last; i++) {

			now.set(Calendar.DATE, i);
			int ww = now.get(Calendar.DAY_OF_WEEK);

			String tt = " ";

			if (dd == i)
				tt = "[";

			if (i < 10)
				tt += " ";

			tt += i;

			if (dd == i)
				tt += "]";

			System.out.print(tt + "\t");

			if (ww == 7)
				System.out.println();
		}

	}

}
