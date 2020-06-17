package exam;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateTest {

	public static void main(String[] args) {

		Date date = new Date();

		System.out.println(date.toString()); // Tue Jun 02 04:24:40 KST 2020

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd"); 

		System.out.println("현재날짜 : " + formatDate.format(date)); // 현재날짜 : 2020-06-02

	}

}
