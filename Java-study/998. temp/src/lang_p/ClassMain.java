package lang_p;

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

	public ClaBBB(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "ClaBBB [a=" + a + ", b=" + b + "]";
	}

}

public class ClassMain {

	public static void main(String[] args) throws Exception {

		ClaAAA aa = new ClaAAA();
		Class cc = aa.getClass();
		System.out.println("cc : " + cc);
		ClaAAA aa1 = (ClaAAA) cc.newInstance();
		System.out.println("aa1 : " + aa1);
		System.out.println("aa.hashCode() : " + aa.hashCode());
		System.out.println("aa1.hashCode() : " + aa1.hashCode());
		System.out.println(aa == aa1); // false => 해쉬코드가 다름

		Class cc1 = Class.forName("lang_p.ClaAAA");
		System.out.println("cc1 : " + cc1);
		ClaAAA aa2 = (ClaAAA) cc.newInstance();
		System.out.println("aa2 : " + aa2);
		System.out.println("aa1.hashCode() : " + aa1.hashCode());
		System.out.println("aa2.hashCode() : " + aa2.hashCode());

		String className = "lang_p.ClaAAA";
		ClaAAA aa3 = (ClaAAA) Class.forName(className).newInstance();
		System.out.println("aa3 : " + aa3);
		System.out.println("aa3.hashCode() : " + aa3.hashCode());

		className = "lang_p.ClaBBB";
		// ClaBBB bb1 = (ClaBBB) Class.forName(className).newInstance();
		// getConstructor(), 생성자 타입을 지정한다.
		ClaBBB bb1 = (ClaBBB) Class.forName(className).getConstructor(String.class, String.class).newInstance("아기상어",
				"엄마상어");
		System.out.println("bb1 : " + bb1);
		System.out.println("bb1.hashCode() : " + bb1.hashCode());

		System.out.println();

		// getConstructor(), 생성자 타입을 지정한다. :: 인수는 가변 매개변수
		Class[] conArr = { String.class, String.class };

		ClaBBB bb2 = (ClaBBB) Class.forName(className).getConstructor(conArr).newInstance("아기상어", "엄마상어");
		System.out.println("bb2 : " + bb2);
		System.out.println("bb2.hashCode() : " + bb2.hashCode());

		System.out.println();

		Class[] conArr2 = { Class.forName("java.lang.String"), Class.forName("java.lang.String") };
		ClaBBB bb3 = (ClaBBB) Class.forName(className).getConstructor(conArr2).newInstance("아기상어", "엄마상어");
		System.out.println("bb3 : " + bb3);
		System.out.println("bb3.hashCode() : " + bb3.hashCode());

		System.out.println();

		String[] strArr = "java.lang.String,java.lang.String".split(",");

		Class[] conArr3 = new Class[strArr.length];

		for (int i = 0; i < conArr3.length; i++) {
			conArr3[i] = Class.forName(strArr[i]);
		}

		ClaBBB bb4 = (ClaBBB) Class.forName(className).getConstructor(conArr3).newInstance("아기상어", "엄마상어");
		System.out.println("bb4 : " + bb4);
		System.out.println("bb4.hashCode() : " + bb4.hashCode());

		System.out.println();

//		ClaAAA aa4 = new ClaAAA();
//		System.out.println(aa4.getClass().getName());
//		String name = aa4.getClass().getName();
//		Class cc2 = Class.forName(name);
//		ClaAAA aa5 = (ClaAAA) cc2.newInstance();
//		System.out.println("aa5 : " + aa5);
//		System.out.println("aa5.hashCode() : " + aa5.hashCode());

		// ClaBBB bb = new ClaBBB();
//
//		System.out.println(aa.toString());
//		System.out.println(bb.toString());

	}

}
