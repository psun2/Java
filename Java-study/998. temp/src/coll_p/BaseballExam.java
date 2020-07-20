package coll_p;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseballExam {

	public static void main(String[] args) {

		String[] one = { "박재상", "박정권", "최정", "김광현", "엄정욱", "박희수", "이호준" };
		String[] two = { "이호준", "엄정욱", "박재홍", "이신협", "장동건" };
		String[] fa = { "이병규", "이승엽", "박정권", "장동건", "박용택", "홍성흔" };

		ArrayList oneList = new ArrayList();
		ArrayList twoList = new ArrayList();
		ArrayList faList = new ArrayList();

		for (int i = 0; i < one.length; i++) {
			oneList.add(one[i]);
		}

		for (int i = 0; i < two.length; i++) {
			twoList.add(two[i]);
		}

		for (int i = 0; i < fa.length; i++) {
			faList.add(fa[i]);
		}

		System.out.println("변경전");
		System.out.println("1군 : " + oneList);
		System.out.println("2군 : " + twoList);
		System.out.println("fa : " + faList);

		String[] resultOne = { "박정권", "이호준" };
		String[] resultTwo = { "장동건" };
		String[] resultFa = { "이병규", "이승엽", "박용택", "홍성흔" };

		System.out.println();

		System.out.println("변경후");
		faList.removeAll(oneList);
		faList.removeAll(twoList);

		for (int i = 0; i < resultOne.length; i++) {
			oneList.remove(resultOne[i]);
		}
		System.out.println("1군 : " + oneList);
		twoList.remove(resultTwo[0]);
		System.out.println("2군 : " + twoList);		
		
//		ArrayList temp  = new Arrays();

		System.out.println("fa : " + faList);

//		for (int i = 0; i < oneList.size(); i++) {
//
//			for (int j = 0; j < faList.size(); j++) {
//				if (oneList.get(i).contains(faList.get(j)))
//					faList.remove(j);
//			}
//
//		}

	}

}
