package theory;

import java.util.ArrayList;

public class ArrayListBaseBallMain {

	public static void main(String[] args) {

//		변경전
//		1군:[박재상, 박정권, 최정, 김광현, 엄정욱, 박희수, 이호준]
//		2군:[이호준, 엄정욱, 박재홍, 이신협, 장동건]
//		FA:[이병규, 이승엽, 박정권, 장동건, 박용택, 홍성흔]

//		변경후
//		1군:[박재상, 최정, 김광현, 박희수]
//		2군:[이호준, 엄정욱, 박재홍, 이신협]
//		FA:[이병규, 이승엽, 박용택, 홍성흔]

		ArrayList t1 = new ArrayList();
		ArrayList t2 = new ArrayList();
		ArrayList fa = new ArrayList();

		for (Object object : "박재상, 박정권, 최정, 김광현,엄정욱, 박희수, 이호준".split(",")) {
			t1.add(object);
		}

		for (Object object : "이호준, 엄정욱, 박재홍,이신협, 장동건".split(",")) {
			t2.add(object);
		}

		for (Object object : "이병규, 이승엽, 박정권, 장동건, 박용택, 홍성흔".split(",")) {
			fa.add(object);
		}

		System.out.println("변경전");
		System.out.println("1군 : " + t1);
		System.out.println("2군 : " + t2);
		System.out.println("fa : " + fa);

		// 2군에는 1군과 겹치는 인원이 없음
		ArrayList buf = (ArrayList) fa.clone();
		fa.removeAll(t1);
		fa.removeAll(t2);

		t1.removeAll(t2); // 일군에서 이군과 겹친 인원 제거
		t1.removeAll(buf); // 일군에서 fa clone 인원을 제거

		t2.removeAll(buf); // 2군에서 fa 인원 제거

		System.out.println("변경후");
		System.out.println("1군 : " + t1);
		System.out.println("2군 : " + t2);
		System.out.println("fa : " + fa);

	}

}

//변경전
//1군 : [박재상,  박정권,  최정,  김광현, 엄정욱,  박희수,  이호준]
//2군 : [이호준,  엄정욱,  박재홍, 이신협,  장동건]
//fa : [이병규,  이승엽,  박정권,  장동건,  박용택,  홍성흔]
//변경후
//1군 : [박재상,  최정,  김광현, 엄정욱,  박희수,  이호준]
//2군 : [이호준,  엄정욱,  박재홍, 이신협]
//fa : [이병규,  이승엽,  박용택,  홍성흔]
