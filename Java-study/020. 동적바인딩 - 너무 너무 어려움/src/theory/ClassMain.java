package theory;

import java.util.Arrays;

class ClaAAA {
	int a = 10;
	String b = "양다리 미워";

	@Override
	public String toString() {
		return "ClaAAA [a=" + a + ", b=" + b + "]";
	}

}

class ClaBBB {
	String a = "그럼 난 오다리";
	String b = "투다리 미워";
	int c = 10;

	public ClaBBB(String a, String b, Integer c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String toString() {
		return "ClaBBB [a=" + a + ", b=" + b + ", c=" + c + "]";
	}

}

class ClaCCC {
	String a = "그럼 난 오다리";
	String b = "투다리 미워";
	int c = 10;

	public ClaCCC(Integer c, String... strings) {

		super();
		this.a = strings[0];
		this.b = strings[1];
		this.c = c;

	}

	@Override
	public String toString() {
//		return "ClaCCC [a=" + a + ", b=" + b + ", c=" + c + "]";
		return "ClaCCC [a=" + a + ", b=" + b + "]";
	}

}

class ClaDDD {
	String[] arr;

	public ClaDDD(String[] strings) {
		super();
		this.arr = strings;

	}

	@Override
	public String toString() {
		return "ClaDDD [arr=" + Arrays.toString(arr) + "]";
	}

}

public class ClassMain {

	public static void main(String[] args) throws Exception {

		// 기본적인 인스턴스 생성방법
		System.out.println("기본적인 인스턴스 생성방법");
		ClaAAA a1 = new ClaAAA();
		System.out.println(a1);

		System.out.println();

		// Class 를 활용한 인스턴스 생성방법
		System.out.println("Class 를 활용한 인스턴스 생성방법");
		Class c = a1.getClass();
//		Class c = a1.getClass().getName(); // error 평소 getClass().getName() 이 명령을 해야 보였던것이 getClass() 하나로 됨.
		System.out.println(c); // theory.ClaAAA
		ClaAAA a2 = (ClaAAA) c.newInstance(); // Class class 로 인스턴스 생성
		System.out.println(a2);

		System.out.println();

		// Class class 를 활용한 쪼개서 인스턴스 생성
		System.out.println("Class class 를 활용한 쪼개서 인스턴스 생성");
		String className = "theory.ClaAAA";
		ClaAAA a3 = (ClaAAA) Class.forName(className).newInstance();
		System.out.println(a3);

		System.out.println();

		// 생성자로 맴버변수를 초기화 시켜줄때의 인스턴스 생성
		System.out.println("생성자로 맴버변수를 초기화 시켜줄때의 인스턴스 생성");
		className = "theory.ClaBBB";
		ClaBBB b1 = (ClaBBB) Class.forName(className).getConstructor(String.class, String.class, Integer.class)
				.newInstance("아기상어", "엄마상어", 1000);
		System.out.println(b1);

		System.out.println();

		// 생성자로 맴버변수를 초기화 시켜줄때의 인스턴스 생성 다른방법
		System.out.println("생성자로 맴버변수를 초기화 시켜줄때의 인스턴스 생성 다른방법");
		className = "theory.ClaCCC";
//		Class[] prameters = { String.class, String.class, Integer.class };
//		Class[] prameters2 = { String.class[], Integer.class }; // 문법 error
//		Class[] prameters3 = { []String.class, Integer.class }; // 문법 error
//		Class[] prameters4 = { String[].class, Integer.class };
		Class[] prameters5 = { Integer.class, String[].class };

		String[] arguments = { "아기상어", "엄마상어" };
		ClaCCC c1 = (ClaCCC) Class.forName(className).getConstructor(prameters5).newInstance(1000, arguments);
		System.out.println(c1);

		System.out.println();
		className = "theory.ClaDDD";
		Class[] prameters6 = { String[].class };
		ClaDDD d1 = (ClaDDD) Class.forName(className).getConstructor(String[].class)
				.newInstance((Object) arguments);
		System.out.println(d1);

		System.out.println();
		ClaDDD d2 = new ClaDDD(arguments);
		System.out.println(d2);

		System.out.println(String.class);
		System.out.println(String[].class);

	}

}
