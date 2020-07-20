package array_p;

public class ArrayForMain {

	public static void main(String[] args) {

		int[] arr = { 98, 92, 91, 85, 75, 86 };

		int tot = 0;

		tot += arr[0];
		tot += arr[1];
		tot += arr[2];
		tot += arr[3];
		tot += arr[4];
		tot += arr[5];

		System.out.println(tot);
		System.out.println(tot / 6);
		System.out.println("length " + arr.length);

		System.out.println("---------------");

		tot = 0;
		for (int i = 0; i < arr.length; i++) {
			tot += arr[i];
			System.out.println("arr[" + i + "]" + arr[i]);
		}

		System.out.println(tot);
		System.out.println(tot / arr.length);

		System.out.println("---------------");

		int[] array = { 87, 65, 82, 46, 98, 65, 72, 34, 91, 79, 45, 67 };
		int sum = 0;
		int count = 0;
		double avg = (double) sum / count;

		for (int i = 0; i < array.length; i++) {
			if (array[i] >= 70) {
				sum += array[i];
				count++;
			}
		}

//		double avg = sum / array.length;
		System.out.println("총점" + sum);
		System.out.println("평균" + avg);

		System.out.println("---------------");

		int[] array2 = { 22, 3, 13, 4, 67, 7, 12, 98, 23, 70, 5, 53, 43, 82, 19, 37, 16, 8, 32 };
		int chk = 0;
//		boolean bo = false;
		String res = "";
		for (int i = 0; i < array2.length; i++) {
			for (int j = 2; j < array2[i]; j++) {
				if (array2[i] % j == 0) {
//					bo = false;
//					chk++;
//				System.out.println(array2[i]);
//					System.out.print(array2[i] + ", ");
//					int a = (array2[i] % j != 0);
					break;
				} else {
					System.out.print(array2[i] + " ");					
					res += array2[i];
				}
			}
		}

		System.out.println();
		System.out.println(chk);
//		System.out.println((bo == true));
		System.out.println(res);

//		System.out.println("---------------");
//
//		int[] array3 = { 78, 12, 45, 67, 78, 12, 67, 5, 78, 12, 56, 78, 78, 9 };

	}

}
