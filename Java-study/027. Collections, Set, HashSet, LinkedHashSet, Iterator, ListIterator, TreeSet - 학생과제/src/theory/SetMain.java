package theory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class SetMain {

	public static void main(String[] args) {

//		set (HashSet, LinkedHashSet, TreeSet)
//		ArrayList, Stack, Queue
//		set 은 중복을 허용하지 않습니다.
//		순서와 상관없이 add 됩니다.
//		set은 index번호가 존재하지 않습니다. index번호로 할 수 있는 모든 
//		메소드들 사용 불가
//		정렬또한 할 수 없습니다.

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
		// [33, 66, 99, 22, 88, 456, 11, 123, 44, 77]

		ArrayList lotto1 = new ArrayList();
		HashSet lotto2 = new HashSet();

		while (true) {

			int num = (int) (Math.random() * 45) + 1;

			lotto1.add(num);
			lotto2.add(num);

			if (lotto2.size() == 7)
				break;

		}

		System.out.println("lotto1 : " + lotto1);
		// ArrayList 는 중복을 허용하기 때문에 중복되지 않아야 된다면 심각한 에러를 유발합니다.
		System.out.println("lotto2 : " + lotto2);
		// 반면 set은 중복을 허용하지 않습니다.

		ArrayList lotto3 = new ArrayList(lotto2); // set을 정렬하려면 ArrayList로 옴겨 정렬 할 수 있음.

		Collections.sort(lotto3);

		System.out.println("lotto3 : " + lotto3);
	}

}
