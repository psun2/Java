package util_p;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

class StrStud {

	String name;

	int[] jum = new int[0];

	int tot, avg;

	public StrStud(String str) {

		StringTokenizer stk = new StringTokenizer(str, ",");

		name = (String) stk.nextElement();

		jum[0] = Integer.parseInt((String) stk.nextElement());
		jum[1] = Integer.parseInt((String) stk.nextElement());
		jum[2] = Integer.parseInt((String) stk.nextElement());

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
			InputStream inn = new FileInputStream("fff/exam.csv");

			Scanner sc = new Scanner(inn);

//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.nextLine());
//			System.out.println(sc.hasNext());

			sc.nextLine();

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
