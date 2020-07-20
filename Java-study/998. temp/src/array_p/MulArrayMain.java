package array_p;

import java.util.Arrays;

public class MulArrayMain {

	public static void main(String[] args) {

		int[][] arr = { // 1차원
				{ 100, 101, 102, 103 }, // arr[0]
				{ 200, 201, 202, 203 }, // arr[1]
				{ 300, 301, 302, 303 }, // arr[2]
				// 2차원 arr[?][0],arr[?][1],arr[?][2],arr[?][3]

		};

		System.out.println(Arrays.deepToString(arr));
		// [[100, 101, 102, 103], [200, 201, 202, 203], [300, 301, 302, 303]]

		System.out.println("arr: " + arr);
		System.out.println("arr[0]: " + arr[0]);
		System.out.println("arr[1]: " + arr[1]);
		System.out.println("arr[2]: " + arr[2]);
//		System.out.println("arr[3]: " +arr[3] );

		System.out.println("arr[0][0]: " + arr[0][0]);
		System.out.println("arr[0][1]: " + arr[0][1]);
		System.out.println("arr[0][2]: " + arr[0][2]);
		System.out.println("arr[0][3]: " + arr[0][3]);
//		System.out.println("arr[0][4]: " + arr[0][4]);

		System.out.println("arr[1][0]: " + arr[1][0]);
		System.out.println("arr[1][1]: " + arr[1][1]);
		System.out.println("arr[1][2]: " + arr[1][2]);
		System.out.println("arr[1][3]: " + arr[1][3]);

		System.out.println("arr.length: " + arr.length);
		System.out.println("arr[0].length: " + arr[0].length);
		System.out.println("arr[1].length: " + arr[1].length);
		System.out.println("arr[2].length: " + arr[2].length);
		System.out.println();

		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i + "]>>>");
			for (int j = 0; j < arr[i].length; j++) {
				System.out.println("arr[" + i + "][" + j + "]" + arr[i][j]);
			}
		}

	}

}
