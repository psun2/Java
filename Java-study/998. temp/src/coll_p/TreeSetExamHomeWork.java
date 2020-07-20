package coll_p;

import java.util.Comparator;
import java.util.TreeSet;

class SetStud {
	String name, kind;
	int[] jum;
	int ban, tot, avg, rank;

	public SetStud(int ban, String name, int... jum) {
		super();
		this.ban = ban;
		this.name = name;
		this.jum = jum;
		calc();
	}

	void calc() {
		kind = new String[] { "일반", "특기생" }[jum.length - 3];

		tot = 0;
		for (int i : jum) {
			tot += i;
		}

		avg = tot / jum.length;
	}

	void rankCalc(int type, TreeSet set) {
		// 0:전체, 1:반, 2:반,kind
		rank = 1;
		for (Object object : set) {
			SetStud you = (SetStud) object;

			if (avg < you.avg) {
				if (type == 1 && ban == you.ban)
					rank++;
				else if (type == 2 && ban == you.ban && kind.equals(you.kind))
					rank++;
				else if (type == 0)
					rank++;
			}
		}
	}

	@Override
	public String toString() {
		String ttt = ban + "\t" + name + "\t" + kind + "\t";

		for (int i : jum) {
			ttt += i + "\t";
		}

		if (kind.equals("일반"))
			ttt += "\t";

		ttt += tot + "\t" + avg + "\t" + rank;
		return ttt;
	}

}

class SetStudCom implements Comparator {

	int type;

	public SetStudCom(int type) {
		super();
		this.type = type;
	}

	@Override
	public int compare(Object o1, Object o2) {
		SetStud me = (SetStud) o1;
		SetStud you = (SetStud) o2;

		int res = 0;

		if (type != 0)
			res = me.ban - you.ban;

		if (type == 2 && res == 0)
			res = me.kind.compareTo(you.kind);

		if (res == 0)
			res = you.avg - me.avg;

		if (res == 0)
			res = me.name.compareTo(you.name);

		return res;
	}
}

public class TreeSetExamHomeWork {

	public static void main(String[] args) {

		int type = 2;

		TreeSet studs = new TreeSet(new SetStudCom(type));

		studs.add(new SetStud(1, "김연지", 99, 99, 55));
		studs.add(new SetStud(1, "홍성혁", 90, 90, 90));
		studs.add(new SetStud(2, "안정민", 99, 88, 77));
		studs.add(new SetStud(2, "김연섭", 80, 85, 99, 90));
		studs.add(new SetStud(2, "김휘진", 87, 76, 70, 90));
		studs.add(new SetStud(1, "김까망", 100, 90, 80));
		studs.add(new SetStud(1, "김제니", 90, 80, 80, 70));
		studs.add(new SetStud(1, "김제니", 90, 80, 70, 70));
		studs.add(new SetStud(2, "김먼지", 7, 99, 99));
		studs.add(new SetStud(2, "김예솔", 99, 66, 99));

		for (Object obj : studs) {
			((SetStud) obj).rankCalc(type, studs);
		}

		for (Object obj : studs) {
			System.out.println(obj);
		}
	}

}
