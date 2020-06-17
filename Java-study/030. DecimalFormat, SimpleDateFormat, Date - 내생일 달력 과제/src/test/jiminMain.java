package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class jiminMain {
	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat();
		String minj = "940713-1234567"; // 민증
		String birth = minj.substring(0, 6); // 생년월일
		Date today = new Date(); // SimpleDateFormat을 받아주기 위해서 사용
		sdf = new SimpleDateFormat("yyMMdd");// 생년월일 구하는 format
		today = sdf.parse(birth); // 생일날짜로 맞춤

		System.out.println("생일 : " + today); // 생일 출력

		int min = 0;
		while (true) { // 7월 1일의 첫째 요일을 확인하기 위해서...

			String day = new SimpleDateFormat("yyMMdd").format(today);
			System.out.println(day);
			String m_day = new SimpleDateFormat("MM").format(today);
			System.out.println(m_day);
			int i_day = Integer.parseInt(day);
			System.out.println(i_day);
			i_day--;
			System.out.println(i_day);
			today = sdf.parse(Integer.toString(i_day));
			System.out.println(today);
			String chk = new SimpleDateFormat("MM").format(today);
			System.out.println(chk);
			if (!chk.equals(m_day)) { // 달이 바뀔때
				min = ++i_day;
				break;
			}
		}
		System.out.println("min : " + min);

		sdf = new SimpleDateFormat("yyMMdd");
		today = sdf.parse(Integer.toString(min)); // today=940701
		String july_first = new SimpleDateFormat("yyMMdd").format(today);// 94년 7월 1일
		System.out.println(july_first); // 940701
		String first_date = new SimpleDateFormat("E").format(today); // 7월 1일의 요일
		System.out.println(first_date); // 금

		int max = 0;
		while (true) { // 7월의 마지막 날짜를 구하는 과정

			String day = new SimpleDateFormat("yyMMdd").format(today);
			String m_day = new SimpleDateFormat("MM").format(today);
			int i_day = Integer.parseInt(day);
			i_day++;
			today = sdf.parse(Integer.toString(i_day));
			String chk = new SimpleDateFormat("MM").format(today);
			if (!chk.equals(m_day)) {// 달이 바뀔때의 숫자 -1이 그 요일의 마지막 날짜이다.
				max = --i_day;// 940731이 담김
				break;
			}
		}
		int max_date = max % 100; // 7월의 마지막 날짜

		String[] date_arr = { "일", "월", "화", "수", "목", "금", "토" };
		int cnt = 0;
		for (int i = 0; i < date_arr.length; i++) {
			if (first_date.equals(date_arr[i])) {
				cnt = i;
			}
		}

		sdf = new SimpleDateFormat("yyMMdd");
		today = sdf.parse(july_first); // 7월 1일로 맞춰준다
		String ttt = new SimpleDateFormat("yyyy년 MMM 달력").format(today);
		System.out.println(ttt);
		System.out.println("일\t월\t화\t수\t목\t금\t토");

		for (int i = 1; i <= max_date; i++) {
			String Line_date = new SimpleDateFormat("E").format(today); // 토요일을 찾기위해서 사용
			String m_day = new SimpleDateFormat("yyMMdd").format(today);
			int i_day = Integer.parseInt(m_day); // i_day =

			if (i == 1) {
				for (int j = 0; j < cnt; j++) {
					System.out.print("\t");
				}

			}
			if (i == Integer.parseInt(birth.substring(4, 6)))// 생일표시
				System.out.print("[");

			if (Line_date.equals("토")) {// 토요일이면 출력하고 줄바꿈
				System.out.print(i + "\t");
				System.out.println(" ");
			}

			else {
				if (i == Integer.parseInt(birth.substring(4, 6)))// 생일표시
					System.out.print(i + "]\t");
				else
					System.out.print(i + "\t");
			}

			i_day++; // 날짜를 하루 늘림 ex) 940701 -> 940702
			today = sdf.parse(Integer.toString(i_day));// 다음날로 바꿔줌
		}

	}
}