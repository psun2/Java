package exam;

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

class Bag2 implements Comparable<Bag2> {
	String name;

	Impo impo;
	Freq freq;
	Integer value;

	ArrayList<Integer> arr; // 중요도, 가치, 빈도 를 가지고 있음

	public Bag2(String name, Impo impo, Integer value, Freq freq) {
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

		int cnt = 0;
		Object sub = null;

		for (Integer key : arr) { // 첫루프 cnt = 1; 두번째 2; 세번째 3;

			sub = new TreeMap();

			cnt++;

			if (cnt == arr.size())
				sub = new TreeSet();

			if (map.containsKey(key))
				sub = map.get(key);

			map.put(key, sub); // 맵에 sub을 put 해줌으로써, 맵의 value는 sub의 주소를 갖게 됨.

//			‼‼ 총 정리
//			메소드 내부에서 바꾸는 주소는 외부에 영향을 주지 않습니다.
//			why ? return 을 시키지 않기 때문에 메소드 내부에서만 사용 합니다.
//			main for문 [전] : 366712642
//			메소드 내부에서의 map의 주소 : 366712642
//			main for문 [후] : 366712642
//			map 에 put 을 하고, 
//			map 을 sub 으로 바꾸는 이유는
//			원본 map 의 안에 있는 또다른 sub 이라는 주소로 접근을 하기 위해서 입니다.
//			그럼 이때 map 가르키는건 원본 map 안에 있는 또다른 map 이 됩니다.
//			System.out.println(366712642 == 366712642); // true

//			System.out.println();
//			System.out.println(System.identityHashCode(map)); // 366712642
//			System.out.println();
//			System.out.println(System.identityHashCode(sub)); // 1829164700
//			System.out.println();

			if (cnt < arr.size()) // 마지막엔 set이 들어가기때문에 // 마지막 루프에 조건문 false
				map = (TreeMap) sub;
//				map = (TreeMap) map.get(key);

//			System.out.println();
//			System.out.println(System.identityHashCode(map)); // 1829164700
//			System.out.println();
//			System.out.println(System.identityHashCode(sub)); // 1829164700
//			System.out.println();

		}

		((TreeSet) sub).add(this);

	}

	@Override
	public String toString() {
		return name + "\t" + impo + "\t" + value + "\t" + freq;
	}

	@Override
	public int compareTo(Bag2 o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}
}

public class GenericBagMain {

	public static void main(String[] args) {

		TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, TreeSet<Bag2>>>> map = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, TreeSet<Bag2>>>>();

		Bag2[] arr = { new Bag2("파우치", Impo.High, 4, Freq.Always), new Bag2("화장품", Impo.High, 4, Freq.Often),
				new Bag2("스마트폰", Impo.High, 5, Freq.Sometimes), new Bag2("지갑", Impo.High, 3, Freq.Often),
				new Bag2("신분증", Impo.High, 3, Freq.Sometimes), new Bag2("운전면허", Impo.High, 2, Freq.Always),
				new Bag2("티슈", Impo.Middle, 4, Freq.Always), new Bag2("쿠폰", Impo.Middle, 2, Freq.Sometimes),
				new Bag2("안경", Impo.Middle, 2, Freq.Often), new Bag2("노트북", Impo.Middle, 3, Freq.Sometimes),
				new Bag2("필통", Impo.Middle, 3, Freq.Often), new Bag2("책", Impo.Low, 1, Freq.Sometimes),
				new Bag2("껌", Impo.Low, 2, Freq.Always), new Bag2("호신용 칼", Impo.Low, 1, Freq.Often),
				new Bag2("물티슈1", Impo.Low, 2, Freq.Always), new Bag2("파우1치", Impo.High, 4, Freq.Always),
				new Bag2("화1장품", Impo.High, 4, Freq.Often), new Bag2("스2마트폰", Impo.High, 5, Freq.Sometimes),
				new Bag2("지3갑", Impo.High, 3, Freq.Often), new Bag2("신분3증", Impo.High, 3, Freq.Sometimes),
				new Bag2("운전4면허", Impo.High, 2, Freq.Always), new Bag2("티5슈", Impo.Middle, 4, Freq.Always),
				new Bag2("6쿠폰", Impo.Middle, 2, Freq.Sometimes), new Bag2("7안경", Impo.Middle, 2, Freq.Often),
				new Bag2("노트8북", Impo.Middle, 3, Freq.Sometimes), new Bag2("필9통", Impo.Middle, 3, Freq.Often),
				new Bag2("8책", Impo.Low, 1, Freq.Sometimes), new Bag2("껌7", Impo.Low, 2, Freq.Always),
				new Bag2("호신7용 칼", Impo.Low, 1, Freq.Often), new Bag2("물티5슈", Impo.Low, 2, Freq.Always), };

//		System.out.println("main for문 [전] : " + System.identityHashCode(map));
		for (Bag2 Bag2 : arr)
			Bag2.add(map);
//		System.out.println("main for문 [후] : " + System.identityHashCode(map));

		for (Entry<Integer, TreeMap<Integer, TreeMap<Integer, TreeSet<Bag2>>>> en : map.entrySet()) {
			System.out.println(Impo.values()[en.getKey()]);

			for (Entry<Integer, TreeMap<Integer, TreeSet<Bag2>>> en2 : en.getValue().entrySet()) {
				System.out.println(">>>" + en2.getKey());

				for (Entry<Integer, TreeSet<Bag2>> en3 : en2.getValue().entrySet()) {
					System.out.println("\t" + Freq.values()[en3.getKey()]);

					for (Bag2 Bag2 : en3.getValue()) {
						System.out.println(Bag2);
					}
				}
			}

//			System.out.println("성언님 진궁에 빠지셨나요?");
		}

	}

}

//High
//>>>2
//	Always
//운전4면허	High	2	Always
//운전면허	High	2	Always
//>>>3
//	Often
//지3갑	High	3	Often
//지갑	High	3	Often
//	Sometimes
//신분3증	High	3	Sometimes
//신분증	High	3	Sometimes
//>>>4
//	Always
//파우1치	High	4	Always
//파우치	High	4	Always
//	Often
//화1장품	High	4	Often
//화장품	High	4	Often
//>>>5
//	Sometimes
//스2마트폰	High	5	Sometimes
//스마트폰	High	5	Sometimes
//Middle
//>>>2
//	Often
//7안경	Middle	2	Often
//안경	Middle	2	Often
//	Sometimes
//6쿠폰	Middle	2	Sometimes
//쿠폰	Middle	2	Sometimes
//>>>3
//	Often
//필9통	Middle	3	Often
//필통	Middle	3	Often
//	Sometimes
//노트8북	Middle	3	Sometimes
//노트북	Middle	3	Sometimes
//>>>4
//	Always
//티5슈	Middle	4	Always
//티슈	Middle	4	Always
//Low
//>>>1
//	Often
//호신7용 칼	Low	1	Often
//호신용 칼	Low	1	Often
//	Sometimes
//8책	Low	1	Sometimes
//책	Low	1	Sometimes
//>>>2
//	Always
//껌	Low	2	Always
//껌7	Low	2	Always
//물티5슈	Low	2	Always
//물티슈1	Low	2	Always