// 원의 넓이와 원의 둘레를 구하시오.
// 소수점으 2자리까지 표련

class Shape {

	String name;
	double circumference, area;

	public Shape(String name) {
		super();
		this.name = name;
	}

	void circle(double radius) {
		// 원의 넓이 => 파이 알의 제곱
		this.area = position(Math.PI * Math.pow(radius, 2));

		// 원의 둘레 => 2 파이 알
		this.circumference = position(2 * Math.PI * radius);

		ppp();

	}

	double position(double value) {

		double result = 0;

		int temp = (int) (value * 100);

		result = (double) temp;

		result = result / 100;

		return result;

	}

	void ppp() {

		System.out.println(toString());

	}

	@Override
	public String toString() {
		return "Shape [name=" + name + ", circumference=" + circumference + ", area=" + area + "]";
	}

}

public class Test1_Circle {

	public static void main(String[] args) {
		Shape circle = new Shape("원");

		circle.circle(5.0);
		// Shape [name=원, circumference=31.41, area=78.53]

	}

}
