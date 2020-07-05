package sub;

class AAA {

	public AAA() {
		// TODO Auto-generated constructor stub
		System.out.println("sub package 의 AAA class 생성");
	}

}

public class SubPackage {

	AAA a;

	public SubPackage() {
		// TODO Auto-generated constructor stub
		System.out.println("sub package 의 SubPackage class 생성");
		this.a = new AAA();
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
