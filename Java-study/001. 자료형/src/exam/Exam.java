package exam;

public class Exam {
	public static void main(String[] args) {

		int number = 1;
		String name = "홍길동";
		int kor = 90, eng = 91, mat = 92;
		int total = kor + eng + mat;
		double avg = total / 3;

		System.out.println("번호 : " + number);
		System.out.println("이름 : " + name);
		System.out.println("국어 : " + kor);
		System.out.println("영어 : " + eng);
		System.out.println("수학 : " + mat);
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + avg);

		String grade = avg >= 90 ? "수" : avg >= 80 ? "우" : avg >= 70 ? "미" : avg >= 70 ? "양" : "가";

		System.out.println(grade);
		
	}
}
