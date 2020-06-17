package theory;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

class TreeClothes implements Comparable {

	String io, brand, name;

	int size;

	public TreeClothes(String io, String brand, String name, int size) {
		super();
		this.io = io;
		this.brand = brand;
		this.name = name;
		this.size = size;
	}

	@Override
	public String toString() {
		return "TreeClothes [io=" + io + ", brand=" + brand + ", name=" + name + ", size=" + size + "]";
	}

	@Override
	public int compareTo(Object o) {
		TreeClothes you = (TreeClothes) o;
		int res = name.compareTo(you.name);

		if (res == 0)
			res = size - you.size;
		return res;
	}

}

public class TreeMapMain {

	public static void main(String[] args) {
		TreeClothes[] tcArr = {

				new TreeClothes("in", "나이키", "헬스복", 90), new TreeClothes("out", "아디다스", "등산복", 100),
				new TreeClothes("in", "필라", "헬스복", 190), new TreeClothes("out", "아디다스", "런닝복", 100),
				new TreeClothes("inner", "나이키", "헬스복", 90), new TreeClothes("in", "필라", "헬스복", 100),
				new TreeClothes("out", "나이키", "런닝복", 100), new TreeClothes("in", "나이키", "등산복", 90),
				new TreeClothes("out", "아디다스", "등산복", 105), new TreeClothes("out", "나이키", "헬스복", 90),
				new TreeClothes("in", "필라", "헬스복", 105)

		};

//		TreeMap 의 구조

//		map = {
//				(tc.io) : map(sub) = {
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//				},

//				(tc.io) : map(sub) = {
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//				},

//				(tc.io) : map(sub) = {
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//						(tc.brand) : set(TreeClothes, TreeClothes, TreeClothes ...),
//				},
//		}

		TreeMap map = new TreeMap();
		// map.put("in", new TreeSet());
		// map.put("out", new TreeSet());
		for (TreeClothes tc : tcArr) {

			// ((TreeSet)map.get(tc.io)).add(tc);

			TreeMap sub = new TreeMap();
			if (map.containsKey(tc.io)) {
				// map 이란 TreeMap 의 Key 값중
				// 반복문으로 하나씩 들어오는 tc.io 의 key 값이 있다면
				sub = (TreeMap) map.get(tc.io);
				// sub 이란 TreeMap 은
				// map 이란 TreeMap 의 key 값이
				// 반복문으로 하나씩 들어오는 tc.io 의 value를 참조 합니다.
			}

			TreeSet set = new TreeSet();
			if (sub.containsKey(tc.brand)) {
				set = (TreeSet) sub.get(tc.brand);
				// sub 은 정렬을 위한 데이터인 set을 가집니다.
			}

			// 검색은 큰 범위 => 작은 범위

			// 추가는 작은 범위 => 큰 범위

			set.add(tc);
			sub.put(tc.brand, set);
			map.put(tc.io, sub);

		}

		for (Object objects : map.entrySet()) {
			Entry en = (Entry) objects;
			System.out.println(en.getKey() + ">>>>>>>>>");

			for (Object objects2 : ((TreeMap) en.getValue()).entrySet()) {
				Entry en2 = (Entry) objects2;

				TreeSet set = (TreeSet) en2.getValue();
				System.out.println("\t" + en2.getKey() + ":" + set.size());

				for (Object sss : set) {
					System.out.println(sss);
				}
			}
			System.out.println();
		}

		// 반, 구분 별로 나누고 평균, 이름순으로 정렬하세요
	}

}