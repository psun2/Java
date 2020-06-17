package exam;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

class Bag { // 가방을 표현

	TreeMap<Integer, TreeMap<Integer, TreeSet<Item>>> bag;

	TreeSet<Item> items;

	public Bag() {
		this.bag = new TreeMap();
		this.items = new TreeSet();
	}

	void mapAdd() {

		bag.clear();

		for (Item item : items) {

			pushMap(this.bag, item.importance, new TreeMap()); // 중요도

			pushMap(bag.get(item.importance), item.value, new TreeSet()); // 가치

			bag.get(item.importance).get(item.value).add(item);
		}

	}

	void pushMap(TreeMap bag, int key, Object obj) {

		if (bag.containsKey(key))
			obj = bag.get(key);

		bag.put(key, obj);

	}

	void print() {

		for (Entry<Integer, TreeMap<Integer, TreeSet<Bag.Item>>> obj : this.bag.entrySet()) {

//			System.out.println(obj instanceof Entry);

			System.out.println("중요도 : " + obj.getKey());

			for (Entry<Integer, TreeSet<Bag.Item>> obj2 : obj.getValue().entrySet()) {

				System.out.println("\t가치 : " + obj2.getKey());

				for (Item object : obj2.getValue()) {
//					System.out.println("\t\t사용 빈도 : " + ((Item) object).frequency);
					System.out.println(object);
				}

			}
			System.out.println("--------------------------------------------------");
		}

	}

	class Item implements Comparable<Item> { // 가방속에 들어 있는 items 를 표현

		String item;
		int importance, value, frequency;

		public Item(String item, int importance, int value) { // 가방에 새로운 item을 생성
			this.item = item;
			this.importance = importance;
			this.value = value;
			this.frequency = 0;

			Bag.this.items.add(this); // 생성시 나의 정보를 가방의 TreeSet에 입력
			Bag.this.mapAdd(); // 사용하지 않을 수 있음.
		}

		void use() { // 사용이될때 빈도수가 업데이트
			this.frequency++;

			Bag.this.items.add(this); // 사용이 될때마다 가방의 TreeSet 업데이트

//			System.out.println(this.item + " 사용됨");

			Bag.this.mapAdd(); // 바뀐 TreeSet으로 TreeMap update
		}

		@Override
		public String toString() {
//			return "\t\t[item = " + item + ", 사용빈도 = " + frequency + ", 중요도 = " + importance + ", 가치 = " + value + "]";
//			return "\t\t[item = " + item + "\t사용빈도 = " + frequency + "]";

			String result = "\t\t";

			result += "[item = " + item;

			if (item.length() == 2)
				result += " ";

			result += "\t사용빈도 = " + frequency + "]";

			return result;
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

			int index = (int) (Math.random() * 9);

			items[index].use(); // 사용에 따른 사용 빈도수 증가

		}

		bag.print();

//	중요도 : 0
//		가치 : 1
//			[item = 드라이버	사용빈도 = 3]
//	--------------------------------------------------
//	중요도 : 10
//		가치 : 20
//			[item = 계산기	사용빈도 = 9]
//	--------------------------------------------------
//	중요도 : 11
//		가치 : 10
//			[item = 건전지	사용빈도 = 4]
//	--------------------------------------------------
//	중요도 : 15
//		가치 : 15
//			[item = 각도기	사용빈도 = 4]
//	--------------------------------------------------
//	중요도 : 55
//		가치 : 15
//			[item = 필통 	사용빈도 = 5]
//	--------------------------------------------------
//	중요도 : 85
//		가치 : 10
//			[item = 공책 	사용빈도 = 3]
//	--------------------------------------------------
//	중요도 : 100
//		가치 : 20
//			[item = 마스크	사용빈도 = 2]
//		가치 : 100
//			[item = 에어팟	사용빈도 = 5]
//			[item = 지갑 	사용빈도 = 4]
//	--------------------------------------------------

//	중요도 : 0
//		가치 : 1
//			[item = 드라이버	사용빈도 = 1]
//	--------------------------------------------------
//	중요도 : 10
//		가치 : 20
//			[item = 계산기	사용빈도 = 5]
//	--------------------------------------------------
//	중요도 : 11
//		가치 : 10
//			[item = 건전지	사용빈도 = 2]
//	--------------------------------------------------
//	중요도 : 15
//		가치 : 15
//			[item = 각도기	사용빈도 = 2]
//	--------------------------------------------------
//	중요도 : 55
//		가치 : 15
//			[item = 필통	사용빈도 = 3]
//	--------------------------------------------------
//	중요도 : 85
//		가치 : 10
//			[item = 공책	사용빈도 = 0]
//	--------------------------------------------------
//	중요도 : 100
//		가치 : 20
//			[item = 마스크	사용빈도 = 0]
//		가치 : 100
//			[item = 에어팟	사용빈도 = 1]
//			[item = 지갑	사용빈도 = 0]
//	--------------------------------------------------

//	중요도 : 0
//		가치 : 1
//	[item = 드라이버	사용빈도 = 10]
//	---------------------------------
//	중요도 : 10
//		가치 : 20
//	[item = 계산기	사용빈도 = 4]
//	---------------------------------
//	중요도 : 11
//		가치 : 10
//	[item = 건전지	사용빈도 = 10]
//	---------------------------------
//	중요도 : 15
//		가치 : 15
//	[item = 각도기	사용빈도 = 7]
//	---------------------------------
//	중요도 : 55
//		가치 : 15
//	[item = 필통	사용빈도 = 7]
//	---------------------------------
//	중요도 : 85
//		가치 : 10
//	[item = 공책	사용빈도 = 6]
//	---------------------------------
//	중요도 : 100
//		가치 : 20
//	[item = 마스크	사용빈도 = 0]
//		가치 : 100
//	[item = 지갑	사용빈도 = 9]
//	[item = 에어팟	사용빈도 = 6]
//	---------------------------------

//	중요도 : 0
//		가치 : 1
//			[item = 드라이버, 사용빈도 = 8, 중요도 = 0, 가치 = 1]
//
//	중요도 : 10
//		가치 : 20
//			[item = 계산기, 사용빈도 = 10, 중요도 = 10, 가치 = 20]
//
//	중요도 : 11
//		가치 : 10
//			[item = 건전지, 사용빈도 = 12, 중요도 = 11, 가치 = 10]
//
//	중요도 : 15
//		가치 : 15
//			[item = 각도기, 사용빈도 = 13, 중요도 = 15, 가치 = 15]
//
//	중요도 : 55
//		가치 : 15
//			[item = 필통, 사용빈도 = 10, 중요도 = 55, 가치 = 15]
//
//	중요도 : 85
//		가치 : 10
//			[item = 공책, 사용빈도 = 13, 중요도 = 85, 가치 = 10]
//
//	중요도 : 100
//		가치 : 20
//			[item = 마스크, 사용빈도 = 0, 중요도 = 100, 가치 = 20]
//		가치 : 100
//			[item = 지갑, 사용빈도 = 11, 중요도 = 100, 가치 = 100]
//			[item = 에어팟, 사용빈도 = 9, 중요도 = 100, 가치 = 100]

	}

}
