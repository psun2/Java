package basic;

public class Exam {

	public static void main(String[] args) {

		int number = 1;
		String name = "홍길동";
		int kor = 90;
		int eng = 91;
		int math = 90;
		int total = kor + eng + math;
//		double avg =  total / 3;
//		double avg =  total / 3.0; // avg = 257/3
		// avg = 85.66666666666667
		double avg = (double) total / 3;

		int pos = 100;
		double ceil = 0.5;
//		double avg2 = avg * 100;
//		double avg3 = (double) ((int) avg2) / 100;
//		double avg2 = (double) ((int) ((double)avg * 100)) / 100;
//		double avg2 = (double) (int) (avg * 100) / 100;
		double avg2 = (double) (int) (avg * pos) / pos;
//		double avg3 = (double) avg2 + 0.01;
//		double avg3 = (double) (int) (avg * 100+0.5) / 100;
		double avg3 = (double) (int) (avg * pos + ceil) / pos;
//		System.out.println(avg + 0.005);

		System.out.println("번호 : " + number);
		System.out.println("이름 : " + name);
		System.out.println("국어 : " + kor);
		System.out.println("영어 : " + eng);
		System.out.println("수학 : " + math);
		System.out.println("총점 : " + total);
		System.out.println("평균1 : " + avg);
		System.out.println("평균2 : " + avg2);
		System.out.println("평균3 : " + avg3);

//		평균으로 수우미양가 출력하세요.
		String grade = avg >= 90 ? "수" : avg >= 80 ? "우" : avg >= 70 ? "미" : avg >= 60 ? "미" : avg >= 50 ? "양" : "가";
		System.out.println("학생성적 : " + grade);

		System.out.println("======================================");

//		ex) 국어 - 99, 영어 - 98, 수학 - 97 : 수(우등생)
//		ex) 국어 - 89, 영어 - 98, 수학 - 97 : 수

		if (avg3 >= 90) {
			if (kor >= 90 && eng >= 90 && math >= 90) {
				System.out.println("수 (우등생)");
			} else {
				System.out.println("수");
			}
		} else if (avg3 >= 80) {
			System.out.println("우");
		} else if (avg3 >= 70) {
			System.out.println("미");
		} else if (avg3 >= 60) {
			System.out.println("양");
		} else {
			System.out.println("가");
		}

		System.out.println("======================================");

		String res;
		if (avg3 >= 90) {
			res = "수";
			if (kor >= 90 && eng >= 90 && math >= 90)
				res += "(우등생)";
		} else if (avg3 >= 80) {
			res = "우";
		} else if (avg3 >= 70) {
			res = "미";
		} else if (avg3 >= 60) {
			res = "양";
		} else {
			res = "가";
		}

		System.out.println("얍!! " + res);

		System.out.println("======================================");

		String res3;
		switch ((int) avg3 / 10) {

		case 10:
		case 9:
			res3 = "수";
			break;
		case 8:
			res3 = "우";
			break;
		case 7:
			res3 = "미";
			break;
		case 6:
			res3 = "양";
			break;

		default:
			res3 = "가";
		}

		System.out.println("얍!! " + res3);

		System.out.println("======================================");

		System.out.printf("여기1\t" + avg + "\n");
		System.out.printf("여기2\t" + "%.2f\n", avg);
//		2번째 자리 까리 출력하되, 3번째 자리에서 반올림 합니다.
		System.out.printf("여기3\t" + "%.2f\n", 85.66666666666667);
		System.out.printf("여기4\t" + "%.2f\n", 33.333337);
		System.out.printf("여기5\t" + "%.2f\n", 33.366337);

	}

}
