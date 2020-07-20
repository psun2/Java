package coll_p;

import java.util.Comparator;
import java.util.TreeSet;

class TreeStud {

	String name;
	int ban, avg;

	public TreeStud(int ban, String name, int avg) {
		super();
		this.name = name;
		this.ban = ban;
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "TreeStud [ban=" + ban + ", name=" + name + ", avg=" + avg + "]";
	}

}

class StudCom implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		TreeStud me = (TreeStud) o1;
		TreeStud you = (TreeStud) o2;

		int res = me.ban - you.ban; // 나머진 반이 다 같아서 2명만 나옴

		if (res == 0)
			res = you.avg - me.avg;

		return res;
	}

}

public class TreeSetExamMain {

	public static void main(String[] args) {

		TreeSet ts = new TreeSet(new StudCom());

		ts.add(new TreeStud(1, "이호인", 67));
		ts.add(new TreeStud(2, "김영재", 77));
		ts.add(new TreeStud(1, "박성언", 97));
		ts.add(new TreeStud(2, "김예솔", 57));
		ts.add(new TreeStud(2, "김휘진", 47));
		ts.add(new TreeStud(1, "안정민", 87));

		// 정리할 방법이 없어 Error

		for (Object object : ts) {
			System.out.println(object);
		}

	}

//	TreeStud [ban=1, name=박성언, avg=97]
//			TreeStud [ban=1, name=안정민, avg=87]
//			TreeStud [ban=1, name=이호인, avg=67]
//			TreeStud [ban=2, name=김영재, avg=77]
//			TreeStud [ban=2, name=김예솔, avg=57]
//			TreeStud [ban=2, name=김휘진, avg=47]

	// 반, 구분, 평규, 등수
	// 등수나 평균이 같다면 이름으로 분류
	// String == compareto 가 있음.....
	// 이름마저 같다면 .... ?

}
