package theory;

import java.util.ArrayList;
import java.util.ListIterator;

public class IteratorExam {

	public static void main(String[] args) {

		int[] nums = { 34, 56, 12, 43, 90, 89, 654, 43, 21234, 675, 45 };
		// 1. 모든 원소를 ArrayList에 넣어 출력하세요.
		// 2. 3의 배수를 제외한 ArrayList로 변환하세요.

		ArrayList num = new ArrayList();

		for (int i : nums) {
			num.add(i);
		}

		System.out.println(num);
		// [34, 56, 12, 43, 90, 89, 654, 43, 21234, 675, 45]

		ListIterator lit = num.listIterator();

		while (lit.hasNext()) {

			int i = (int) lit.next();

			if (i % 2 == 0 || i % 3 == 0)
				lit.remove();

		}

		System.out.println(num);
		// [43, 89, 43]
	}

}
