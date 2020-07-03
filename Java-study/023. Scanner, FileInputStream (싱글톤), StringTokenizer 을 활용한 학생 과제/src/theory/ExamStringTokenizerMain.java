package theory;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class Info {

	int[] jum = new int[3];
	String name;
	int total, avg;

	Info(String str) {

		StringTokenizer stk = new StringTokenizer(str, "[,]");

//			System.out.println(stk.nextToken());
//			이름
//			국어
//			영어
//			수학
		// 분류 코드(,) 를 기준으로 한 Token씩 획득 합니다.

		name = stk.nextToken();
		jum[0] = Integer.parseInt(stk.nextToken());
		jum[1] = Integer.parseInt(stk.nextToken());
		jum[2] = Integer.parseInt(stk.nextToken());

		calc();

	}

	void calc() {
		for (int i : jum) {
			total += i;
		}

		avg = total / jum.length;
	}

	@Override
	public String toString() {

		String result = name + "\t";

		for (int i : jum) {
			result += i + "\t";
		}
		result += total + "\t" + avg;

		return result;

	}

}

public class ExamStringTokenizerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filName = "./exam.csv";

		try {
			FileInputStream students = new FileInputStream(filName);
			Scanner sc = new Scanner(students);

			// 맨 처음 윗줄의 title을 제거하기위해 Line을 하나 소모하고 갑니다.
			sc.nextLine();

			while (sc.hasNext()) {

//				System.out.println(sc.nextLine());
				// 이름,국어,영어,수학
				// file 의 한 라인씩 따 옵니다.

				Info info = new Info(sc.nextLine());
				System.out.println(info.toString());

			}

			sc.close();
			students.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

//		한가인	77	91	65	233	77
//		두가인	79	56	45	180	60
//		삼가인	78	28	78	184	61
//		사가인	61	12	91	164	54
//		오가인	86	32	76	194	64

	}

}
