package util_p;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

class StrStud2 {

	String name;

	int[] jum = new int[0];

	int tot, avg, rank;

	public StrStud2(String str) {

		StringTokenizer stk = new StringTokenizer(str, ",");

		name = (String) stk.nextElement();

		while (stk.hasMoreElements()) {
			addJum(stk.nextElement());
		}

		calc();
	}

	void addJum(Object nn) {
		int[] buf = new int[jum.length + 1];
		for (int i = 0; i < jum.length; i++) {
			buf[i] = jum[i];
		}
		buf[jum.length] = Integer.parseInt((String) nn);
		jum = buf;
	}

	void calc() {
		if (ScannerFileMain2.max < jum.length)
			ScannerFileMain2.max = jum.length;
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
		for (int i = 0; i < ScannerFileMain2.max - jum.length; i++) {
			ttt += "\t";
		}

		ttt += tot + "\t" + avg + "\t" + rank;
		System.out.println(ttt);
	}

	void rankCalc(StrStud2[] arr) {
		rank = 1;
		for (StrStud2 st : arr) {
			if (avg < st.avg)
				rank++;
		}
	}

}

public class ScannerFileMain2 {
	static int max = -1;
	static StrStud2[] arr;

	static void addStud(StrStud2 nn) {
		StrStud2[] buf = new StrStud2[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			buf[i] = arr[i];
		}
		buf[arr.length] = nn;
		arr = buf;
	}

	public static void main(String[] args) {
		try {
			InputStream inn = new FileInputStream("fff/exam.csv");

			arr = new StrStud2[0];

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

				addStud(new StrStud2(sc.nextLine()));
			}

			inn.close();

			for (StrStud2 st : arr) {
				st.rankCalc(arr);
				st.ppp();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
