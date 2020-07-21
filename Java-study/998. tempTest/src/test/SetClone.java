package test;

import java.util.ArrayList;
import java.util.HashSet;

public class SetClone {

	public static void main(String[] args) {

		HashSet set = new HashSet();

		set.add("1234");

		ArrayList list = new ArrayList(set);

		for (Object object : list) {
			System.out.println(object);
		}

	}

}
