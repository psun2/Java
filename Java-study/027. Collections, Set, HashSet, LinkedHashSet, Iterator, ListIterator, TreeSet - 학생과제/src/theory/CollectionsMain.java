package theory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CollectionsMain {

	public static void main(String[] args) {

		int[] nums = { 34, 56, 12, 43, 90, 89, 654, 43, 21234, 675, 45 };

		
		ArrayList num = new ArrayList();

		for (int i : nums) {
			num.add(i);
		}

//		pop() = > Stack
//		poll() => Queue

		System.out.println("ArrayList num : " + num);

		System.out.println();
		System.out.println("Collections.suffle - 무작위로 순서를 섞음");
		Collections.shuffle(num);
		System.out.println("Collections.shuffle(num); : " + num);
		// Collections.shuffle(num); : [89, 90, 654, 21234, 34, 43, 45, 56, 12, 43, 675]

		System.out.println();
		System.out.println("Collections.sort() - 정렬");
		Collections.sort(num);
		System.out.println("Collections.sort(num); : " + num);
		// Collections.sort(num); : [12, 34, 43, 43, 45, 56, 89, 90, 654, 675, 21234]

		System.out.println();
		System.out.println("Collections.reverse() - 역순정렬");
		Collections.reverse(num);
		System.out.println("Collections.reverse(num); : " + num);
		// Collections.reverse(num); : [21234, 675, 654, 90, 89, 56, 45, 43, 43, 34, 12]

		System.out.println();
		System.out.println("Collections.swap() - 1 : 1 자리 변경");
		Collections.swap(num, 1, 7);
		System.out.println("Collections.swap(num, 1, 7); : " + num);
	}

}
