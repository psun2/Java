package array_p;

import java.util.Arrays;

public class ArrayCntMain {

	public static void main(String[] args) {

//		22,4,67,12,98,70,53,82,16,8,32 ---> 약분의 갯수가 높은 순으로 정렬하세요
//		22 => 2,11 : 2개
//		12 => 2,3,4,6 : 4개 

		int[] arr = { 22, 4, 67, 12, 98, 70, 53, 82, 16, 8, 32 };

		int[] result = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 2; j < arr[i]; j++) {
				if (arr[i] % j == 0)
					result[i]++;
			}
		}

		System.out.println(Arrays.toString(result));

		int buf = 0;
		for (int i = 0; i < result.length; i++) {
			for (int j = i; j < result.length; j++) {
				if (result[i] < result[j]) {

					buf = result[i];
					result[i] = result[j];
					result[j] = buf;
					
					buf = arr[i];
					arr[i] = arr[j];
					arr[j] = buf;

				}
			}
		}

		System.out.println(Arrays.toString(result));

	}

}
