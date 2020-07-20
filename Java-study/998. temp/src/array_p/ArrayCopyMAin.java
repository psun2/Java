package array_p;

import java.util.Arrays;

public class ArrayCopyMAin {

	public static void main(String[] args) {

		int[] arr1 = { 10, 20, 30 };

		int[] arr2 = arr1; // shallow copy

		int[] arr3 = new int[arr1.length];

		for (int i = 0; i < arr3.length; i++) { // deep copy
			arr3[i] = arr1[i]; // 같은 값을 참조 하는 것이 아니라 새로운 배열을 생성합니다.
		}

		int[] arr4 = arr1.clone(); // clone 함수는 대상 배열의 참조가 아닌 복제를 해 새로운 주소를 생성합니다.

		System.out.println("arr1: " + Arrays.toString(arr1) + ", 주소: " + arr1);
		// arr1: [10, 20, 30], 주소: [I@15db9742
		System.out.println("arr2: " + Arrays.toString(arr2) + ", 주소: " + arr2);
		// arr2: [10, 20, 30], 주소: [I@15db9742
		System.out.println("arr3: " + Arrays.toString(arr3) + ", 주소: " + arr3);
		// arr3: [10, 20, 30], 주소: [I@6d06d69c
		System.out.println("arr4: " + Arrays.toString(arr4) + ", 주소: " + arr3);
		// arr4: [10, 20, 30], 주소: [I@6d06d69c

		arr1[1] = 2222;
		System.out.println("arr1: " + Arrays.toString(arr1) + ", 주소: " + arr1);
		// arr1: [10, 2222, 30], 주소: [I@15db9742
		System.out.println("arr2: " + Arrays.toString(arr2) + ", 주소: " + arr2);
		// arr2: [10, 2222, 30], 주소: [I@15db9742
		System.out.println("arr3: " + Arrays.toString(arr3) + ", 주소: " + arr3);
		// arr3: [10, 20, 30], 주소: [I@6d06d69c
		System.out.println("arr4: " + Arrays.toString(arr4) + ", 주소: " + arr3);
		// arr4: [10, 20, 30], 주소: [I@6d06d69c

//		arr1 을 참조하는  arr2는  arr1 의 값이 바뀌면 arr2의 값도 바뀝니다.

		System.out.println();

		int[] no = { 11, 22 };
		System.out.println(no.length); // 2
		System.out.println("no" + Arrays.toString(no) + no);
//		no[2] = 1234;

		int[] temp = new int[no.length + 1];

		for (int i = 0; i < no.length; i++) {
			temp[i] = no[i];
		}

//		temp[0] = no[0];
//		temp[1] = no[1];
//		temp[2] = 1234;
		temp[no.length] = 1234;

		System.out.println("no" + Arrays.toString(temp) + temp); // 0
		System.out.println("no" + Arrays.toString(no) + no); // 0

		no = temp;
		System.out.println("no" + Arrays.toString(no) + no); // 0

		System.out.println();

		int[] data = { 22, 4, 67, 12, 98, 70, 53, 81, 16, 8, 35 };

		// 짝수 배열
		int count = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] % 2 == 0) { // 짝수만 만들 배열의 길이를 구합니다.
				count++;
			}
		}

		System.out.println(count);
		System.out.println(Arrays.toString(data));

		int[] even = new int[count];

		for (int i = 0; i < data.length; i++) {
			if (data[i] % 2 == 0) { // 짝수이면서
				for (int j = 0; j < even.length; j++) {
					if (even[j] == 0) { // 현재의 짝수배열에 값이 초기값 0 이면
						even[j] = data[i]; // 값을 바꿔줍니다.
						break;
					}
				}
			}

		}

		System.out.println(Arrays.toString(even));

		// 홀수 배열
		count = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] % 2 != 0) {
				count++;
			}
		}

		System.out.println(count);

		int[] odd = new int[count];

		for (int i = 0; i < data.length; i++) {
			if (data[i] % 2 != 0) {
				for (int j = 0; j < odd.length; j++) {
					if (odd[j] == 0) {
						odd[j] = data[i];
						break;
					}
				}
			}

		}

		System.out.println(Arrays.toString(odd));

//===============================================================================
		System.out.println("다른방법");

		int[] newEven = new int[count];
		int target = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] % 2 != 0) {
				newEven[target++] = data[i];
			}
		}

		System.out.println(Arrays.toString(newEven));
//===============================================================================

//		forEach
		// 처음부터 차례차례 1씩 늘어날때만 사용 합니다.
		// index 를 알 수 없습니다.
		
		for(int i: data) {
			System.out.print(i + "\t");
		}
		
//		for(int i: data) {
//			if(i % 2 == 0) {
//				for(int i = 0; i < even.length; i++) {
//					buf[j] = even[j];
//				}
//				buf[even.length] = i;
//				even = buf;
//			}
//		}
		
//===============================================================================

		// 한번만

		int[] ones = { 78, 12, 45, 67, 78, 12, 67, 5, 78, 12, 56, 78, 78, 9 };
		// 78 X 12 X 45X 5 X
		// 67 O
		int len = 0; // 길이를 구할 변수를 선언합니다.
		int[] temp2 = new int[ones.length]; // 전 비교했던 요소가 올 수 있을 경우가 있으니 전요소가 맞는 지 확인하는 배열을 생성합니다.
		for (int i = 0; i < ones.length; i++) {
			count = 0;
			for (int j = 0; j < ones.length; j++) {
				if (ones[i] == ones[j])
					count++;
			}
			if (count == 2) { // count 는 비교 배열에서 본인포함 2개 일때 한번만 반복되는 수 입니다.
				temp2[i] = ones[i];
				for (int j = 0; i < temp2.length; j++) { // 겹치는 두번째 요소가 있으니, temp2라는 배열을 선언하고 겹치는 그 값을 저장할 수 있도록 합니다.
					if(temp2[j] == ones[i]) {
						break;
						
					} 
						
						len++;
					
				}
			}
			count = 0;
		}

		System.out.println(ones.length);
		System.out.println(len);

		int[] 한번만 = new int[len];
		target = 0;
		for (int i = 0; i < ones.length; i++) {
			count = 0;
			for (int j = 0; j < ones.length; j++) {
				if (ones[i] == ones[j])
					count++;
			}
			if (count == 2) {
				for (int j = 0; j < 한번만.length; j++) {
					if (한번만[j] != ones[i])
						한번만[target++] = ones[i];
					break;
				}
			}
			count = 0;
		}

		System.out.println(Arrays.toString(한번만));

	}

}
