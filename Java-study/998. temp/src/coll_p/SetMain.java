package coll_p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SetMain {

	public static void main(String[] args) {

		Object[] arr = { 11, 44, 33, 77, 88, 22, 44, 11, 99, 123, 456, 123, 66, 33 };

		ArrayList list1 = new ArrayList();
		HashSet set1 = new HashSet();

		for (Object object : arr) {
			list1.add(object);
			set1.add(object);
		}

		System.out.println(list1);
		// [11, 44, 33, 77, 88, 22, 44, 11, 99, 123, 456, 123, 66, 33]
		System.out.println(set1);
		// [33, 66, 99, 22, 88, 456, 11, 123, 44, 77] => 중복이 다 제거됨, 순서와 관계없이 뒤죽 박죽 들어옴
		// ArrayList 의 shffle 과 비슷함.

		ArrayList lotto1 = new ArrayList();
		HashSet lotto2 = new HashSet();

		while (true) {

			int num = (int) (Math.random() * 45) + 1;

			lotto1.add(num);
			lotto2.add(num);

			if (lotto2.size() == 7)
				break;

		}

		System.out.println("lotto1 : " + lotto1); // 랜덤한 수가 중복이 될수 있으므로, 로또번호는 ArrayList 사용 불가
		System.out.println("lotto2 : " + lotto2);

//		Collections.sort(lotto2);

		ArrayList lotto3 = new ArrayList(lotto2); // set을 정렬하려면 ArrayList로 옴겨 정렬 할 수 있음.

		Collections.sort(lotto3);

		System.out.println("lotto3 : " + lotto3);

	}

}
