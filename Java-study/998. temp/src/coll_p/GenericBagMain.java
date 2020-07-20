package coll_p;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * 
 *     여성 - 가방을 정리하세요
    종류별로 출력할 것
    파우치, 화장품, 신분증, 쿠폰, 책, 전자제품 ...
 
    1. 중요도, 2. 가치, 3. 빈도
 
 * 중요도를 2개나 3개로 나눔
 * 가치(화폐가치)가 있는지 없는지 ex) 운전 면허증
 * 
 * 
 */

enum Impo {
	High, Middle, Low
}

enum Freq {
	Always, Often, Sometimes
}

class Bag implements Comparable<Bag> {
	String name;

	Impo impo;
	Freq freq;
	Integer value;

	ArrayList<Integer> arr; // 중요도, 가치, 빈도 를 가지고 있음

	public Bag(String name, Impo impo, Integer value, Freq freq) {
		super();
		this.name = name;
		this.impo = impo;
		this.value = value;
		this.freq = freq;

		arr = new ArrayList<Integer>();
		arr.add(impo.ordinal());
		arr.add(value);
		arr.add(freq.ordinal());
	}

	void add(TreeMap map) {
		System.out.println("밖 : map : " + map);

		TreeMap map2 = map;

		int cnt = 0;
		Object sub = null;

//		System.out.println(map);

		for (Integer key : arr) { // 첫루프 cnt = 1; 두번째 2; 세번째 3;

			System.out.println("반복문 안에서의  Map : " + map);
			System.out.println("반복문 안에서의  sub : " + sub);

			sub = new TreeMap();
			System.out.println("2222반복문 안에서의  sub : " + sub);
//			System.out.println("증가전 cnt : " + cnt);
			cnt++;

			if (cnt == arr.size())
				sub = new TreeSet();

			if (map.containsKey(key))
				sub = map.get(key);

			map.put(key, sub);

			System.out.println("put 후 map" + map);

//			System.out.println("arr.size : " + arr.size());
//			System.out.println("cnt : " + cnt);
//			System.out.print("cnt < arr.size() : ");
//			System.out.println(cnt < arr.size());

			if (cnt < arr.size()) // 마지막엔 set이 들어가기때문에 // 마지막 루프에 조건문 false
				map = (TreeMap) sub;

			System.out.println(map == sub);

			System.out.println("바뀐후 map : " + map);
			System.out.println("바뀐후 sub : " + sub);
		}

		((TreeSet) sub).add(this);

		System.out.println("반복문이 끝나고의 map : " + map);
	}

	@Override
	public String toString() {
		return name + "\t" + impo + "\t" + value + "\t" + freq;
	}

	@Override
	public int compareTo(Bag o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}
}

public class GenericBagMain {

	public static void main(String[] args) {

		TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, TreeSet<Bag>>>> map = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, TreeSet<Bag>>>>();

		Bag[] arr = { new Bag("파우치", Impo.High, 4, Freq.Always), new Bag("화장품", Impo.High, 4, Freq.Often),
				new Bag("스마트폰", Impo.High, 5, Freq.Sometimes), new Bag("지갑", Impo.High, 3, Freq.Often),
				new Bag("신분증", Impo.High, 3, Freq.Sometimes), new Bag("운전면허", Impo.High, 2, Freq.Always),
				new Bag("티슈", Impo.Middle, 4, Freq.Always), new Bag("쿠폰", Impo.Middle, 2, Freq.Sometimes),
				new Bag("안경", Impo.Middle, 2, Freq.Often), new Bag("노트북", Impo.Middle, 3, Freq.Sometimes),
				new Bag("필통", Impo.Middle, 3, Freq.Often), new Bag("책", Impo.Low, 1, Freq.Sometimes),
				new Bag("껌", Impo.Low, 2, Freq.Always), new Bag("호신용 칼", Impo.Low, 1, Freq.Often),
				new Bag("물티슈1", Impo.Low, 2, Freq.Always), new Bag("파우1치", Impo.High, 4, Freq.Always),
				new Bag("화1장품", Impo.High, 4, Freq.Often), new Bag("스2마트폰", Impo.High, 5, Freq.Sometimes),
				new Bag("지3갑", Impo.High, 3, Freq.Often), new Bag("신분3증", Impo.High, 3, Freq.Sometimes),
				new Bag("운전4면허", Impo.High, 2, Freq.Always), new Bag("티5슈", Impo.Middle, 4, Freq.Always),
				new Bag("6쿠폰", Impo.Middle, 2, Freq.Sometimes), new Bag("7안경", Impo.Middle, 2, Freq.Often),
				new Bag("노트8북", Impo.Middle, 3, Freq.Sometimes), new Bag("필9통", Impo.Middle, 3, Freq.Often),
				new Bag("8책", Impo.Low, 1, Freq.Sometimes), new Bag("껌7", Impo.Low, 2, Freq.Always),
				new Bag("호신7용 칼", Impo.Low, 1, Freq.Often), new Bag("물티5슈", Impo.Low, 2, Freq.Always), };

		for (Bag bag : arr)
			bag.add(map);

		for (Entry<Integer, TreeMap<Integer, TreeMap<Integer, TreeSet<Bag>>>> en : map.entrySet()) {
			System.out.println(Impo.values()[en.getKey()]);

			for (Entry<Integer, TreeMap<Integer, TreeSet<Bag>>> en2 : en.getValue().entrySet()) {
				System.out.println(">>>" + en2.getKey());

				for (Entry<Integer, TreeSet<Bag>> en3 : en2.getValue().entrySet()) {
					System.out.println("\t" + Freq.values()[en3.getKey()]);

					for (Bag bag : en3.getValue()) {
						System.out.println(bag);
					}
				}
			}

			System.out.println("성언님 진궁에 빠지셨나요?");
		}

	}

}
