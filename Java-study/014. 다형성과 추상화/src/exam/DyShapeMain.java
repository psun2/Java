package exam;

abstract class DyShape { // 추상화된 맴버변수나, 메소드가 있을시, abstract 키워드를 사용합니다.
	String name;

	double area, border;

	public DyShape(String name) {
		this.name = name;
	}

	abstract void calc(); // abstract 사용시 추상화 시키는 메소드는 body 가 없어야합니다.

	void ppp() {
		calc();
		String ttt = name + " : " + area + " : " + border;
		System.out.println(ttt);
	}
}

class DyRec extends DyShape {

	int w, h;

	public DyRec(int w, int h) {
		super("직사각형");
		this.w = w;
		this.h = h;
	}

	void calc() { // 추상화된 메소드를 구현하는 부분입니다.
		this.area = w * h;
		this.border = (w + h) * 2;
	}

}

class DyCir extends DyShape {

	int r;

	public DyCir(int r) {
		super("원");
		this.r = r;
	}

	void calc() { // 부모에도 존재하는 method 이므로, 메소드 영역에서 오버라딩 합니다.
		double pi = 3.141592;
		this.area = pi * r * r;
		this.border = 2 * pi * r;
	}

}

public class DyShapeMain {

	public static void main(String[] args) {

		DyShape[] arr = { new DyRec(10, 5), new DyCir(10), new DyRec(7, 3), new DyCir(3), };

		for (DyShape ds : arr) {
			ds.calc();
			ds.ppp();
		}

	}

}