package array_p;

public class SelectSort {

	public static void main(String[] args) {
		int[] arr = { 23, 9, 72, 43, 51 };

		int buf;
		for (int me = 0; me < arr.length; me++) {
			for (int you = me + 1; you < arr.length; you++) {
				if (arr[me] > arr[you]) {
					buf = arr[me];
					arr[me] = arr[you];
					arr[you] = buf;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}

	}

}
