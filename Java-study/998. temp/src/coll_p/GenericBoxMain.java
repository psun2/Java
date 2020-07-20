package coll_p;

class GBox<TT, JJ> {

	String str;
	TT a, b;
	JJ c, d;

	public GBox(String str, TT a, TT b, JJ c, JJ d) {
		super();
		this.str = str;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	@Override
	public String toString() {
		return "GBox [str=" + str + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
	}

}

public class GenericBoxMain {

	public static void main(String[] args) {

		GBox<String, Integer> g1 = new GBox("g1", "김현준", "오민석", 100, 200);
		GBox<Boolean, Integer> g2 = new GBox("g2", true, false, 30, 40);
		GBox g3 = new GBox("g3", "김영재", false, 30, 40);
		// 모든 자료형이 다 사용 가능 합니다.
		// ArrayList 등등이 지금 Generic의 type을 설정해주지 않아,
		// 모든 Object 가 사용 됩니다.
		// 꺼내 쓸때가 문제가 생깁니다.

		System.out.println(g1);
		System.out.println(g2);
		System.out.println(g3);

//		g1.a = 123.456;
		g1.a = "장정호";
//		g2.a = "이호인";
		g2.a = false;

//		선언 및 생성시에 내가 사용하고자 하는 자료형을 지정 할 수 있습니다.

		g3.a = false;
		g3.a = "박성언";

		System.out.println(g1);
		System.out.println(g2);
		System.out.println(g3);
	}

}
