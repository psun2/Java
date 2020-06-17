package exam;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class TeacherTest {

	static void ooo(ArrayList arr) {

		System.out.println(arr);
//		arr.clear();
		arr.add(4);
		System.out.println(arr);

	}

	static void ooo2(TreeMap map) {

		TreeMap sub = new TreeMap();
		System.out.println("메소드 내부 => sub의 hash code : " + sub.hashCode());
		System.out.println("메소드 내부 => map의 hash code : " + map.hashCode());

		System.out.println("메소드 내부 => map : " + map);

//		map.clear();
		map.put("4", 4);
		System.out.println("put 후 => map : " + map);

		System.out.println("put 후 => sub의 hash code : " + sub.hashCode());
		System.out.println("put 후 => map의 hash code : " + map.hashCode());
		map = sub;
		System.out.println("교페 후 => sub의 hash code : " + sub.hashCode());
		System.out.println("교페 후 => map의 hash code : " + map.hashCode());

		System.out.println("교페 후 => map : " + map);

	}

//	static void ooo3(String[] key, TreeMap map, int i) {
//
//		TreeMap sub = new TreeMap();
//
//		System.out.println("메소드 내부 => sub의 hash code : " + sub.hashCode() + "\t" + i + "번째");
//		System.out.println("메소드 내부 => map의 hash code : " + map.hashCode() + "\t" + i + "번째");
//		System.out.println("메소드 내부 => map : " + map);
//		System.out.println(map == sub);
//
//		for (String string : key) {
//			System.out.println("put 전 => map : " + map);
//			System.out.println("put 전 => sub의 hash code : " + sub.hashCode() + "\t" + i + "번째");
//			System.out.println("put 전 => map의 hash code : " + map.hashCode() + "\t" + i + "번째");
//			System.out.println(map == sub);
//
//			if (map.containsKey(string))
//				sub = (TreeMap) map.get(string);
//
//			map.put(string, 7);
//			System.out.println("put 후 => map : " + map);
//			System.out.println("put 후 => sub의 hash code : " + sub.hashCode() + "\t" + i + "번째");
//			System.out.println("put 후 => map의 hash code : " + map.hashCode() + "\t" + i + "번째");
//			System.out.println(map == sub);
//
//			System.out.println(map == sub);
//			map = sub;
//			System.out.println(map == sub);
//
//			System.out.println("교페 후 => sub의 hash code : " + sub.hashCode() + "\t" + i + "번째");
//			System.out.println("교페 후 => map의 hash code : " + map.hashCode() + "\t" + i + "번째");
//			System.out.println("교페 후 => map : " + map);
//		}
//
//		map.clear();
//
//	}

	public static void main(String[] args) {

		TreeMap map = new TreeMap();

		TreeSet set = new TreeSet();

		map.put("트리셋", set);

		System.out.println(map);

		map = new TreeMap();

		System.out.println(map);

		set.add("내부 트리셋");

		System.out.println(map);

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();

		ArrayList arr = new ArrayList();

		arr.add(1);
		arr.add(2);
		arr.add(3);

		System.out.println(arr);

		ooo(arr);

		System.out.println(arr);

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();

		map.clear();

		System.out.println(map);

		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);

		System.out.println("(메소드 실행 전) map : " + map);

		System.out.println("(메소드 실행 전) map의 hash code : " + map.hashCode());

		ooo2(map);

		System.out.println("(메소드 실행 후) map의 hash code : " + map.hashCode());

		System.out.println("(메소드 실행 후) map : " + map);

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();

//		map = new TreeMap();
//
//		System.out.println("(메소드 실행 전) map : " + map);
//
//		System.out.println("(메소드 실행 전) map의 hash code : " + map.hashCode());
//
//		String[] keys = { "여기 참나무숲", "장수풍뎅이", "사슴벌레" };
//		int i = 1;
//		for (int j = 0; j < keys.length; j++) {
//			ooo3(keys, map, i);
//			i++;
//			System.out.println("요기맵 : " + map);
//		}
//
//		System.out.println("(메소드 실행 후) map의 hash code : " + map.hashCode());
//
//		System.out.println("(메소드 실행 후) map : " + map);

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println();

		TreeMap test = new TreeMap();

		TreeMap sub = new TreeMap();

		System.out.println(test);
		
		test.put("test1", sub);

		System.out.println(test);
		test = sub;
		System.out.println(test);

	}

}
