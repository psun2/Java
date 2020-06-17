package theory;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

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

class Error<String, Integer> {

	String name;
	Integer age;
	ArrayList<String> arr;
	TreeSet<String> set;
	TreeMap<String, TreeMap> map;

	public Error(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
		this.arr = new ArrayList();
		this.set = new TreeSet();
		this.map = new TreeMap();
		print(name);
	}

	void print(String name) {
		arr.add(name);
		set.add(name);
		map.put(name, new TreeMap());
		System.out.println(name);
	}

}

public class GenericBoxMain {

	public static void main(String[] args) {

		GBox<String, Integer> g1 = new GBox("g1", "김현준", "오민석", 100, 200); // 같은 Generic 의 같은 type의 자료형을 넣어 줍니다.
		GBox<Boolean, Integer> g2 = new GBox("g2", true, false, 30, 40); // 같은 Generic 의 같은 type의 자료형을 넣어 줍니다.
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

		Error<String, Integer> error = new Error("asd", 12);
		System.out.println(error.name instanceof String);

	}

}
