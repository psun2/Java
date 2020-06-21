package exam;

import java.util.ArrayList;
import java.util.TreeSet;

class Outer {

	TreeSet<AAA> test;

	public Outer() {
		this.test = new TreeSet<AAA>();
	}

	class AAA implements Comparable<AAA> {

		String name;
		int sum, avg, rank = 1;

		public AAA() {
			Outer.this.test.add(this);
		}

		@Override
		public int compareTo(AAA you) { // 정렬

			int result = avg - you.avg;

			if (result == 0)
				name.compareTo(you.name);

			return result;
		}

	}
}

public class test5 {

	public static void main(String[] args) {

		Outer test = new Outer();

		Outer.AAA a1 = test.new AAA();
		Outer.AAA a2 = test.new AAA();
		Outer.AAA a3 = test.new AAA();

		System.out.println(test.test);
		
		Outer.AAA[] arr = {};

	}

}
