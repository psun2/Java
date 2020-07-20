package note;

import java.util.Arrays;

public class Note1 {

	public static void main(String[] args) {

//		22,4,67,12,98,70,53,82,16,8,32 ---> 약분의 갯수가 높은 순으로 정렬하세요
//		22 => 2,11 : 2개
//		12 => 2,3,4,6 : 4개 

		int[] arr = { 22, 4, 67, 12, 98, 70, 53, 82, 16, 8, 32 };

		
		// 1. 약분된 갯수중에서도 큰 수를 찾기 위해 arr 배열을 큰 수로 정렬합니다.
		for (int i = 0; i < arr.length; i++) {

			int a = 0;

			for (int j = 0; j < arr.length; j++) {

				if (arr[i] < arr[j]) {
					a = arr[i];
					arr[i] = arr[j];
					arr[j] = a;
				}

			}
		}

		System.out.println(Arrays.toString(arr));

		// 2. 약분의 갯수를 새로운 abb 배열에 담습니다.
		int[] abb = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int count = 0;
			for (int j = 2; j < arr[i]; j++) {

				if (arr[i] % j == 0)
					count++;

			}
			abb[i] = count;
		}

		System.out.println(Arrays.toString(abb));

		// 3. 약분의 크기를 비교하기 위해 abb의 배열을 복사한 abbSort 배열을 생성합니다.
		int[] abbSort = new int[arr.length];
		for (int i = 0; i < abb.length; i++) {
			abbSort[i] = abb[i];
		}

		// 4. 복사한 abbSort배열을 정렬합니다.
		for (int i = 0; i < abbSort.length; i++) {

			int count = 0;

			for (int j = i + 1; j < abbSort.length; j++) {

				if (abbSort[i] > abbSort[j]) {
					count = abbSort[i];
					abbSort[i] = abbSort[j];
					abbSort[j] = count;
				}
			}
		}

		System.out.println(Arrays.toString(abbSort));

		// 5. 정렬되기전 약분의 갯수를 담은 abb 배열과 정렬후의 abbSort 배열을 비교하여,
		// 해당값의 index를 얻어 옵니다.
		int[] abbSortIndex = new int[arr.length];
		for (int i = 0; i < abb.length; i++) {

			int init = -1;

			for (int j = 0; j < abb.length; j++) {
				if (abbSort[i] == abb[j]) {
					abbSortIndex[i] = j;
					abb[j] = init;
					break;
				}
			}

		}

		System.out.println(Arrays.toString(abbSortIndex));

		// 6. 역순 정렬이기에 반대로 출력합니다.
		for (int i = abbSortIndex.length - 1; i >= 0; i--) {
			System.out.print(arr[ abbSortIndex[i]] + "\t");
		}

	}

}