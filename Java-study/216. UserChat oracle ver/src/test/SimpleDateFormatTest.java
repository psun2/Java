package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

	public static void main(String[] args) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println(sdf.format(date));

		try {
			date = sdf.parse("2017-10-17 23:20:00");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);

		System.out.println("20/09/24 12:33:01.000000000".substring(0, "20/09/24 12:33:01.000000000".lastIndexOf(".")));

	}

}
