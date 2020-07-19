package theory.pac_1;

//import pac_05_25.pac_1_05_25.sub_05_25.*; // 이때의 DDD는 같은 패키지 내의 DDD
import theory.pac_1.sub.DDD; // 이때의 DDD는 sup의 DDD
//import pac_05_25.pac_2_05_25.*; // 이때의 AAA는 같은 패키지 내의 AAA
import theory.pac_2.AAA;
//import pac_05_25.pac_2_05_25.BBB; 
// 한가지의 java file 에는 한 가지의 class 에서만 public 접근 제어자가 사용 가능하고,
// 패키지가 다르므로 default class, private class 사용 불가
import theory.pac_2.FFF;
import theory.pac_2.GGG;

class CCC {
	int a = 50000;
	private int b = 60000;

	void meth_1() {
		System.out.println("pac.pac_1.CCC meth_1() 실행");
	}

	private void meth_2() {
		System.out.println("pac.pac_1.CCC meth_2() 접근불가");
	}
}

public class PacMain {

	public static void main(String[] args) {

		CCC p1c = new CCC(); // pac => pac1 => PacMain => default class
		System.out.println("p1c.a : " + p1c.a);
		// 같은 java file default 접근 가능
//		System.out.println("p1c.b : " + p1c.b);
		// 같은 java file 이지만 class 가 다르므로 private 접근 불가

		p1c.meth_1(); // 같은 java file default 접근 가능
//		p1c.meth_2(); // 같은 java file 이지만 class 가 다르므로 private 접근 불가

		System.out.println();
/////////////////////////////////////////////////////////////////////////////////////////////////
//		pac_05_25.pac_2_05_25.AAA p1a = new pac_05_25.pac_2_05_25.AAA();
		// 패키지를 import pac_05_25.pac_2_05_25.*; 와 같으 전부 import 받 았다면
		// 이때의 AAA는 나의 패키지 내의 AAA이므로 그냥 AAA를 사용해도 되고 import 한 AAA 를 사용하려면
		// 인스턴스 생성시 패키지 이름을 명시해 줌으로써 사용 가능 합니다.
		// 보통 모든 클래스는 다 쓰지 않는 이상 전체를 import하는 것은 상당히 비효율 적입니다.
//		AAA p1a = new AAA();
		// 이때의 AAA 는 다른 패키지 내의 AAA클래스를 명시적으로 import 해올때는
		// import 해온 AAA 가 되니, 아래와 같이 인스턴스 생성시 나의 패키지를 명시 해줍니다.
		theory.pac_1.AAA p1a = new theory.pac_1.AAA();
		System.out.println("p1a.a : " + p1a.a);
		// 같은 패키지 내의 public 접근 가능
		System.out.println("p1a.b : " + p1a.b);
		// 같은 패키지 내의 default 접근 가능

		p1a.meth_1(); // 같은 패키지 내의 public 접근 가능
		p1a.meth_2(); // 같은 패키지 내의 default 접근 가능

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////
		theory.pac_1.DDD p1d = new theory.pac_1.DDD();
//		DDD p1d = new DDD();
		// 원 생각은 같은 패키지 내의 DDD를 쓰고
		// import pac_05_25.pac_1_05_25.sub_05_25.DDD 여길 import 하면서
		// sub 패키지의 DDD 를 밑에서 따로 쓰고 싶엇지만 import 를 명시적으로 하자마자
		// 같은 패키지 내의 DDD가 사라지고 sub 패키지의 DDD 가 됩니다.
		// 이를 해결하기 위하여 => ✔ 본인 패키지의 패키지명을 명시해줌으로써
		// 이전과 같은 결과가 나올수 있도록 합니다.

		System.out.println("p1d.a : " + p1d.a);
		// 같은 패키지 내의 모든 default 접근 가능
		System.out.println("p1d.b : " + p1d.b);
		// 같은 패키지 내의 모든 default 접근 가능

		p1d.meth_1(); // 같은 패키지 내의 모든 default 접근 가능
		p1d.meth_2(); // 같은 패키지 내의 모든 default 접근 가능

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////
		EEE p1e = new EEE();

		System.out.println("p1e.a : " + p1e.a);
		// 같은 패키지 내의 모든 default 접근 가능
//		System.out.println("p1e.b : " + p1e.b);
		// 같은 클래스 내부가 아닐시 private 접근 불가

		p1e.meth_1(); // 같은 패키지 내의 모든 default 접근 가능
//		p1e.meth_2(); // 같은 클래스 내부가 아닐시 private 접근 불가
		p1e.ppp(); // 같은 패키지 내의 모든 default 접근 가능
		// 같은 클래스 내부에서 private 접근 가능

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////
//		GGG p1g = new GGG(); 
		// pac_05_25.pac_2_05_25.GGG 가 명시 되었으므로 이때의 GGG 는 package2 의 GGG 입니다.
		// 본인 패키지의 이름을 명시해줌으로써 문제를 해혈 가능 합니다.
		theory.pac_1.GGG p1g = new theory.pac_1.GGG();

		System.out.println("p1g.a : " + p1g.a);
		// public 은 java file 당 한개의 클래스만 선언 가능하며,
		// 패키지가 달라도 어디서든 접근 가능
		System.out.println("p1g.b : " + p1g.b);
		// 같은 패키지 내의 모든 default 접근 가능

		p1g.meth_1();
		// public 은 java file 당 한개의 클래스만 선언 가능하며,
		// 패키지가 달라도 어디서든 접근 가능
		p1g.meth_2(); // 같은 패키지 내의 모든 default 접근 가능

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////
		DDD spd = new DDD();

		System.out.println("spd.a : " + spd.a);
		// 패키지가 다르다면 public과 상속을 받은 protected 접근제어자 사용가능
		// default 와 private 사용 불가

		spd.meth_1();
		// 패키지가 다르다면 public과 상속을 받은 protected 접근제어자 사용가능
		// default 와 private 사용 불가

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////
		AAA p2a = new AAA();

		System.out.println("p2a.a : " + p2a.a);
		// 패키지가 다르다면 public 과 상속 받은 protected 접근 제어자만 사용 가능합니다.
//		System.out.println("p2a.b : " + p2a.b);
		// 패키지가 다르다면 default 접근 제어자는 사용 할 수 없습니다.

		p2a.meth_1(); // 패키지가 다르다면 public 과 상속 받은 protected 접근 제어자만 사용 가능합니다.
//		p2a.meth_2(); // 패키지가 다르다면 default 접근 제어자는 사용 할 수 없습니다.

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////
//		BBB = p2b = new BBB();
		// 한 java file의 public 접근제어자는 한개의 class 만 가능 하고,
		// 패키지가 다르므로, import 하여야 하는데 이때 default 는
		// 패키지가 다르다면 import 할 수 없어 사용 불가능 합니다.

		// 만약 protected 를 사용하고자 한다면 class에 protected 를 붙이게되면 상속하여 사용 할 수 있는데,
		// 패키지가 다르다면 상속할때 import가 되어 있어야 하는데 import 당시 public 이 아니면 import자체가 될 수 없고
		// 그러므로 상속또한 할 수 없으므로 패키지가 다르다면 class 는 무조건 public 만 import 할 수 있습니다.

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////
		GGG p2g = new GGG();

		System.out.println("p2g.a : " + p2g.a);
		// 패키지가 다르다면 public 접근제어자가 사용 되었으면 import 하여 접근 가능 합니다.
//		System.out.println("p2g.b : " + p2g.b;
		// 패키지가 다를때 default 접근제어자이면 절대적으로 접근 불가능 합니다.

		p2g.meth_1(); // 패키지가 다르다면 public 접근제어자가 사용 되었으면 import 하여 접근 가능 합니다.
//		p2g.meth_2(); // 패키지가 다를때 default 접근제어자이면 절대적으로 접근 불가능 합니다.

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////
		FFF p2f = new FFF();

		System.out.println("p2f.a : " + p2f.a);
		// 패키지가 다르다면 public 접근제어자가 사용 되었으면 import 하여 접근 가능 합니다.
//		System.out.println("p2f.b : " + p2f.b);
		// 패키지가 다를때 default 접근제어자이면 절대적으로 접근 불가능 합니다.

		p2f.meth_1(); // 패키지가 다르다면 public 접근제어자가 사용 되었으면 import 하여 접근 가능 합니다.
//		p2f.meth_2(); // 패키지가 다를때 default 접근제어자이면 절대적으로 접근 불가능 합니다.

		System.out.println();
///////////////////////////////////////////////////////////////////////////////////////////////

	}

}

//p1c.a : 50000
//pac.pac_1.CCC meth_1() 실행
//
//p1a.a : 10000
//p1a.b : 20000
//pac.pac_1.AAA meth_1() 실행
//pac.pac_1.AAA meth_2() 실행
//
//p1d.a : 70000
//p1d.b : 80000
//pac.pac_1.DDD meth_1() 실행
//pac.pac_1.DDD meth_2() 실행
//
//p1e.a : 90000
//pac.pac_1.EEE meth_1() 실행
//EEE.ppp() 시작 b:10000
//pac.pac_1.EEE meth_2() 실행
//EEE.ppp() 끝
//
//p1g.a : 12345
//p1g.b : 67890
//pac.pac_1.GGG meth_1() 실행
//pac.pac_1.GGG meth_2() 실행
//
//spd.a : 1010
//pac.pac_1.sub.DDD meth_1() 실행
//
//p2a.a : 10
//pac.pac_2.AAA meth_1() 실행
//
//
//p2g.a : 54
//pac.pac_2.GGG meth_1() 실행
//
//p2f.a : 98
//pac.pac_2.FFF meth_1() 실행