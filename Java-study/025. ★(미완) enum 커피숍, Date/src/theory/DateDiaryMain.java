package theory;

import java.util.Date;

public class DateDiaryMain {

	public static void main(String[] args) {

		Date today = new Date();

		today = new Date(today.getYear(), today.getMonth() + 1, 0);
		// 7월의 0일 이면 6월 30일
		// today 를 6월 30 일로 설정

		int last = today.getDate(); // 마지막날 저장

		today.setDate(1); // 반복을 진행하기 위하여 1일로 설정

		for (int i = 0; i < today.getDay(); i++) { // 1일의 요일을 파악
			// 파악한뒤 맞는 요일까지 탭을 해주며 칸을 맞춤니다.
			System.out.print("\t");
		}

		for (int i = 1; i <= last; i++) {
			System.out.print(i + "\t");
			today.setDate(i);

			if (today.getDay() == 6) // 토요일이면 엔터
				System.out.println();
		}

	}

}