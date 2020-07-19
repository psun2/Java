package theory;

class CastPar {

	String name = "부모";
	int a = 10, b = 20;

	void meth_1() {
		System.out.println("부모 meth_1():" + a + "," + b);
	}

	void meth_2() {
		System.out.println("부모 meth_2()");
	}

}

class CastChild extends CastPar {

	String name = "자식";
	int a = 1000, c = 2000;

	void meth_1() {
		System.out.println("자식 meth_1()" + a + "," + b + "," + c);
	}

	void meth_2() {
		System.out.println("자식 meth_2()");
	}

	void meth_3() {
		System.out.println("자식 meth_3()");
	}

}

public class CastingClassMain {

	public static void main(String[] args) {

		CastPar 부모의시선으로부모 = new CastPar();

		System.out.println("부모의시선으로부모.name : " + 부모의시선으로부모.name);
		System.out.println("부모의시선으로부모.a : " + 부모의시선으로부모.a);
		System.out.println("부모의시선으로부모.b : " + 부모의시선으로부모.b);
		부모의시선으로부모.meth_1();
		부모의시선으로부모.meth_2();

		System.out.println();
////////////////////////////////////////////////////////////////////////////////////
		CastChild 자식의시선으로자식 = new CastChild();

		System.out.println("자식의시선으로자식.name : " + 자식의시선으로자식.name);
		System.out.println("자식의시선으로자식.a : " + 자식의시선으로자식.a);
		System.out.println("자식의시선으로자식.b : " + 자식의시선으로자식.b);
		System.out.println("자식의시선으로자식.c : " + 자식의시선으로자식.c);
		자식의시선으로자식.meth_1();
		자식의시선으로자식.meth_2();
		자식의시선으로자식.meth_3();

		System.out.println();
////////////////////////////////////////////////////////////////////////////////////
		CastPar 부모의시선으로자식 = new CastChild();
//		자식의 시선으로 자식을 생성했을때 부모에 있는 맴버 변수를 hiding 시키고,
//		또 다시 부모의 시선으로 자식을 보았을땐, hiding 된 자식의 맴버 변수를 가리고
//		부모의 맴버변수를 보여주나, 메소드는 오버라이딩 된 본인의 메소드를 사용합니다.

		System.out.println("부모의시선으로자식.name : " + 부모의시선으로자식.name);
		System.out.println("부모의시선으로자식.a : " + 부모의시선으로자식.a);
		System.out.println("부모의시선으로자식.b : " + 부모의시선으로자식.b);
//		System.out.println("부모의시선으로자식.c : " + 부모의시선으로자식.c);
		// 부모에는 c가 없으므로 접근 불가
		부모의시선으로자식.meth_1(); // 메소드는 오버라이딩이 됩니다.
		부모의시선으로자식.meth_2();
//		부모의시선으로자식.meth_3(); // 부모에는 meth_3 이 없으므로 접근 불가

		System.out.println(부모의시선으로자식 instanceof CastPar); // true
		System.out.println(부모의시선으로자식 instanceof CastChild); // true

		System.out.println();
////////////////////////////////////////////////////////////////////////////////////
		CastChild 형변환 = (CastChild) 부모의시선으로자식;

		System.out.println("형변환.name : " + 형변환.name);
		System.out.println("형변환.a : " + 형변환.a);
		System.out.println("형변환.b : " + 형변환.b);
		System.out.println("형변환.c : " + 형변환.c);

		형변환.meth_1(); // 메소드는 오버라이딩이 됩니다.
		형변환.meth_2();
		형변환.meth_3();
		
//		형변환(casting)을 해줌으로써, 부모의시선으로자식 에서 접근되지 않았던 자식의 것들이
//		접근 할 수 있도록 합니다.

	}

}
