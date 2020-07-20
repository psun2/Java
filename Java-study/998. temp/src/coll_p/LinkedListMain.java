package coll_p;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListMain {

	static void add_1(String title, List list) {

		long ff = System.currentTimeMillis(); // 시간측정
		// ff = 시작시간

		for (int i = 0; i < 1000000; i++) {
			list.add(1234);
		}
		System.out.println("순차추가 => " + title + " : " + (System.currentTimeMillis() - ff) + "초 걸림");
		// 현재시간 - 시작시간 = 걸린 Time
	}

	static void add_2(String title, List list) {

		long ff = System.currentTimeMillis(); // 시간측정
		// ff = 시작시간

		for (int i = 0; i < 10000; i++) {
			list.add(100, 1234); // 비 순차적인 추가
		}
		System.out.println("[비]순차추가 => " + title + " : " + (System.currentTimeMillis() - ff) + "초 걸림");
		// 현재시간 - 시작시간 = 걸린 Time
	}

	static void remove_1(String title, List list) {

		long ff = System.currentTimeMillis(); // 시간측정
		// ff = 시작시간

		for (int i = list.size() - 1; i > 0; i--) {
			list.remove(i); // 순차적인 삭제 1234 = 값

		}
		System.out.println("순차삭제 => " + title + " : " + (System.currentTimeMillis() - ff) + "초 걸림");
		// 현재시간 - 시작시간 = 걸린 Time
	}

	static void remove_2(String title, List list) {

		long ff = System.currentTimeMillis(); // 시간측정
		// ff = 시작시간

		for (int i = 0; i < 10000; i++) {
			list.remove(100); // 비 순차적인 삭제 100 = index
		}
		System.out.println("[비]순차삭제 => " + title + " : " + (System.currentTimeMillis() - ff) + "초 걸림");
		// 현재시간 - 시작시간 = 걸린 Time
	}

	public static void main(String[] args) {

		ArrayList ar = new ArrayList();

		LinkedList link = new LinkedList();

		add_1("ar", ar); // 순차적 17초
		add_1("link", link); // 순차적 131초

		System.out.println();

		add_2("ar", ar); // 비순차적 903초
		add_2("link", link); // 비순차적 3초

		// ✔ ArrayLink = 순차적 추가 속도가 빠릅니다.
		// ✔ LinkedList = 순차적 속도는 ArrayLink 보다 느리지만 비순차적 속도는 ArrayLink 보다 빠릅니다.

		System.out.println();

		remove_2("ar", ar);
		remove_2("link", link);

		System.out.println();

		remove_1("ar", ar);
		remove_1("link", link);

	}

}
