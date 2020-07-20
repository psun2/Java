package dynamic_P;

import java.util.Arrays;

class Capsule {

	private int abc;

	private boolean ref;

	public Capsule(int abc, boolean ref) {
		super();
		this.abc = abc;
		this.ref = ref;
	}

	public int getAbc() { // getter
		return abc;
	}

	public void setAbc(int abc) { // setter
		this.abc = abc;
	}

	public boolean isRef() { // getter
		return ref;
	}

	public void setRef(boolean ref) { // setter
		this.ref = ref;
	}

	private int[] arr = { 11, 22, 33, 44 };

	public int[] getArr() {
		System.out.println("getArr");
		return arr;
	}

	void printArr() {
		System.out.println("print arr");
		System.out.println(arr);
		System.out.println(Arrays.toString(arr));
	}

	public int getArr2(int i) {
		return arr[i];
	}

	public int[] getArr3() {
		int[] arr = this.arr.clone();
		return arr;
	}

}

public class CapsuleMain {

	public static void main(String[] args) {

		Capsule cc = new Capsule(100, true);

		System.out.println(cc.getAbc() + ", " + cc.isRef());

		cc.setAbc(1234);
		cc.setRef(false);

		System.out.println(cc.getAbc() + ", " + cc.isRef());

		// FIXME 캡슐화의 문제점
//		System.out.println(Arrays.toString(cc.arr));
		// private 로 접근 불가
		System.out.println(Arrays.toString(cc.getArr()));
		// [11, 22, 33, 44]

//		cc.arr[1] = 2345;
		// private 로 접근 불가

		cc.getArr()[1] = 2345; // private 로 걸어놓은 배열의 값이 바뀌 었습니다.
		System.out.println(Arrays.toString(cc.getArr()));

		cc.printArr();

		System.out.println(cc.getArr2(0));
		System.out.println(cc.getArr2(1));
		// 주소를 넘기지말고, index를 이용하여 내가 원하는 값만 넘길 수 있도록 getter을 셋팅 합니다.\
		// 주소를 통채로 넘기면 private 이 되어도 값변경이 너무나 쉽게 가능합니다.

		cc.getArr3()[1] = 124;
		System.out.println(Arrays.toString(cc.getArr3()));
		// 변수에 저장 또는 원본 배열을 clone 해서 return 시켜 줍니다.
		// 원본 배열이 변경 되지 않습니다.

//		int[] arr = cc.getArr().clone();

	}

}
