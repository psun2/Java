package theory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleDateFormatMiniExam {

	public static void main(String[] args) {

		// '89年 nov 28일 (화) 05:07:30 형태로 출력해 주세요

		Date date = new Date();

		String result = new SimpleDateFormat("''yy年 MMM dd일 (", new Locale("en")).format(date);
		result += new SimpleDateFormat("E) HH:mm:ss").format(date);

		System.out.println(result);
		// '20年 Jun 18일 (목) 00:28:02

		for (String string : "''YY年 MMM dd일 (E) HH:mm:ss".split(" ")) {
			String print = "";
			if (!string.equals("MMM"))
				print += new SimpleDateFormat(string, new Locale("ko")).format(date);
			else
				print += new SimpleDateFormat(string, new Locale("en")).format(date);

			System.out.print(print + " ");
			// '20年 Jun 18일 (목) 00:32:19
		}
	}

}
