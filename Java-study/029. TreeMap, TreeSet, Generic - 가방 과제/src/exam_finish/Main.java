package exam_finish;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

class Bag { // 가방을 표현

	TreeMap<String, TreeMap<String, TreeSet<Item>>> bag;

	TreeSet<Item> items;

	public Bag() {
		this.bag = new TreeMap();
		this.items = new TreeSet();
	}

	void mapAdd() {

		bag.clear();

		for (Item item : items) {

//			pushMap(this.bag, item.a, new TreeMap()); // 중요도
			pushMap(this.bag, item.importance, new TreeMap()); // 중요도

//			pushMap(bag.get(item.a), item.b, new TreeMap()); // 가치
//			pushMap(bag.get(item.importance), item.value, new TreeMap()); // 가치
			pushMap(bag.get(item.importance), item.value, new TreeSet()); // 가치

//			pushMap(bag.get(item.a).get(item.b), item.c, new TreeSet()); // 빈도
//			pushMap(bag.get(item.importance).get(item.value), item.frequency, new TreeSet()); // 빈도

//			bag.get(item.a).get(item.b).get(item.c).add(item);
//			bag.get(item.importance).get(item.value).get(item.frequency).add(item);
			bag.get(item.importance).get(item.value).add(item);
		}

	}

//	void pushMap(TreeMap bag, String key, Object obj) {
	void pushMap(TreeMap bag, int key, Object obj) {

		if (bag.containsKey(key))
			obj = bag.get(key);

		bag.put(key, obj);

	}

	void print(Object obj, String previous) {

		if (obj instanceof TreeSet) { // 형변환을 하지 않는 이유 : Set 과 Map 중 무엇이 들어 올지 몰라서 ...

			TreeSet set = (TreeSet) obj;

			for (Object object : set) {
				System.out.println(object);
			}

		} else {

			TreeMap map = (TreeMap) obj;

			String myPrevius = previous;

			previous += "\t";

//			for (Object object : map.entrySet()) {
//				Entry en = (Entry) object;
//
//				System.out.println(myPrevius + en.getKey());
//
//				print(en.getValue(), previous);
//			}

			for (Object object : map.entrySet()) {

				Entry en = (Entry) object;

				System.out.println(myPrevius + en.getKey());

				print(en.getValue(), previous);
			}
//			System.out.println("---------------------------------------------------");
		}
	}

	class Item implements Comparable<Item> { // 가방속에 들어 있는 items 를 표현

		String item, a = "", b = "", c = "사용빈도 : 0";
		int importance, value, frequency;

		public Item(String item, int importance, int value) { // 가방에 새로운 item을 생성
			this.item = item;
			this.importance = importance;
			this.value = value;

			this.a += "중요도 : " + importance;
			this.b += "가치 : " + value;

			this.frequency = 0;

			System.out.println(item + " 생성자는 다 돌아요");
			Bag.this.items.add(this); // 생성시 나의 정보를 가방의 ArrayList에 입력
			Bag.this.mapAdd();
		}

		void use() { // 사용이될때 빈도수가 업데이트
			this.frequency++;
			String buf = "사용빈도 : " + frequency;
			this.c = buf;

			Bag.this.items.add(this); // 사용이 될때마다 가방의 TreeSet 업데이트

//			System.out.println(this.item + " 사용됨");

			Bag.this.mapAdd(); // 바뀐 TreeSet으로 TreeMap update
		}

		@Override
		public String toString() {
			return "\t\tItem [item=" + item + ", importance=" + importance + ", value=" + value + ", frequency="
					+ frequency + "]";
		}

		@Override
		public int compareTo(Item item) {

			int res = item.frequency - frequency;

			if (res == 0)
				res = this.item.compareTo(item.item);

			return res;
		}

	}

}

public class Main {

	public static void main(String[] args) {

//		여성 - 가방을 정리하세요
//		종류별로 출력할 것
//		파우치, 화장품, 신분증, 쿠폰, 책, 전자제품 ...
//
//		1. 중요도, 2. 가치, 3. 빈도

		Bag bag = new Bag();

		// 중요도 와 가치는 100 을 기준
		Bag.Item[] items = { bag.new Item("지갑", 100, 100), bag.new Item("에어팟", 100, 100), bag.new Item("드라이버", 0, 1),
				bag.new Item("건전지", 11, 10), bag.new Item("계산기", 10, 20), bag.new Item("각도기", 15, 15),
				bag.new Item("필통", 55, 15), bag.new Item("공책", 85, 10), bag.new Item("마스크", 100, 20) };

		int end = (int) (Math.random() * 100) + 1;

		for (int i = 0; i < end; i++) { // 가방안의 item 사용

			int index = (int) (Math.random() * 8);

			items[index].use(); // 사용에 따른 사용 빈도수 증가

		}

		bag.print(bag.bag, "");

//		System.out.println(bag.bag.get(100).get(20));
//		System.out.println(bag.items);
//		System.out.println(bag.bag);
//		System.out.println(bag.items2);

	}

}
