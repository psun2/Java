package oops;

class OverShape {
	String name;
	double area, border;

	void init(int r) {
		name = "원";
		double pi = 3.141592;
		area = r * r * pi;
		border = 2 * pi * r;
	}

	void init(int w, int h) {
		name = "직사각형";
		area = w * h;
		border = (w + h) * 2;
	}

	void init(int hh, double bb) {
		name = "직각삼각형";
		area = hh * bb / 2;
		border = Math.sqrt(hh * hh + bb * bb) + hh + bb;
	}

	void ppp() {
		System.out.println(name + ": " + area + ", " + border);
	}
}

public class OverShapeMain {

	public static void main(String[] args) {

		OverShape os = new OverShape();

		os.init(5);
		os.ppp();
		os.init(5, 6);
		os.ppp();
		os.init(5, 6.0);
		os.ppp();

	}

}
