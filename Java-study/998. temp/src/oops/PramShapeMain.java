package oops;

class PramShape {
	String name;
	double border, area;

	void init(int... line) { // 원일때 반지름, 직사각형 w, h
		String[] title = { "원", "직사각형", "직각삼각형" };
		int p = line.length;
		double pi = 3.141592;

		name = title[p - 1];

		switch (p) {
		case 1:
			border = 2 * pi * line[0];
			area = line[0] * line[0] * pi;
			break;
		case 2:
			border = (line[0] + line[1]) * 2;
			area = line[0] * line[1];
			break;
		case 3:
			border = line[0] + line[1] + line[2];
			area = line[0] * line[1] / 2;
			break;
		}
	}

	void ppp() {
		String ttt = name + "\t" + border + "\t" + area;
		System.out.println(ttt);
	}
}

public class PramShapeMain {

	public static void main(String[] args) {

		PramShape ps = new PramShape();

		ps.init(5);
		ps.ppp();

		ps.init(5, 6);
		ps.ppp();

		ps.init(5, 6, 7);
		ps.ppp();
		
		//국어, 영어, 수학 - 일반
		// 국어, 영어, 수학, 예체능 - 특기
		// 총점, 평균까지 출력하세요.

	}

}
