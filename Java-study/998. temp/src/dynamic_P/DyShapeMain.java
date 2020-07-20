package dynamic_P;

abstract class DyShape {
	String name;

	double area, border;

	public DyShape(String name) {
		this.name = name;
	}

	abstract void calc(); // body 가 없어야합니다.

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

	void calc() {
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

	void ccc() {
		System.out.println("생성시 ccc() 정의");
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

		// FIXME abstract의 문제점
		DyShape ds = new DyCir(5);

		ds = new DyRec(8, 9);
		// 부모 타입으로 서클 자식을 인스턴스 화 했지만,
		// ds 가 부모의 다른 자식인 Rec의 주소도 할당 받을 수 있습니다.

		// ds = new DyShape("원이야");
		// Cannot instantiate the type DyShape
		// DyShape 유형을 인스턴스화할 수 없음
		// Error 의 이유!!!!!!!
		// abstract 로 설정한 calc 매소드가 정의 되지 않아서 Error 가 납니다.

		ds = new DyShape("원이야") { // 이런식의 재정의는 상속과 같게 생각하십니다.

//			area = 123; // 정의부기 때문에 Error 입니다.

			int a = 10;

			int border = 456; // 자식클래스의 정의부와 같다.
			// hiding 됩니다.

			void ccc() {
				area = 123;
				System.out.println(a);
				System.out.println("생성시 ccc() 정의");
			}
			// DyShape 는 ccc메소드가 존재 하지 않음
			// 그래서 호출 할 수 없습니다.
			// 하지만 밑에 오버라이드한 메소드에서 호출 시켜준다면, 의미있게 바뀔 수 있습니다.

			@Override
			void calc() {
				ccc();
				// 오버라이딩이 되면서, 에러가 사라집니다.
				System.out.println("생성시 calc 정의");
			}

		};

		ds.calc();
		// ds.ccc(); // 접근 불가
		// The method ccc() is undefined for the type DyShape
		// 메서드 ccc()가 DyShape 유형에 대해 정의되지 않음
		ds.ppp();

	}

}
