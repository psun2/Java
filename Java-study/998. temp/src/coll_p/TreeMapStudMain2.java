package coll_p;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
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
	public String toString() { // 출력
		return ban + "\t" + name + "\t" + kind + "\t" + gender + "\t" + Arrays.toString(jum) + "\t" + tot + "\t" + avg
				+ "\t" + rank;
	}

}

class MySet extends TreeSet { // TreeSet 을 상속 받은 MySet 클래스

	LinkedHashMap res;

	public MySet() { // 생성자
		res = new LinkedHashMap();
		res.put("총점", new TrStud(-1, "총점", "", 0, 0, 0, 0));
		res.put("평균", new TrStud(-1, "평균", "", 0, 0, 0, 0));
		res.put("Max", new TrStud(-1, "Max", "", -1, -1, -1, -1));
		res.put("Min", new TrStud(-1, "Min", "", 101, 101, 101, 101));
	}

	@Override
	public boolean add(Object e) { // set 을 상속 받았으므로 add 오버라이딩
		boolean res = super.add(e);
		calc(e);
		return res;
	}

	void calc(Object e) { // mpa의 맨 마지막 map 은 set을 가지고 있고 그 셋은 MySet 이고
		// MySet 안에는 모든 학생들이 다 들어 있음;
		// mapAdd 메소드 내에서 Set에서 add 를 진행 할때 마다 누적 되어 계속 값을 바꾸고
		// 바꾼 값에 add 를 시켜주고, 그 값에 추가로 add 를 하게 됨.
		// 결론 : gender Map 안에는 set 이 존재하고 그 set 안에는 그에 맞는
		// TrStud 클래스가 존재

		TrStud ts = (TrStud) e;

		TrStud tot = (TrStud) res.get("총점");
		TrStud avg = (TrStud) res.get("평균");
		TrStud max = (TrStud) res.get("Max");
		TrStud min = (TrStud) res.get("Min");

		avg.gender = tot.gender = ts.gender;
		avg.kind = tot.kind = ts.kind;
		avg.ban = tot.ban = ts.ban;

		max.gender = ts.gender;
		max.kind = ts.kind;
		max.ban = ts.ban;

		min.gender = ts.gender;
		min.kind = ts.kind;
		min.ban = ts.ban;

		for (int i = 0; i < ts.jum.length; i++) { // add 가 될때마다 TrStud 가 증가

			tot.jum[i] += ts.jum[i];
			avg.jum[i] = tot.jum[i] / size();

			if (ts.jum[i] > max.jum[i])
				max.jum[i] = ts.jum[i];

			if (min.jum[i] > ts.jum[i])
				min.jum[i] = ts.jum[i];

		}

	}

	void ppp() {
		for (Object ooo : res.entrySet()) {
			Entry en = (Entry) ooo;
			System.out.println(en.getValue());
		}
	}

}

public class TreeMapStudMain2 {

	static void mapAdd(Object mm, Object key, Object cc) {
		// 원본, 키, 뉴 맵
		TreeMap res = (TreeMap) mm;

		if (res.containsKey(key))
			cc = res.get(key);

		res.put(key, cc);
	}

	static void mapPrint(Object oo) {

		if (oo instanceof MySet) {

			MySet ms = (MySet) oo;

			for (Object oo4 : ms) {
				System.out.println(oo4);
			}
			ms.ppp();

		} else {

			TreeMap en = (TreeMap) oo;

			for (Object oo3 : en.entrySet()) {
				Entry en3 = (Entry) oo3;

				System.out.println("      " + en3.getKey());

				mapPrint(en3.getValue());
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

		TreeMap res = new TreeMap();

		for (TrStud tr : arr) {
			mapAdd(res, tr.ban, new TreeMap());
			mapAdd(res.get(tr.ban), tr.kind, new TreeMap());
			mapAdd(((TreeMap) res.get(tr.ban)).get(tr.kind), tr.gender, new MySet());
			((TreeSet) ((TreeMap) ((TreeMap) res.get(tr.ban)).get(tr.kind)).get(tr.gender)).add(tr);

		}

		mapPrint(res);

	}

}
