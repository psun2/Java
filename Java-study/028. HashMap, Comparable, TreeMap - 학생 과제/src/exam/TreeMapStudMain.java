package exam;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

class TrStud implements Comparable { // 학생정보
	String name, kind, gender;
	int ban, tot, avg, rank;
	int[] jum;

	public TrStud(int ban, String name, String gender, int... jum) { // 생성자
		super();
		this.ban = ban;
		this.name = name;
		this.jum = jum;
		this.gender = gender;

		calc();
	}

	void calc() { // 분류 및 평균 계산
		kind = new String[] { "일반", "특기생" }[jum.length - 3];

		tot = 0;

		for (int i : jum) {
			tot += i;
		}

		avg = tot / jum.length;
	}

	@Override
	public int compareTo(Object o) { // 정렬 // 정렬 안함
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String toString() {
		String ttt = ban + "\t" + name + "\t" + kind + "\t" + gender + "\t";

		int cut = 3;

		if (kind.equals("특기생"))
			cut = 4;

		for (int i = 0; i < cut; i++) {
			ttt += jum[i] + "\t";
		}

		if (cut == 3)
			ttt += "\t";

		ttt += tot + "\t" + avg + "\t" + rank;

		return ttt;
	}

}

class MySet extends TreeSet { // TreeSet 을 상속 받은 MySet 클래스

	ArrayList<TrStud> res;
	TrStud tot, avg, max, min;

	public MySet() { // 생성자를 이용하여 추가할 데이터를 삽입
		res = new ArrayList();
		tot = new TrStud(-1, "총점", "", 0, 0, 0, 0);
		avg = new TrStud(-1, "평균", "", 0, 0, 0, 0);
		max = new TrStud(-1, "최대", "", 0, 0, 0, 0);
		min = new TrStud(-1, "최소", "", 101, 101, 101, 101);
		res.add(tot);
		res.add(avg);
		res.add(max);
		res.add(min);

	}

	@Override
	public boolean add(Object e) { // set 을 상속 받았으므로 add 오버라이딩
		boolean res = super.add(e); // TreeSet 이 생성전
		calc(e); // calc 에 있는 ArrayList 를 가지고 갈수 있게 해줍니다.
		return res; // Set 에 추가 될수 있게 return
	}

	void calc(Object e) {

		// mpa의 맨 마지막 map 은 set을 가지고 있고 그 셋은 MySet 이고
		// MySet 안에는 모든 학생들이 다 들어 있음;
		// mapAdd 메소드 내에서 Set에서 add 를 진행 할때 마다 누적 되어 계속 값을 바꾸고
		// 바꾼 값에 add 를 시켜주고, 그 값에 추가로 add 를 하게 됨.
		// 결론 : gender Map 안에는 set 이 존재하고 그 set 안에는 그에 맞는
		// TrStud 클래스가 존재

		TrStud ts = (TrStud) e;

		max.gender = min.gender = avg.gender = tot.gender = ts.gender;
		max.kind = min.kind = avg.kind = tot.kind = ts.kind;
		max.ban = min.ban = avg.ban = tot.ban = ts.ban;

		for (int i = 0; i < ts.jum.length; i++) {

			tot.jum[i] += ts.jum[i];
//			avg.jum[i] = tot.jum[i] / size();
			avg.jum[i] = tot.jum[i] / ts.jum.length;

			if (max.jum[i] < ts.jum[i])
				max.jum[i] = ts.jum[i];

			if (min.jum[i] > ts.jum[i])
				min.jum[i] = ts.jum[i];
		}
	}

	void ppp() {

		for (TrStud ooo : res) {

			System.out.println(ooo);
		}

	}

}

public class TreeMapStudMain {

	static void mapAdd(TreeMap res, Object key, Object cc) {
		// 원본, 키, 뉴 맵
		if (res.containsKey(key)) // 원본map 에서 key 가 존재 한다면
			cc = res.get(key); // 임시 map 은 원본 map 의 key로 얻은 value 를 갖습니다.
		// cc 를 보내는 이유 만약 key 를 가진 데이타가 있다면 임시로 저장을 해야합니다.
		// 이런 구조는 즉 구조를 만들고 마디막에 데이타를 추가 합니다.

		res.put(key, cc); // ㅇ
	}

	static void mapPrint(Object oo, String pre) { // 형변환을 하지 않는 이유 : Set 과 Map 중 무엇이 들어 올지 몰라서 ...

		if (oo instanceof MySet) { // 형변환을 하지 않는 이유 : Set 과 Map 중 무엇이 들어 올지 몰라서 ...

			MySet ms = (MySet) oo;

			for (Object oo4 : ms) {
				System.out.println(oo4);
			}
			System.out.println("-------------------------------------");
			ms.ppp(); // MySet 에서 TreeSet에 + 시켜준 다른 정보들을 출력

		} else {

			TreeMap en = (TreeMap) oo;

			String myPre = pre;
			pre += " >>> ";

			for (Object oo3 : en.entrySet()) {
				Entry en3 = (Entry) oo3;

				System.out.println(myPre + en3.getKey());

				mapPrint(en3.getValue(), pre);
			}
		}
	}

	public static void main(String[] args) {
		TrStud[] arr = { new TrStud(1, "김연지", "여", 99, 99, 55), new TrStud(1, "홍성혁", "남", 90, 90, 90),
				new TrStud(2, "안정민", "남", 99, 88, 77), new TrStud(2, "김연섭", "남", 80, 100, 99, 90),
				new TrStud(2, "김휘진", "남", 87, 76, 70, 90), new TrStud(1, "김예솔", "여", 100, 90, 80),
				new TrStud(1, "오민석", "여", 70, 70, 80, 70), new TrStud(1, "장정호", "남", 70, 100, 80, 70),
				new TrStud(1, "김영재", "남", 80, 80, 70, 70), new TrStud(1, "김현준", "여", 90, 80, 70, 70),
				new TrStud(2, "박성언", "여", 7, 99, 99), new TrStud(3, "이주혁", "여", 99, 66, 99),
				new TrStud(2, "이호인", "남", 99, 65, 99), new TrStud(2, "김지민", "여", 99, 65, 99, 12),
				new TrStud(2, "박시현", "여", 19, 88, 88, 77), new TrStud(2, "이신협", "여", 100, 100, 100, 100) };

		TreeMap<Integer, TreeMap<String, TreeMap<String, TreeSet>>> res = new TreeMap();

		for (TrStud tr : arr) {
			mapAdd(res, tr.ban, new TreeMap());
			mapAdd(res.get(tr.ban), tr.kind, new TreeMap());
//			res.get(tr.ban).get(tr.kind).put("key", "value"); 
			mapAdd(res.get(tr.ban).get(tr.kind), tr.gender, new MySet());
			// 모든 구조를 만든 후에
			res.get(tr.ban).get(tr.kind).get(tr.gender).add(tr);
			// 그 구조에 맞는 set에 데이터를 넣습니다.

		}

		mapPrint(res, ">> ");

	}

}