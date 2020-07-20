package text_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleDateFormatMain {

	public static void main(String[] args) throws Exception {

		Date today = new Date();

		String[] ppArr = { "y", "yy", "yyy", "yyyy", "M", // M은 대문자를 사용
				"MM", "MMM", "MMMM", "d", "dd", "E", "EEEE", "F", // 이번달 들어서 몇번째 주 ? 6월 17일 기준 수요일은 3번째 주
				"z", "Z", "w", // 올해들어 몇번째주
				"ww", // 이번달 들어 몇번째 주
				"W", "WW", "a", "H", "HH", "h", "hh", "m", "mm", "s", "ss", "S", // 밀리세컨드
				"SSS" // 밀리세컨드
		};

		SimpleDateFormat sdf;
		for (String string : ppArr) {
			sdf = new SimpleDateFormat(string, new Locale("en"));
			// en : 미국, ko : 한국,
			System.out.println(string + " : " + sdf.format(today));
		}

		System.out.println();
		//// '89年 nov 28일 (화) 05:07:30 형태로 출력해 주세요
		String pattern = "`YY年 MMM dd일 (E) hh:mm:ss";

		for (String string : pattern.split(" ")) {
//			System.out.println(string);
			if (!string.equals("MMM"))
				sdf = new SimpleDateFormat(string, new Locale("ko"));
			else
				sdf = new SimpleDateFormat(string, new Locale("en"));
			System.out.print(sdf.format(today) + " ");
		}
		System.out.println();

//		`20年 Jun 17일 (수) 09:39:25 

		// '89年 nov 28일 (화) 05:07:30

		System.out.println();
		String ttt = new SimpleDateFormat("''yy年 MMM dd일 (", new Locale("en")).format(today);
		ttt += new SimpleDateFormat("E) HH:mm:ss").format(today);
		System.out.println(ttt);
		
		sdf = new SimpleDateFormat("yyyMMdd");
		today = sdf.parse("20131013");
		today = sdf.parse("20133099");
		System.out.println(today);
		
		// 주민등록 번호를 이용하여, 태어난 달에 해당하는 달력을 출력

	}

}
