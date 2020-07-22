package test;

import java.util.HashMap;



class MapTest {

	HashMap<String, String> test;

	public MapTest() {
		// TODO Auto-generated constructor stub

		test = new HashMap<String, Objrct>();

		test.put("1", "일이다");
		test.put("2", "이이다");
		test.put("3", "삼이다");
		test.put("4", "사이다");
		test.put("5", "오이다");

		for (String a : test.values()) {
			System.out.println(a);
		}

	}

}

public class HashMapTest {

	public static void main(String[] args) {
		new MapTest();
	}

}
