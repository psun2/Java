package coll_p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ListIterator;

public class BingGoExam {

	public static void main(String[] args) {

		HashSet binggo = new HashSet();

		while (true) {

			int num = (int) (Math.random() * 100) + 1;

			binggo.add(num);

			if (binggo.size() == 25)
				break;

		}

		ArrayList binggo2 = new ArrayList(binggo);

		Collections.shuffle(binggo2);

//		System.out.println(binggo2);

		ListIterator lit = binggo2.listIterator();

		int line = 0;

		while (lit.hasNext()) {

			System.out.print(lit.next() + "\t");

			line++;

			if (line % 5 == 0)
				System.out.println("\n");

		}

	}

}
