package theory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

class StrStud {

	String name;

	int[] jum = new int[3];

	int tot, avg;

	public StrStud(String str) {

		StringTokenizer stk = new StringTokenizer(str, ",");

		name = stk.nextToken();

		jum[0] = Integer.parseInt(stk.nextToken());
		jum[1] = Integer.parseInt(stk.nextToken());
		jum[2] = Integer.parseInt(stk.nextToken());

		calc();
	}

	void calc() {
		tot = 0;
		for (int i : jum) {
			tot += i;
		}
		avg = tot / jum.length;
	}

	void ppp() {
		String ttt = name + "\t";
		for (int i : jum) {
			ttt += i + "\t";
		}
		ttt += tot + "\t" + avg;
		System.out.println(ttt);
	}

}

public class ScannerFileMain {

	public static void main(String[] args) {
		try {
			InputStream inn = new FileInputStream("./exam.csv");

			Scanner sc = new Scanner(inn);

//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.hasNext());

			// 이름,국어,영어,수학
			sc.nextLine(); // 위 한줄을 이 코드로 까고 들어와서 시작합니다.
			// 그래서 바로 이름과 점수를 받을 수 있습니다.

			while (sc.hasNext()) {

				new StrStud(sc.nextLine()).ppp();

			}

			inn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}