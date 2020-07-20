package dynamic_P;

class InheritShape { // 부모는 자식을 포함하지 않습니다.
	String name;

	double border, area;

	void calc() {
		System.out.println("부모의 계산을 받아랐");
	}

	void cirCalc() {
		System.out.println("부모의 계산을 받아랐");
	}

	void triCalc() {
		System.out.println("부모의 계산을 받아랐");
	}

	void ppp() {
//		this.calc(); // 이렇게 해도 안됨
		calc(); // 오버라이딩 된 자식의 메소드를 호출합니다.
		cirCalc();
		triCalc();
		System.out.println(name + ": " + area + ", " + border);
	}

}

class InheriRec extends InheritShape { // 자식은 부모를 포함합니다.

//	hide : 숨는 장소
//	hidding => String name;

	double pi, r;

	InheriRec(int r) {
		name = "원";
//		double pi = 3.141592;
//		area = r * r * pi;
//		border = 2 * pi * r;
		this.pi = 3.141592;
		this.r = r;
	}

	void cirCalc() {
		System.out.println("자식의 오버라이딩 계산이다.");
		area = r * r * pi;
		border = 2 * pi * r;
	}

	int w, h;

	InheriRec(int w, int h) {
		name = "직사각형";
//		area = w * h;
//		border = (w + h) * 2;
		this.w = w;
		this.h = h;
	}

	void calc() {
		System.out.println("자식의 오버라이딩 계산이다.");
		area = w * h;
		border = (w + h) * 2;
	}

	int hh;
	double bb;

	InheriRec(int hh, double bb) {
		name = "직각삼각형";
		this.hh = hh;
		this.bb = bb;
//		area = hh * bb / 2;
//		border = Math.sqrt(hh * hh + bb * bb) + hh + bb;
	}

	void triCalc() {
		System.out.println("자식의 오버라이딩 계산이다.");
		area = hh * bb / 2;
		border = Math.sqrt(hh * hh + bb * bb) + hh + bb;
	}

}

public class InheritShapeMain {

	public static void main(String[] args) {

		InheriRec[] rec = { new InheriRec(5, 6), new InheriRec(5), new InheriRec(5, 6) };

		for (InheriRec inheriRec : rec) {
			inheriRec.ppp(); // 상속을 받아 부모의 메소드 를 사용합니다.
		}

		InheriRec[] clone = rec.clone();

	}

}
