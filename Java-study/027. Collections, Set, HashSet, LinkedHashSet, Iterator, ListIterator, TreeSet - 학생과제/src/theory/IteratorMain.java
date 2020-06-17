package theory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

class Test {

	void test() {
		System.out.println("test");
	}
}

public class IteratorMain {

	public static void main(String[] args) {

		ArrayList arr = new ArrayList();

		arr.add("홍박이");
		arr.add("성시주");
		arr.add("혁현혁");
		arr.add("김김오");
		arr.add("연현민");
		arr.add("섭준석");

		System.out.println("iterator의 필요성");
		Test test = new Test();

		ArrayList testArr = new ArrayList();

		for (int i = 0; i < 5; i++) {
			testArr.add(test);
		}

//		for (Object object :  testArr) {
//			object.test(); // Error 
//			(Test)object.test();  // Error
//		}

//		for (Test object :  (Test) testArr) { // Error
//			object.test(); // Error 
//			(Test)object.test();  // Error
//		}

//		for (Object object : arr) { //Error
//			if (object.equals("성시주")) {
//				arr.remove(object); // for문 내부에서 remove() 실행시 반복되는 index의 길이가 달라지게 되므로 에러 Error
//			}
//			System.out.println(object);
//		}

//		❤❤ 위와 같은 경우 때문에 iterator 사용
		System.out.println("❤❤ 위와 같은 경우 때문에 iterator 사용");
		System.out.println("----------------------------------------------------------");

		Iterator it = arr.iterator();

		System.out.println(it.toString());

		System.out.println(arr);
		// [홍박이, 성시주, 혁현혁, 김김오, 연현민, 섭준석]

		while (it.hasNext()) {

			Object obj = it.next();
			if (obj.equals("성시주")) {
//				arr.remove(obj); //Error // iterator을 이용하여 삭제
				it.remove();
			}
		}

		System.out.println(arr);
		// [홍박이, 혁현혁, 김김오, 연현민, 섭준석]

		System.out.println("----------------------------------------------------------");
		System.out.println("iterator 은 일회성 ..... ListIterator 사용 권장");
		Iterator it2 = arr.iterator();

		while (it2.hasNext()) {
			System.out.println(it2.next());
		}

		System.out.println("----------------------------------------------------------");

		for (Object object : arr) {
			System.out.println(object);
		}

		System.out.println("ListIterator (입력순서별 출력)-----------------------------------------------------------");

		ArrayList number = new ArrayList();

		for (int i = 1; i <= 5; i++) {
			number.add(i);
		}

		ListIterator lit = number.listIterator();

		while (lit.hasNext()) { // 입력 순서대로 출력
			System.out.println(lit.next());
		}
		System.out.println(number);

		System.out.println("hasPrevious (역순출력)----------------------------------------------------------");

		while (lit.hasPrevious()) {
			System.out.println(lit.previous());
		}
		System.out.println(number);

		System.out.println("----------------------------------------------------------");

		System.out.println(number);
		System.out
				.println("hasPrevious (역순출력) 후 => hasPrevious (역순출력)------------------------------------------------");
		while (lit.hasPrevious()) {
			System.out.println(lit.previous());
		}
		System.out.println(number);

		System.out.println("hasPrevious (역순출력) 후 => hasNext(입력순서별 출력)------------------------------------------------");
		while (lit.hasNext()) {
			System.out.println(lit.next());
		}
		System.out.println(number);

		System.out.println("전진을 해야 후진 할 것이 생깁니다.");
		System.out.println("역순 출력후 다시 역순으로 출력 할 수 없습니다.");
	}

}
