package theory;

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

		int res = me.ban - you.ban;

		return res;
	}

}

class StudCom2 implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		TreeStud me = (TreeStud) o1;
		TreeStud you = (TreeStud) o2;

		int res = me.ban - you.ban;

		if (res == 0) // 0 즉 자기자신 => 반이 겹친다... 그헝다면 평균점수로 출력...
//			res = me.avg - you.avg;
			// but 점수는 높은 사람이 1등이기 때문에 you - me 를 빼줍니다
			res = you.avg - me.avg;

		return res;
	}

}

class StudCom3 implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		TreeStud me = (TreeStud) o1;
		TreeStud you = (TreeStud) o2;

		int res = me.ban - you.ban;

		if (res == 0) // 0 즉 자기자신 => 반이 겹친다... 그헝다면 평균점수로 출력...
//			res = me.avg - you.avg;
			// but 점수는 높은 사람이 1등이기 때문에 you - me 를 빼줍니다
			res = you.avg - me.avg;

		if (res == 0) // 0 즉 점수가 같다면.... 이제는 마지막 항목인 이름으로 정렬하여 출력
			res = me.name.compareTo(you.name);

		return res;
	}

}

public class TreeSetExamMain {

	public static void main(String[] args) {

		TreeSet ts = new TreeSet();

//		ts.add(new TreeStud(1, "이호인", 67));
		// TreeSet에 는 정렬이 되어
		// 들어가는데....
		// 정렬이 될 기준이 없어 add 시 Error
//		ts.add(new TreeStud(2, "김영재", 77));
//		ts.add(new TreeStud(1, "박성언", 97));
//		ts.add(new TreeStud(2, "김예솔", 57));
//		ts.add(new TreeStud(2, "김휘진", 47));
//		ts.add(new TreeStud(1, "안정민", 87));

		System.out.println("----------------------------------------");
		TreeSet ts2 = new TreeSet(new StudCom());
		// TreeSet 의 생성자의 인자로 정렬 할 수 있는 Comparator 인터페이스를 줍니다.

		ts2.add(new TreeStud(1, "이호인", 67));
		ts2.add(new TreeStud(2, "김영재", 77));
		ts2.add(new TreeStud(1, "박성언", 97));
		ts2.add(new TreeStud(2, "김예솔", 57));
		ts2.add(new TreeStud(2, "김휘진", 47));
		ts2.add(new TreeStud(1, "안정민", 87));

		for (Object object : ts2) {
			System.out.println(object);
		}

//		TreeStud [ban=1, name=이호인, avg=67]
//		TreeStud [ban=2, name=김영재, avg=77]

		System.out.println("반별로 정렬 후 출력");
		System.out.println("문제점 : 반은 1 과 2 밖에 없으므로 1반의 첫번째 와 2반의 첫번째만 출력후 출력이 안되는 현상");
		System.out.println("느낀점 : 모든 학생들이 다른 정보를 가질수 있는 기준이 되게 해주는 조건을 걸어 주어야 합니다.");

		System.out.println("----------------------------------------");

		TreeSet ts3 = new TreeSet(new StudCom2());
		// TreeSet 의 생성자의 인자로 정렬 할 수 있는 Comparator 인터페이스를 줍니다.

		ts3.add(new TreeStud(1, "이호인", 67));
		ts3.add(new TreeStud(2, "김영재", 77));
		ts3.add(new TreeStud(1, "박성언", 97));
		ts3.add(new TreeStud(2, "김예솔", 57));
		ts3.add(new TreeStud(2, "김휘진", 47));
		ts3.add(new TreeStud(1, "안정민", 87));

		// 위 학생들의 각각 다른 고유의 항목은 이름과 평균점수
		// 평균점수를 반을 기준으로 정렬하고 평균 점수를 기준으로 출력

		for (Object object : ts3) {
			System.out.println(object);
		}

//		TreeStud [ban=1, name=박성언, avg=97]
//		TreeStud [ban=1, name=안정민, avg=87]
//		TreeStud [ban=1, name=이호인, avg=67]
//		TreeStud [ban=2, name=김영재, avg=77]
//		TreeStud [ban=2, name=김예솔, avg=57]
//		TreeStud [ban=2, name=김휘진, avg=47]

		System.out.println("----------------------------------------");
		System.out.println("String1.compareTo(String2) => 1 과 2 를 비교하여 1이 작으면 음수, 1과 같으면 0, 1이 크면 양수 반환");
		TreeSet ts4 = new TreeSet(new StudCom3());
		// TreeSet 의 생성자의 인자로 정렬 할 수 있는 Comparator 인터페이스를 줍니다.

		ts4.add(new TreeStud(1, "이호인", 67));
		ts4.add(new TreeStud(2, "김영재", 67));
		ts4.add(new TreeStud(1, "박성언", 67));
		ts4.add(new TreeStud(2, "김예솔", 67));
		ts4.add(new TreeStud(2, "김휘진", 67));
		ts4.add(new TreeStud(1, "안정민", 67));

		// 위 학생들의 각각 다른 고유의 항목은 이름과 평균점수
		// 평균점수가 같을때 이름 별로 출력

		for (Object object : ts4) {
			System.out.println(object);
		}

		// 이름 정렬 전 출력
//		TreeStud [ban=1, name=이호인, avg=67]
//		TreeStud [ban=2, name=김영재, avg=67]

		// 반과 점수 이름 정렬 후 출력
//		TreeStud [ban=1, name=박성언, avg=67]
//		TreeStud [ban=1, name=안정민, avg=67]
//		TreeStud [ban=1, name=이호인, avg=67]
//		TreeStud [ban=2, name=김영재, avg=67]
//		TreeStud [ban=2, name=김예솔, avg=67]
//		TreeStud [ban=2, name=김휘진, avg=67]

	}

}
