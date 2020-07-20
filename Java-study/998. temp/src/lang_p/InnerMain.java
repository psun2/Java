package lang_p;

// Inner 클래스 == (js) closer

class Outer { // 외부 클래스
	// 외부 클래스는 내부 클래스를 부를 수 없습니다.
	// but 외부에서 내부를 생성하고 맴버변수를 잡아,
	// 내부클래스를 이용 가능

	int a = 10;
	String b = "바깥양반";
	static int aa = 10;
//	static int dd = InnerStatic.d; //Error

	InnerMember mm = new InnerMember();
//	static InnerMember mm2 = new InnerMember(); // static 으로 만들수 없음

	InnerStatic ss = new InnerStatic();
	static InnerStatic ss2 = new InnerStatic();

//	InnerFunction ioc; // 내부 지역클래스 선언/생성/호출 불가

	void out_1() {
		System.out.println("out_1() 실행");
	}

	static void out_11() {

		System.out.println("static out_11() 실행");

	}

	void out_2() {
		System.out.println();
	}

	void out_3() {
		System.out.println("out_3() 실행 >>>>>>>>>>");

		class InnerFunction { // 메소드 내부에서만 정의/선언/호출
			int e = 5656;

			void InnerFunction_1() {
				System.out.println("InnerFunction_1() 실행 : " + a + ", " + b + ", " + aa + ", " + e);
			}

			public InnerFunction() {
				System.out.println("InnerFunction() 생성자 실행");
			}
		}

		InnerFunction ioc = new InnerFunction();
		System.out.println("ioc.e : " + ioc.e);
		ioc.InnerFunction_1();
		System.out.println("out_3() 끝 >>>>>>>>>>");
	}

	public Outer() {
		System.out.println("Outer() 생성자 실행");
	}

	class InnerMember { // 내부 클래스

		int c = 3000;

		void InnerMember_1() {
			System.out.println("InnerMember_1() 실행");
			System.out.println("InnerMember_1() 실행  시작: 외부클래스의 맴버변수 접근 가능(클래스 정의부에서만..)" + a + ", " + b + ", " + c);
			out_1();
			System.out.println("out_1() : 내부클래스에서 호출된 외부 클래스의 메소드");
			System.out.println("InnerMember_1() 실행  끝: 외부클래스의 맴버변수 접근 가능(클래스 정의부에서만..)" + a + ", " + b + ", " + c);
		}

		public InnerMember() {
			System.out.println("InnerMember() 생성자 실행");
		}

	}

	static class InnerStatic {

		int d = 4444;

		void InnerStatic_1() {
			System.out.println("InnerStatic_1() 시작" + aa + ", " + d);
			out_11(); // static class 이므로, static 만 접근 가능
			System.out.println("InnerStatic_1() 끝 ---------------------");
		}

		public InnerStatic() {
			System.out.println("InnerStatic() 생성자 실행");
		}

	}
}

public class InnerMain {

	public static void main(String[] args) {

		Outer.InnerMember oi = new Outer().new InnerMember();
		// 위 와 같은 방법으로도 내부 클래스를 사용 가능

		System.out.println("----------------------");

		Outer oo = new Outer();
		System.out.println("----------------------");
//		Outer.InnerMember im = new Outer.InnerMember(); // Error
//		Outer.InnerMember im =  Outer.new InnerMember(); // Error
		Outer.InnerMember im = oo.new InnerMember();
		System.out.println("----------------------");
//		Outer.InnerFunction ioc;
//		oo.out_3().InnerFunction ioc;
		// 지역 내부 클래스는 메인에서 선언/생성/호출 불가

		System.out.println("----------------------");
		// 내부 클래스를 외부클래스를 이용하여 밖에서 생성할 수 있습니다.
		// (내부 클래스도 싱글톤이 가능한가 ?)
		System.out.println("im.c : " + im.c);
		im.InnerMember_1();
//		System.out.println("im.a : " + im.a);
		// 외부 클래스의 변수 접근 불가

		System.out.println("----------------------");
		Outer ooAno = new Outer() { // 익명 클래스 정의
			class Ano {
				int f = 7777;

				void ano_1() {
					System.out.println("anno_1() 실행" + a + ", " + b + ", " + aa + ", " + f);
				}

				public Ano() {
					System.out.println("Anno() 생성자 실행");
				}
			}

			Ano an = new Ano();
//			an.ano_1(); // 정의부 이기 때문에 실행 구문을 넣을 수 없습니다.

//			public Outer() {
//				nnnee();
//			}

			@Override
			void out_2() {
				System.out.println("out_2() 재정의 실행 시작 ");
				an.ano_1();
				System.out.println("out_2() 재정의 실행 끝 ");
			}

			void nnnee() {
				System.out.println("생성에만 있는 nnnee() 재정의 실행 시작 ");
//				an.ano_1();
				System.out.println("생성에만 있는 nnnee() 재정의 실행 ");
			}


		};
		System.out.println("----------------------");
		// static 이므로, 위 와는 다르게 생성이 됩니다.

		Outer.InnerStatic is1 = new Outer.InnerStatic();
//		Outer.InnerStatic is2 =  Outer new.InnerStatic();
//		Outer.InnerStatic is2 = oo.new InnerStatic();
		System.out.println("----------------------");
		System.out.println("im.c : " + im.c);
		im.InnerMember_1();
//		System.out.println("im.a : " + im.a);
//		im.out_1(); // Error

		System.out.println("is1.d:" + is1.d);
		is1.InnerStatic_1();

		// System.out.println("Outer.InnerStat:"+Outer.InnerStat.d);

		oo.out_3();

		// oo.in.InnerMember_1(); // 외부클래스의 내부에서 생성시 접근하는법;

		ooAno.out_1();
		ooAno.out_2();
		ooAno.out_3();
//		ooAno.nnnee(); // 익명 메소드이므로, 호출 불가

	}

}
