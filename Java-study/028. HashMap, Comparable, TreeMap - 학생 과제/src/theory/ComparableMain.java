package theory;

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

		// Comparator 과 다른점 인자를 1개 받는다.
		// set의 생성자로 넣어 주지 않고 클래서에서 바로 implements 한다.
		// 물론 Comparator 도 class 에서 바로 implements 가능 할 것으로 예상
		// 두개를 비교하는 것이 아니라, 자기 자신과 다른 set 객체들을 비교
		// 좀더 스마트 합니다.

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

//		1, 이주혁, 92
//		1, 김영재, 82
//		1, 홍성혁, 72
//		1, 오민석, 62
//		1, 김연섭, 22
//		2, 이호인, 92
//		2, 박시현, 82
//		2, 장정호, 72
//		2, 김현준, 52

	}

}