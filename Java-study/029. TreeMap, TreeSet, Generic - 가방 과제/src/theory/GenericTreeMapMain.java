package theory;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

// <Generic> : 쓸데 없는 형변환을 하지 않아, 코드의 줄수가 줄어들고, 사소한 오류를 catch 할 수 있습니다.
// 028. project의 TreeMapMain 와 비교 하여 살펴 봅니다.

class TreeClothes implements Comparable<TreeClothes> {

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
	public int compareTo(TreeClothes you) {
		// TreeClothes you = (TreeClothes)o;
		int res = name.compareTo(you.name);

		if (res == 0)
			res = size - you.size;
		return res;
	}

}

public class GenericTreeMapMain {

	public static void main(String[] args) {
		TreeClothes[] tcArr = {

				new TreeClothes("in", "나이키", "헬스복", 90), new TreeClothes("out", "아디다스", "등산복", 100),
				new TreeClothes("in", "필라", "헬스복", 190), new TreeClothes("out", "아디다스", "런닝복", 100),
				new TreeClothes("inner", "나이키", "헬스복", 90), new TreeClothes("in", "필라", "헬스복", 100),
				new TreeClothes("out", "나이키", "런닝복", 100), new TreeClothes("in", "나이키", "등산복", 90),
				new TreeClothes("out", "아디다스", "등산복", 105), new TreeClothes("out", "나이키", "헬스복", 90),
				new TreeClothes("in", "필라", "헬스복", 105)

		};

		TreeMap<String, TreeMap<String, TreeSet<TreeClothes>>> map = new TreeMap();
		// map.put("in", new TreeSet());
		// map.put("out", new TreeSet());
		for (TreeClothes tc : tcArr) {

			// ((TreeSet)map.get(tc.io)).add(tc);

			TreeMap<String, TreeSet<TreeClothes>> sub = new TreeMap();

			if (map.containsKey(tc.io)) {
				sub = map.get(tc.io);
			}

			TreeSet<TreeClothes> set = new TreeSet();
			if (sub.containsKey(tc.brand)) {
				set = sub.get(tc.brand);
			}

			set.add(tc);
			sub.put(tc.brand, set);
			map.put(tc.io, sub);

		}

		for (Entry<String, TreeMap<String, TreeSet<TreeClothes>>> en : map.entrySet()) {

			System.out.println(en.getKey() + ">>>>>>>>>");

			for (Entry<String, TreeSet<TreeClothes>> en2 : en.getValue().entrySet()) {

				System.out.println("\t" + en2.getKey() + ":" + en2.getValue().size());

				for (TreeClothes sss : en2.getValue()) {
					System.out.println(sss);
				}
			}
			System.out.println();
		}

		// 반, 구분 별로 나누고 평균, 이름순으로 정렬하세요
	}

}