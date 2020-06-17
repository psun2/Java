package theory;

class Outer {

	int a = 10;
	String b = "Outer Class";
	static int aa = 100;
	static String bb = "Static Outer Class";

	public Outer() {
		System.out.println("Outer 생성자 실행");
	}

	void outer_1() {
		System.out.println("outer_1() 메소드 실행");
		System.out.println(a + ", " + b + ", " + aa + ", " + bb);
		// 전부 접근 가능
	}

	static void outer_2() {
		System.out.println("static outer_2() 메소드 실행");
//		System.out.println(a + ", " + b + ", " + aa + ", " + bb);
		// static 외 맴버 변수 접근 불가
		System.out.println(aa + ", " + bb);
	}

	class Inner {

		int a = 1000;
		String b = "Inner Class";

		public Inner() {
			System.out.println("Inner 생성자 실행");
		}

		void inner_1() {
			System.out.println("inner_1() 메소드 실행");
			System.out.println(
					Outer.this.a + ", " + Outer.this.b + ", " + Outer.aa + ", " + Outer.bb + ", " + a + ", " + b);
			// outer inner 전부 접근 가능
		}

//		static void inner_2() {
		// inner 에서 static 생성 불가
//		}

	}

	static class StaticInner {

		int a = 10000;
		String b = "StaticInner Class 맴버 변수";
		static int aa = 100000;
		static String bb = "StaticInner Outer Class static 맴버변수";

		public StaticInner() {
			System.out.println("StaticInner 생성자 실행");
		}

		void staticInner_1() {
			System.out.println("staticInner_1() 메소드 실행");
//			System.out.println(Outer.this.a + ", " + Outer.this.b + ", " + Outer.aa + ", " + Outer.bb + ", " + this.a
//					+ ", " + this.b + ", " + this.aa + ", " + this.bb);
			System.out.println(
					Outer.aa + ", " + Outer.bb + ", " + this.a + ", " + this.b + ", " + this.aa + ", " + this.bb);
		}

	}

}

public class InnerMain {

	public static void main(String[] args) {

		Outer outer = new Outer();
		outer.outer_1();
		Outer.outer_2();

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();

		Outer.Inner oi = outer.new Inner();

		oi.inner_1();

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();

		Outer.StaticInner os = new Outer.StaticInner();
		
		os.staticInner_1();
	}

}
