package array_p;

import java.util.Arrays;

public class ArrayExamCntMain {

	public static void main(String[] args) {

//		22,4,67,12,98,70,53,82,16,8,32 ---> 약분의 갯수가 높은 순으로 정렬하세요
//		22 => 2,11 : 2개
//		12 => 2,3,4,6 : 4개 

		int[] arr = { 22, 4, 67, 12, 98, 70, 53, 82, 16, 8, 32 };

		int[] result = new int[arr.length];
		int[] rank = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 2; j < arr[i]; j++) {
				if (arr[i] % j == 0)
					result[i]++;
			}
		}

		System.out.print("result: ");
		System.out.println(Arrays.toString(result));

//		int buf = 0;
//		for (int i = 0; i < result.length; i++) {
//			for (int j = i; j < result.length; j++) {
//				if (result[i] < result[j]) {
//
//					buf = result[i];
//					result[i] = result[j];
//					result[j] = buf;
//
//					buf = arr[i];
//					arr[i] = arr[j];
//					arr[j] = buf;
//
//				}
//			}
//		}
//
//		System.out.print("result: ");
//		System.out.println(Arrays.toString(result));

		for (int i = 0; i < rank.length; i++) {
			rank[i] = 1;
			for (int j = i + 1; j < rank.length; j++) {

				if (result[i] < result[j])
					rank[i]++;

			}
		}

		System.out.print("rank: ");
		System.out.println(Arrays.toString(rank));

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {

				if (rank[i] == i) {
					System.out.println(result[i] + ", " + rank[i]);

				}

			}
		}

	}

}
