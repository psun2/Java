package theory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleDateFormatMain {

	public static void main(String[] args) {

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

	}

}
