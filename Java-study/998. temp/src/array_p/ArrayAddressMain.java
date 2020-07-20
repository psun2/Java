package array_p;

import java.util.Arrays;

public class ArrayAddressMain {

	public static void main(String[] args) {

		int a = 10;
		int b = a;

		int[] arr1 = { 10, 20, 30 };
		int[] arr2 = { 10, 20, 30 };
		int[] arr3 = arr1; // 배열은 참조한다
		// 배열은 new 로 인해 생성되고, 메모리의 heap에 저장됩니다.

		System.out.println("변경 전>>>");
		System.out.println(a + ", " + b);
		System.out.println("arr1: " + Arrays.toString(arr1) + ", " + arr1);
		System.out.println("arr2: " + Arrays.toString(arr2) + ", " + arr2);
		System.out.println("arr3: " + Arrays.toString(arr3) + ", " + arr3);
		a = 1234;
		arr1[1] = 2222;
		System.out.println("변경 후>>>");
		System.out.println(a + ", " + b);
		System.out.println("arr1: " + Arrays.toString(arr1) + ", " + arr1);
		System.out.println("arr2: " + Arrays.toString(arr2) + ", " + arr2);
		System.out.println("arr3: " + Arrays.toString(arr3) + ", " + arr3);

	}

}
