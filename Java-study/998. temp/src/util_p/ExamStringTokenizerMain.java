package util_p;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ExamStringTokenizerMain {

	public static void main(String[] args) {

		try {
			FileInputStream students = new FileInputStream("fff/exam.csv");

			Scanner sc = new Scanner(students);

//			int result = 0;

			while (sc.hasNext()) {
//				System.out.println(sc.nextLine());
				StringTokenizer stk = new StringTokenizer(sc.nextLine(), "[,]");

				String name = "";

				int total = 0, cnt = 0;

				double avg = 0;

				while (stk.hasMoreTokens()) {
//				while (stk.hasMoreElements()) {

//					System.out.println(stk.nextToken());

					String temp = stk.nextToken();

					try {

//						System.out.println(stk.nextToken());
//						System.out.println(temp);

//						total += Integer.parseInt(stk.nextToken());
						total += Integer.parseInt(temp);
//						total += Integer.parseInt((String) stk.nextElement());

						cnt++;

					} catch (Exception e) {

//						name = stk.nextToken();
						name = temp;
//						name = (String) stk.nextElement();

					}
				}

				if (total != 0) {
					avg = total / cnt;
//					System.out.println(name + " : " + total);
					System.out.println(name + " : " + total + " : " + avg);
				}

			}

			sc.close();
			students.close();

		} catch (Exception e) {

		}

	}

}
