package theory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListMain {

	static void add_1(String title, List list) { // 순차적 추가

		long ff = System.currentTimeMillis(); // 시간측정
		// ff = 시작시간

		for (int i = 0; i < 1000000; i++) {
			list.add(1234); // 맨 마지막 인덱스에 추가 => 순차적임
		}

		System.out.println("순차추가 => " + title + " : " + (System.currentTimeMillis() - ff) + "초 걸림");
		// 현재시간 - 시작시간 = 걸린 Time
	}

	static void add_2(String title, List list) { // 비 순차적 추가

		long ff = System.currentTimeMillis(); // 시간측정
		// ff = 시작시간

		for (int i = 0; i < 9999; i++) {
			list.add(100, 1234); // 비 순차적인 추가
		}
		System.out.println("[비]순차추가 => " + title + " : " + (System.currentTimeMillis() - ff) + "초 걸림");
		// 현재시간 - 시작시간 = 걸린 Time
	}

	static void remove_1(String title, List list) {
		// 순차적이란....
		// 제일 마지막에 add 된것부터 지우는 것이 순차적인 삭제

		long ff = System.currentTimeMillis(); // 시간측정
		// ff = 시작시간

		for (int i = list.size() - 1; i > 0; i--) {
			list.remove(i);
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

		System.out.println("ArrayList_LinkedList_Node_delete.jpg");

		ArrayList ar = new ArrayList();

		LinkedList link = new LinkedList();

		
		System.out.println();
		add_1("ar", ar); // 순차추가 => ar : 17초 걸림
		add_1("link", link); // 순차추가 => link : 132초 걸림
//		System.out.println(ar);
		System.out.println("ar.size() : " + ar.size()); // 1000000
//		System.out.println(link);
		System.out.println("link.size() : " + link.size()); // 1000000

		System.out.println();

		add_2("ar", ar); // [비]순차추가 => ar : 932초 걸림
		add_2("link", link); // [비]순차추가 => link : 3초 걸림
		System.out.println("ar.size() : " + ar.size()); // 1009999
		System.out.println("link.size() : " + link.size()); // 1009999

		System.out.println();

		remove_2("ar", ar); // [비]순차삭제 => ar : 929초 걸림
		remove_2("link", link); // [비]순차삭제 => link : 1초 걸림
		System.out.println("ar.size() : " + ar.size()); // 1009999
		System.out.println("link.size() : " + link.size()); // 1009999

		System.out.println();

		remove_1("ar", ar); // 순차삭제 => ar : 5초 걸림
		remove_1("link", link); // 순차삭제 => link : 11초 걸림
		System.out.println("ar.size() : " + ar.size()); // 1009999
		System.out.println("link.size() : " + link.size()); // 1009999

		System.out.println();
		System.out.println("순차적으로 작업을 할땐 ArrayList가 현저히 빠르고");
		System.out.println("비순차적으로 작업을 할땐 LinkedList 가 빠릅니다.");
	}

}
