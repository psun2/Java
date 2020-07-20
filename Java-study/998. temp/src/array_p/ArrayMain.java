//byte 1
//short 2
//int 4
//long 8
//
//
//char 2
//
//float 4
//double 8
//
//200
//500
//200

package array_p;

public class ArrayMain {

	public static void main(String[] args) {

		int a = 10;

//		int[] arr1;
//		int[] arr1 = 20; // error
		int[] arr1 = new int[3];
		// 배열 변수명 = 배열 공간 할당 {0, 0, 0}
		// TODO 초기값이 없을땐 0 으로 할당 됩니다.

		System.out.println("a : " + a);
//		System.out.println("arr1 : " + arr1); // 값이 없는 변수는 사용할 수 없습니다.
		System.out.println("arr1 : " + arr1); // 배열 호출 : 배열주소 호출 (arr1 : [I@15db9742)
		System.out.println("arr1[0] : " + arr1[0]);
		System.out.println("arr1[1] : " + arr1[1]);
		System.out.println("arr1[2] : " + arr1[2]);
//		System.out.println("arr1[3] : " + arr1[3]); // 없는 index 는 error

		System.out.println();

		int[] arr2 = { 111, 222, 333, 444 };
		System.out.println("arr2[0] : " + arr2[0]);
		System.out.println("arr2[1] : " + arr2[1]);
		System.out.println("arr2[2] : " + arr2[2]);
		System.out.println("arr2[3] : " + arr2[3]);

		System.out.println();

		int[] arr3 = { 123, 456, 789 };
		System.out.println("arr3[0] : " + arr3[0]);
		System.out.println("arr3[1] : " + arr3[1]);
		System.out.println("arr3[2] : " + arr3[2]);

		arr3[0] = 135; // 배열 원소 대입
		arr3[1] = 246; // 배열 원소 대입
		arr3[2] = 369; // 배열 원소 대입
//		arr3[3] = 47; // 존재하지 않는 index 접근 불가

		System.out.println("arr3[0] : " + arr3[0]);
		System.out.println("arr3[1] : " + arr3[1]);
		System.out.println("arr3[2] : " + arr3[2]);

		int b;
		b = 100;
		int[] arr4;
//FIXME arr4 = {10 ,20, 30, 40}; // 배열 변수 초기화는 선언시에만 가능
		arr4 = new int[5]; // 배열변수 대입은 배열의 주소를 받아야함.
		arr4 = new int[] { 10, 20, 30, 40 }; // 배열 생성 및 초기화 후 주소 넘김
	}

}
