package theory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ListIterator;

public class BingGoExam {

	public static void main(String[] args) {

		// 숫자는 1 ~ 100 까지
		// 5 * 5 의 빙고판

		HashSet buf = new HashSet(); // 중복 되면 안되기 때문에 HashSet 사용

		while (true) {

			int num = (int) (Math.random() * 100) + 1;

			buf.add(num);

			if (buf.size() == 25)
				break;
		}

		System.out.println(buf);

//		ArrayList bingo = (ArrayList) buf.clone(); // Error
//		List bingo = (List) buf.clone(); // Error
		ArrayList bingo = new ArrayList(buf);

		System.out.println(bingo);

		Collections.shuffle(bingo); // Collectios 를 사용하여 값을 무작으로 섞어줍니다.

		System.out.println(bingo);

		for (int i = 0; i < bingo.size(); i++) {

			System.out.print(bingo.get(i) + "\t");

			if (i % 5 == 4)
				System.out.println("\n");

		}

		System.out.println(" iterator 이용------------------------------------------------------");

		ListIterator lit = bingo.listIterator();

		int line = 0;
		while (lit.hasNext()) {

			System.out.print(lit.next() + "\t");

			line++;

			if (line % 5 == 0)
				System.out.println("\n");

		}

	}

}
