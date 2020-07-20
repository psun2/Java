package coll_p;

import java.util.ArrayList;

public class ArrayListBaseBallMain {

	public static void main(String[] args) {
		ArrayList t1 = new ArrayList();
		ArrayList t2 = new ArrayList();
		ArrayList fa = new ArrayList();
		for (Object obj : "박재상,박정권,최정,김광현,엄정욱,박희수,이호준".split(",")) {
			t1.add(obj);
		}
		for (Object obj : "이호준,엄정욱,박재홍,이신협,장동건".split(",")) {
			t2.add(obj);
		}
		for (Object obj : "이병규,이승엽,박정권,장동건,박용택,홍성흔".split(",")) {
			fa.add(obj);
		}

		System.out.println("변경전");
		System.out.println("1군:" + t1);
		System.out.println("2군:" + t2);
		System.out.println("FA:" + fa);

		ArrayList buf = (ArrayList) fa.clone();
		fa.removeAll(t1);
		fa.removeAll(t2);

		t1.removeAll(t2);
		t1.removeAll(buf);

		t2.removeAll(buf);

		System.out.println("변경후");
		System.out.println("1군:" + t1);
		System.out.println("2군:" + t2);
		System.out.println("FA:" + fa);
	}

}
