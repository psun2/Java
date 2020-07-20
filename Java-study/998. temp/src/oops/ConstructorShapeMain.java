package oops;

class ConstructorShape {

	String name;
	double area, border;

	ConstructorShape(int r) {
		name = "원";
		double pi = 3.141592;
		area = pi * r * r;
		border = pi * r * 2;
		ppp();
	}

	ConstructorShape(int w, int h) {
		name = "직사각형";

		area = w * h;
		border = (w + h) * 2;
		ppp();
	}

	ConstructorShape(int w, int h, int a) {
		name = "직각삼각형";

		area = w * h / 2;
		border = w + h + a;
		ppp();
	}

	void ppp() {
		System.out.println(name + ": " + area + ", " + border);
	}

}

public class ConstructorShapeMain {

	public static void main(String[] args) {

//		ConstructorShape[] arr = { new ConstructorShape(5), new ConstructorShape(5, 6), new ConstructorShape(5, 6, 7) };

//		for (ConstructorShape constructorShape : arr) {
//			constructorShape.ppp();
//		}

		new ConstructorShape(5); // 익명 생성과 동시에 모든 프로세스가 돌고 끝나서 사라집니다.
		new ConstructorShape(5, 6);
		new ConstructorShape(5, 6, 7);

	}

}
