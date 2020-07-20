package array_p;

import java.util.Arrays;

public class MulArrayAddressMain {

	public static void main(String[] args) {

		int[] arr1 = { 11, 22, 33, 44 };

		int[] arr2, arr3;

		int[][] tot2 = { { 123, 456 }, { 789, 120, 304, 506 } };

		int[][] tot1 = { //
				{ 1000, 2000, 3000 }, //
				new int[5], // {0, 0, 0, 0, 0}
				arr1, // {11,22,33,44};
//				tot2 // error 2차원?�� 배열?�� ?��?�� ???��  error ?�� 발생?��?��?��.
				tot2[0], // 1차원 배열?? ?��?�� ?�� ?�� ?��?��?��?��.
//				arr2 // error 주소�? ?��?��?��?���? ?��문에 ?��?�� ?�� ?�� ?��?��?��?��.
				arr3 = new int[] { 45, 67, 89 } }; // ?��?��?��?�� 값이 ?���?�?
		// ?��?��?�� 배열?�� ?��?��?��?��?�� error?�� ?�� ?�� ?�� ?��?��?��?��.

		System.out.println("�?경전 >>>");
		System.out.println("arr1: " + Arrays.toString(arr1) + arr1);
//		System.out.println("arr2: " + Arrays.toString(arr2)); // undefined
		System.out.println("arr3: " + Arrays.toString(arr3) + arr3);

		System.out.println("tot2 > " + tot2);
		for (int i = 0; i < tot2.length; i++) {
			System.out.println("	tot2[" + i + "]" + Arrays.toString(tot2[i]));
		}
		System.out.println("tot2: " + Arrays.deepToString(tot2));

		System.out.println("tot1 > " + tot1);
		for (int i = 0; i < tot1.length; i++) {
			System.out.println("	tot1[" + i + "]" + Arrays.toString(tot1[i]));
		}
		System.out.println(Arrays.deepToString(tot1));

		arr1[0] = 222;
		arr3[2] = 4989;
		tot2[0][1] = 264;

		tot1[2][3] = 123456;
		tot1[3][0] = 9999;
		tot1[4][1] = 62026;

		System.out.println();

		System.out.println("�?경후 >>>");
		System.out.println("arr1: " + Arrays.toString(arr1) + arr1);
//		System.out.println("arr2: " + Arrays.toString(arr2)); // undefined
		System.out.println("arr3: " + Arrays.toString(arr3) + arr3);

		System.out.println("tot2 > " + tot2);
		for (int i = 0; i < tot2.length; i++) {
			System.out.println("	tot2[" + i + "]" + Arrays.toString(tot2[i]));
		}
		System.out.println("tot2: " + Arrays.deepToString(tot2));

		System.out.println("tot1 > " + tot1);
		for (int i = 0; i < tot1.length; i++) {
			System.out.println("	tot1[" + i + "]" + Arrays.toString(tot1[i]));
		}
		System.out.println(Arrays.deepToString(tot1));

	}

}
