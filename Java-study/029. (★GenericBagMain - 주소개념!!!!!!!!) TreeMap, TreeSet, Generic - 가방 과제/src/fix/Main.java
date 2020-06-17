package fix;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

class Bag { // 가방을 표현

	TreeMap<String, TreeMap<String, TreeMap<String, TreeSet<Item>>>> bag;
//	TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, TreeSet<Item>>>> bag;
	// 중요도 가치 빈도

	ArrayList<Item> items;

	public Bag() {
		this.bag = new TreeMap();
		this.items = new ArrayList();
	}

	void mapAdd() {

		bag.clear();

//		System.out.println("어디까지 진행    \t 1");

		for (Item item : items) {
//			System.out.println(item);
//			System.out.println("어디까지 진행    \t 2");
//			pushMap(this.bag, item.importance, new TreeMap()); // 중요도
			pushMap(this.bag, item.a, new TreeMap()); // 중요도
//			System.out.println("어디까지 진행    \t 4");
//			pushMap(bag.get(item.importance), item.value, new TreeMap()); // 가치
			pushMap(bag.get(item.a), item.b, new TreeMap()); // 가치
//			pushMap(bag.get(item.importance).get(item.value), item.frequency, new TreeSet()); // 빈도
			pushMap(bag.get(item.a).get(item.b), item.c, new TreeSet()); // 빈도
//			bag.get(item.importance).get(item.value).get(item.frequency).add(item);
			bag.get(item.a).get(item.b).get(item.c).add(item);
		}

	}

//	void pushMap(TreeMap bag, int key, Object obj) {
	void pushMap(TreeMap bag, String key, Object obj) {

//		System.out.println("어디까지 진행    \t 3");

		if (bag.containsKey(key))
			obj = bag.get(key);

		bag.put(key, obj);

	}

	void print(Object obj, String pre) {

		if (obj instanceof TreeSet) { // 형변환을 하지 않는 이유 : Set 과 Map 중 무엇이 들어 올지 몰라서 ...

			TreeSet set = (TreeSet) obj;

			for (Object oo4 : set) {
				System.out.println(oo4);
			}
//			System.out.println("-------------------------------------");
//			System.out.println(set.toString());

		} else {

			TreeMap en = (TreeMap) obj;

			String myPre = pre;
			pre += "\t";
			for (Object obj2 : en.entrySet()) {
				Entry en3 = (Entry) obj2;

				System.out.println(myPre + en3.getKey());

				print(en3.getValue(), pre);
			}
			System.out.println("---------------------------------------------------");
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
			Bag.this.items.add(this); // 생성시 나의 정보를 가방의 ArrayList에 입력
		}

		void use() { // 사용이될때 빈도수가 업데이트
			this.frequency++;
			String buf = "사용빈도 : " + frequency;
			this.c = buf;
//			System.out.println(this.item + " 사용됨");

			// 사용될시 빈도수 업데이트를 하기 위해
			int index = Bag.this.items.indexOf(this); // 가방안에 있는 나의 index 번호를 찾아서
			Bag.this.items.remove(index); // 지우고
			Bag.this.items.add(index, this); // 그자리에 업데이트 된 나의 정보를 입력
			Bag.this.mapAdd(); // 바뀐 ArrayList로 TreeMap update
		}

		@Override
		public String toString() {
			return "Item [item=" + item + ", importance=" + importance + ", value=" + value + ", frequency=" + frequency
					+ "]";
		}

		@Override
		public int compareTo(Item item) {

			int res = item.importance - importance; // 중요도

			if (res == 0)
				res = item.value - value; // 가치

			if (res == 0)
				res = item.frequency - frequency;

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

//			System.out.println(items[index].frequency);

		}

//		System.out.println(bag.items); // 여기까진 제대로 됨
//		System.out.println(bag.bag); // 여기까진 제대로 됨

//		bag.mapAdd(items);

		bag.print(bag.bag, "");

	}

}
