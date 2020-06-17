package exam;

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
		// sc.nextLine() 으로 받아온 text 를 Tokenizer 을 이용해
		// , 을 기준으로 Token을 냅니다.

		name = (String) stk.nextElement();
		// 맨처음에는 이름이 나오기때문에 바로 이름에 대입합니다.

		while (stk.hasMoreElements()) { // 다음 토큰이 있다면....

			addJum(stk.nextElement()); // 애드 함수

		}

		calc(); // 총점, 평균 계산
	}

	void addJum(Object nn) { // String 도 결국 최상위 에는 Object가 있습니다.

		// 받아오는 토큰의 수대로 점수 배열을 1칸씩 늘리면서 점수를 입력하는 메소드

		int[] buf = new int[jum.length + 1];

		for (int i = 0; i < jum.length; i++) {
			buf[i] = jum[i];
		}

		buf[jum.length] = Integer.parseInt((String) nn);
		// 문자열이기 때문에 Integer.parseInt 로 숫자형태로 변환 시켜 줍니다.

		jum = buf;
	}

	void calc() {

		if (ScannerFileMain2.max < jum.length) // 즉 점수 배열에 점수가 담겨 있다면...
			ScannerFileMain2.max = jum.length;
		// 맥스를 점수의 길이로 바꾸어 줍니다. why? 평균을 내기 위하여...

		tot = 0;

		for (int i : jum) {
			tot += i; // 총점
		}

		avg = tot / jum.length; // 평균
	}

	void ppp() { // 출력부 메소드

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

	void rankCalc(StrStud2[] arr) { // 등수 계산 메소드
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

		// StrStud2 타입의 받아온 객체를
		// StrStud2 타입의 배열을 한칸씩 늘리면서 학생을 추가

		StrStud2[] buf = new StrStud2[arr.length + 1];

		for (int i = 0; i < arr.length; i++) {

			buf[i] = arr[i];
		}

		buf[arr.length] = nn;

		arr = buf;

	}

	public static void main(String[] args) {
		try {
			InputStream inn = new FileInputStream("./exam.csv");
			// 파일을 불러옵니다.

			arr = new StrStud2[0];

			Scanner sc = new Scanner(inn);
			// 스캐너로 불러온 파일을 읽습니다.

			sc.nextLine(); // 맨위 title 한줄을 까고 시작합니다.

			while (sc.hasNext()) { // 스캐너로 불러올 Line이 존재 한다면...

				addStud(new StrStud2(sc.nextLine()));
				// 등수 와 총점 평균 계산이 끝낫다면
				// 계산이 끝난 객체를 addStud 함수를 통해
				// 배열의 index에 집어넣음
			}

			inn.close();

			for (StrStud2 st : arr) {
				st.rankCalc(arr); // 등수계산
				st.ppp(); // 출력
				// 등수별 정렬을 하지 않았기 때문에 그냥 출력
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		한가인	77	91	65	233	77	1
//		두가인	79	56	45	180	60	4
//		삼가인	78	28	78	184	61	3
//		사가인	61	12	91	164	54	5
//		오가인	86	32	76	194	64	2

	}
}
