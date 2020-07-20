package lang_p;

class AAA {
	int a;
	String b;

	public AAA(int a, String b) {
		super();
		this.a = a;
		this.b = b;
	}

	// 생성자, get, set, toString => toString 은 보통 출력 메소드로 오버라딩 하여 사용 합니다.
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return a + ", " + b;
	}

//	@Override
//	public boolean equals(Object obj) {
//		AAA you = (AAA) obj;
////		return super.equals(obj);
//		return a == you.a && b.equals(you.b);
//	}

	@Override
	public int hashCode() { // 오버라이딩 전 hashCode() => 16진수의 주소를 10진수로 변환 // 보안상으로 위험
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// 기존 주소개념 비교에서 맴버 변수 비교
		if (this == obj) // this => class, obj => parameter
			return true;
		if (obj == null) // obj => parameter
			return false;
		if (getClass() != obj.getClass()) // getClass() => 클래스 이름, obj => parameter
			return false;
		AAA other = (AAA) obj; // obj 는 AAA로 형변환 됩니다.
		if (a != other.a) // 맴버 변수 비교
			return false;
		if (b == null) { // 맴버 변수 비교
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b)) // 맴버 변수 비교
			return false;
		return true;
	}

}

public class ObjectMain {

	public static void main(String[] args) {

		AAA a1 = new AAA(10, "아기상어");
		AAA a2 = new AAA(20, "엄마상어");
		AAA a3 = new AAA(10, "아기상어");
		AAA a4 = a1;

		System.out.println("a1 : " + a1); // a1 : lang_p.AAA@15db9742
		System.out.println("a2 : " + a2); // a2 : lang_p.AAA@6d06d69c
		System.out.println("a3 : " + a3); // a3 : lang_p.AAA@7852e922
		System.out.println("a4 : " + a4); // a4 : lang_p.AAA@15db9742

		// a1 과 a4 는 shallow copy 로서 주소값이 같다.

		System.out.println();
		System.out.println("Class -------------------------------------");

		System.out.println("a1.getClass() : " + a1.getClass());
		System.out.println("a2.getClass() : " + a2.getClass());
		System.out.println("a3.getClass() : " + a3.getClass());
		System.out.println("a4.getClass() : " + a4.getClass());

//		a1.getClass() : class lang_p.AAA
//		a2.getClass() : class lang_p.AAA
//		a3.getClass() : class lang_p.AAA
//		a4.getClass() : class lang_p.AAA

		System.out.println("a1.getClass().getName() : " + a1.getClass().getName());
		System.out.println("a2.getClass().getName() : " + a2.getClass().getName());
		System.out.println("a3.getClass().getName() : " + a3.getClass().getName());
		System.out.println("a4.getClass().getName() : " + a4.getClass().getName());

//		a1.getClass().getName() : lang_p.AAA
//		a2.getClass().getName() : lang_p.AAA
//		a3.getClass().getName() : lang_p.AAA
//		a4.getClass().getName() : lang_p.AAA

		System.out.println();
		System.out.println(".hashCode() -------------------------------------");

//		.hashCode() = 주소를 보여줍니다. (16 진수 의 주소 값을 10진수로 바꾸어 보여줍니다.)
//		메모리 주소로서 완벽하게 노출 하면 보안상 위험이 있습니다.

		System.out.println("a1.hashCode() : " + a1.hashCode());
		System.out.println("a2.hashCode() : " + a2.hashCode());
		System.out.println("a3.hashCode() : " + a3.hashCode());
		System.out.println("a4.hashCode() : " + a4.hashCode());

//		a1.hashCode() : 366712642
//		a2.hashCode() : 1829164700
//		a3.hashCode() : 2018699554
//		a4.hashCode() : 366712642

		System.out.println("16진수(주소) : " + a1 + ", 10진수(주소) : " + a1.hashCode());
//		16진수 : lang_p.AAA@15db9742, 10진수 : 366712642
//		.hashCode() = 주소를 보여줍니다. (16 진수 의 주소 값을 10진수로 바꾸어 보여줍니다.)
//		메모리 주소로서 완벽하게 노출 하면 보안상 위험이 있습니다.

		System.out.println();
		System.out.println(".toString()=> 오버라이딩 -------------------------------------");

		System.out.println("a1.toString() : " + a1.toString());
		System.out.println("a2.toString() : " + a2.toString());
		System.out.println("a3.toString() : " + a3.toString());
		System.out.println("a4.toString() : " + a4.toString());

//		a1.toString() : lang_p.AAA@15db9742
//		a2.toString() : lang_p.AAA@6d06d69c
//		a3.toString() : lang_p.AAA@7852e922
//		a4.toString() : lang_p.AAA@15db9742

		System.out.println();
		System.out.println("==");

		System.out.println("a1 == a2 : " + (a1 == a2)); // a1 == a2 : false
		System.out.println("a1 == a3 : " + (a1 == a3)); // a1 == a3 : false
		System.out.println("a1 == a4 : " + (a1 == a4)); // a1 == a4 : true

//		주소 로 비교 합니다.(생성자로 초기화 하는 값이 아닌 주소비교)

		System.out.println();
		System.out.println(".equals()=> 오버라이딩 -------------------------------------");

//		기본 equals 는 주소 값을 비교하지만, 오버라이딩 한 quals 는 맴버 변수가 같은지를 비교 합니다.

		System.out.println("a1.equals(a2) : " + a1.equals(a2));
		// 오버라이딩 전 : a1.equals(a2) : false, 오버라이딩 후 : a1.equals(a2) : false
		System.out.println("a1.equals(a3) : " + a1.equals(a3)); //
		// 오버라이딩 전 : a1.equals(a3) : false, 오버라이딩 후 : a1.equals(a3) : true
		System.out.println("a1.equals(a4) : " + a1.equals(a4)); //
		// 오버라이딩 전 : a1.equals(a4) : true, 오버라이딩 후 : a1.equals(a4) : true

	}

}
