package text_p;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JuminDiaryMain {

	public static void main(String[] args) throws Exception {

		String jumin = "200620-1234567";

		String ym = jumin.substring(0, 4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMd");
		SimpleDateFormat ww = new SimpleDateFormat("E");
		DecimalFormat df = new DecimalFormat("00");

		int last = (int) (long) df.parse(new SimpleDateFormat("d").format(
				sdf.parse(jumin.substring(0, 2) + df.format(((long) df.parse(jumin.substring(2, 4)) + 1)) + "0")));

		String week = "일월화수목금토";

		for (char ch : week.toCharArray()) {
			System.out.print(ch + "\t");
		}
		System.out.println("\n---------------------------------------------------");

		for (int i = 0; i < week.indexOf(ww.format(sdf.parse(ym + 1))); i++) {
			System.out.print("\t");
		}

		for (int i = 1; i <= last; i++) {
			System.out.print(i + "\t");

			if (ww.format(sdf.parse(ym + i)).equals("토")) {
				System.out.println();
			}

		}

		System.out.println();
		System.out.println(
				"--------------------------------------------------------------------------------------------------");

		System.out.println(new SimpleDateFormat("yyMMdd")
				.parse(jumin.substring(0, 2) + ((long) new DecimalFormat().parse(jumin.substring(2, 4)) + 1) + "00"));

		System.out.println(new SimpleDateFormat("yyMMdd").parse("900512"));

		System.out.println(new SimpleDateFormat("MM").format(new SimpleDateFormat("yyMMdd").parse("900512")));

		// 달에 +1 월을 하고 일은 0 일이라면 ?

		System.out.println(new DecimalFormat("00").format(123.45));

		System.out.println(new SimpleDateFormat("MM").format(new SimpleDateFormat("yyMMdd").parse("900512")));// 05
		// 위 코드를 한번더 parse

		String a = new SimpleDateFormat("MM").format(new SimpleDateFormat("yyMMdd").parse("900512"));
		long b = (long) new DecimalFormat("#").parse(a);
		int c = (int) b;
		System.out.println("b : " + b); // b : 5
		System.out.println("c : " + c); // c : 5
		// 다음 달을 위하여 + 1
		System.out.println(c + 1); // 6
//		System.out.println((int) new DecimalFormat("#")
//				.parse(new SimpleDateFormat("MM").format(new SimpleDateFormat("yyMMdd").parse("900512"))) + 1); // Error
//		System.out.println((long) new DecimalFormat("#")
//				.format(new SimpleDateFormat("MM").format(new SimpleDateFormat("yyMMdd").parse("900512"))) + 1); // 6
		System.out.println((long) new DecimalFormat("00")
				.parse(new SimpleDateFormat("MM").format(new SimpleDateFormat("yyMMdd").parse("900512"))) + 1); // 6
		System.out.println((long) new DecimalFormat("#")
				.parse(new SimpleDateFormat("MM").format(new SimpleDateFormat("yyMMdd").parse("900512"))) + 1); // 6
		
		

		SimpleDateFormat asd = new SimpleDateFormat("dd");
		String asd2 = "123";
		System.out.println(asd.parse(asd2));

//		System.out.println(new SimpleDateFormat()
//				.parse(new SimpleDateFormat("MM").format(new SimpleDateFormat("yyMMdd").parse("900512"))));

//		System.out.println(new DecimalFormat("00")
//				.format();

	}

}
