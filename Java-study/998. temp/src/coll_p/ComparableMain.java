package coll_p;

import java.util.TreeSet;

class StudCCC implements Comparable {
	String name;
	int jum, ban;

	public StudCCC(int ban, String name, int jum) {
		super();
		this.ban = ban;
		this.name = name;
		this.jum = jum;
	}

	@Override
	public String toString() {
		return ban + ", " + name + ", " + jum;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub

		StudCCC you = (StudCCC) o;

		int res = ban - you.ban;

		if (res == 0)
			res = you.jum - jum;

		return res;
	}

}

public class ComparableMain {

	public static void main(String[] args) {

		TreeSet set = new TreeSet();

		set.add(new StudCCC(1, "홍성혁", 72));
		set.add(new StudCCC(2, "박시현", 82));
		set.add(new StudCCC(1, "이주혁", 92));
		set.add(new StudCCC(1, "김연섭", 22));
		set.add(new StudCCC(2, "김현준", 52));
		set.add(new StudCCC(1, "오민석", 62));
		set.add(new StudCCC(2, "장정호", 72));
		set.add(new StudCCC(1, "김영재", 82));
		set.add(new StudCCC(2, "이호인", 92));

		for (Object object : set) {
			System.out.println(object);
		}

	}

}
