package coll_p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class IteratorMain {

	public static void main(String[] args) {

		ArrayList arr = new ArrayList();

		arr.add("홍박이");
		arr.add("성시주");
		arr.add("혁현혁");
		arr.add("김김오");
		arr.add("연현민");
		arr.add("섭준석");

		Iterator it = arr.iterator();

		System.out.println(it.toString());

		System.out.println(arr);

		while (it.hasNext()) {
			Object obj = it.next();
			if (obj.equals("성시주")) {
//				arr.remove(obj);
				it.remove();
			}

			System.out.println(obj);
		}

		System.out.println(arr);

		System.out.println("----------------------------------------------------------");

//		System.out.println(it.next()); // 홍박이

		Iterator it2 = arr.iterator();

		while (it2.hasNext()) {
			System.out.println(it2.next());
		}

		System.out.println("----------------------------------------------------------");

		for (Object object : arr) {
			System.out.println(object);
		}

//		for (Object object : arr) {
//			if (object.equals("성시주")) {
//				arr.remove(object); // for문 내부에서 remove() 실행시 반복되는 index의 길이가 달라지게 되므로 에러 Error
//			}
//			System.out.println(object);
//		}
		System.out.println("ListIterator ----------------------------------------------------------");

		ListIterator lit = arr.listIterator();

		while (lit.hasNext()) { // 순서출력
			System.out.println(lit.next());
		}

		System.out.println("hasPrevious----------------------------------------------------------");

		while (lit.hasPrevious()) { // 역순 출력
			System.out.println(lit.previous());
		}

		System.out.println("----------------------------------------------------------");

		while (lit.hasPrevious()) { // 역순 출력
			System.out.println(lit.previous());
		}

		while (lit.hasNext()) { // 순서출력
			System.out.println(lit.next());
		}

		// 순서가 역순이면 후진을 하지 않습니다.
		// 전진을 해야 후진을 할것이 생기는데 ....

		int[] nums = { 34, 56, 12, 43, 90, 89, 654, 43, 21234, 675, 45 };
		// 1. 모든 원소를 ArrayList에 넣어 출력하세요.
		// 2. 3의 배수를 제외한 ArrayList로 변환하세요.

		ArrayList num = new ArrayList();

		for (int i : nums) {
			num.add(i);
		}

//		num.add("문자열을 숫자와 비교할수 없어 sort 시 Error");

		System.out.println(num);
		// [34, 56, 12, 43, 90, 89, 654, 43, 21234, 675, 45]

		System.out.println("shuffle");
		Collections.shuffle(num); // 실행시마다 순서를 뒤집음
		System.out.println(num);

		System.out.println("sort");
//		num.sort(null); // 정렬기능
		Collections.sort(num); // 정렬기능
		System.out.println(num);

		System.out.println("reverse");
		Collections.reverse(num); // 역순 정렬기능
		System.out.println(num);
		// [12, 34, 43, 43, 45, 56, 89, 90, 654, 675, 21234]

		System.out.println("swap");
		System.out.println(num);
		Collections.swap(num, 2, 6); // 해당 index의 자리만 서로 swap 자리변경 합니다.
		System.out.println(num);

		ListIterator numIt = num.listIterator();

		while (numIt.hasNext()) {

			int i = (int) numIt.next();

			if (i % 3 == 0 || i % 2 == 0) {
//				num.remove(temp);
				numIt.remove();
			}

		}

		System.out.println(num);

	}

}
