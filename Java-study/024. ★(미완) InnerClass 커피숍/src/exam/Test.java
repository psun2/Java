package exam;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {

		int[] arr = { 1000, 10000000, 100, 1, 1000000000, };

		String temp = "";

		String[] arr2 = new String[arr.length];

		for (int i = 0; i < arr.length; i++) {
			arr2[i] = temp;
			arr2[i] += arr[i];

		}

		System.out.println(Arrays.toString(arr2));

//		for (int i = 0; i < arr2.length; i++) {
//
//			char[] ch = arr2[i].toCharArray();
//
//			System.out.println(Arrays.toString(ch));
//			System.out.println(ch.length);
//			System.out.println(ch[ch.length - 1]);
//
//			for (int j = ch.length - 1; j > 0; j--) {
//				System.out.println("시작");
//				System.out.println(ch[j]);
//				System.out.println("끝");
//				arr2[i] += ch[j];
//			}
//
//		}
//
//		System.out.println(Arrays.toString(arr2));

		for (int i = 0; i < arr2.length; i++) {

			for (int j = arr2[i].length() - 1; j >= 0; j--) {
				System.out.println(arr2[i].length() - 1);
				if (j % 3 == 0) {
					arr2[i] += arr2[i].charAt(j) + ",";
				}
				arr2[i] += arr2[i].charAt(j);
			}

		}
		System.out.println(Arrays.toString(arr2));

	}

}
