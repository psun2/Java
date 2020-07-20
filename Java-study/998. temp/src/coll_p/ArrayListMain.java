package coll_p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AAA {

	@Override
	public String toString() {
		return "AAA []";
	}

}

public class ArrayListMain {

	public static void main(String[] args) {

		System.out.println("ArrayList 는 Calss 이지 배열이 아닙니다.");
		System.out.println("CRUD 기능 제공");

		ArrayList arr1 = new ArrayList();

		System.out.println(arr1); // []
		System.out.println(arr1.size()); // 0

		arr1.add(11);
		arr1.add(34);
		arr1.add(5);
		arr1.add(11);
		arr1.add(78);

		System.out.println(arr1); // [11, 34, 5, 11, 78]
		System.out.println(arr1.size()); // 5
		System.out.println(arr1.get(0)); // 11
//		System.out.println(arr1.get(5)); // 5번 index는 존재하지 않음으로 error

		System.out.println();
		System.out.println("ArrayList 반복문 과 index ---------------------");

		for (int i = 0; i < arr1.size(); i++) {
			System.out.println("arr1.get(" + i + ") : " + arr1.get(i));
		}
//		arr1.get(0) : 11
//		arr1.get(1) : 34
//		arr1.get(2) : 5
//		arr1.get(3) : 11
//		arr1.get(4) : 78

		// 기본적으로 배열의 마지막 index에 추가 됩니다.
		System.out.println();
		System.out.println("추가");
		arr1.add(100);
		System.out.println(arr1); // [11, 34, 5, 11, 78, 100]
		System.out.println(arr1.size()); // 6

		// index 번호에 따라 중간중간에 넣어 줄 수 도 있습니다.
		System.out.println();
		System.out.println("중간삽입");
		arr1.add(2, 200);
		System.out.println(arr1); // [11, 34, 200, 5, 11, 78, 100]
		System.out.println(arr1.size()); // 7

		// 수정
		System.out.println();
		System.out.println("수정");
		arr1.set(3, 555);
		System.out.println(arr1); // [11, 34, 200, 555, 11, 78, 100]
		System.out.println(arr1.size()); // 7

		// 삭제
		System.out.println();
		System.out.println("삭제");
		arr1.remove(5); // 해당 index 번호를 입력하여 해당 index 번호를 삭제 합니다.
		System.out.println(arr1); // [11, 34, 200, 555, 11, 100]
		System.out.println(arr1.size()); // 6

		arr1.remove((Object) 34);
		System.out.println(arr1); // [11, 200, 555, 11, 100]
		System.out.println(arr1.size()); // 5

		arr1.remove((Object) 11);
		System.out.println(arr1); // [200, 555, 11, 100]
		System.out.println(arr1.size()); // 4

		// indexOf
		System.out.println();
		System.out.println("indexOf()");
		System.out.println(arr1.indexOf(555)); // 1
		System.out.println(arr1.indexOf(11)); // 2
		System.out.println("값이 존재 하지 않을때 -1 반환");
		System.out.println(arr1.indexOf(5555)); // -1
//		System.out.println(arr1.indexOf(2, 11)); // Error String 처럼은 사용 할 수 없음
//		System.out.println(arr1.indexOf(11, 2)); // Error String 처럼은 사용 할 수 없음

		// lastIndexOf
		System.out.println();
		System.out.println("lastIndexOf()");
		System.out.println(arr1.lastIndexOf(11)); // 2
		// 해당 배열에서 해당원소가 마지막으로 등장하는 index 번호를 반환

		// contains
		System.out.println();
		System.out.println("contains() boolean 값 반환");
		System.out.println(arr1.contains(11)); // true
		System.out.println(arr1.contains(55)); // false

		System.out.println();
		System.out.println("✔");
		ArrayList arr2 = arr1;
		ArrayList arr3 = new ArrayList(arr1);
		arr1.set(1, 456456);
		System.out.println("arr1 : " + arr1);
		// arr1 : [200, 456456, 11, 100]
		System.out.println("arr2 : " + arr2);
		// arr2 : [200, 456456, 11, 100]
		System.out.println("arr3 : " + arr3);
		// arr3 : [200, 555, 11, 100]

		System.out.println("arr3 은 생성시 arr1 의 값을 가지고 생성을 해서 주소가 다릅니다.");
		System.out.println("반면 arr1 과  arr2 는 주소를 공유 합니다.");

		System.out.println();
		System.out.println("subList => shallow copy 입니다.");
		List arr4 = arr1.subList(1, 3);
		List arr5 = new ArrayList(arr1.subList(1, 3)); // deepcopy
//		ArrayList arr5 = new ArrayList(arr1.subList(1, 3)); // deepcopy

		// containsAll(collection e) collection 안의 모든 항목이 있는지
		System.out.println(arr1.containsAll(arr5)); // true

		System.out.println("arr4 : " + arr4);
		// arr4 : [456456, 11]

		arr4.set(0, 9999);
		System.out.println("arr1 : " + arr1);
		// arr1 : [200, 9999, 11, 100]
		System.out.println("arr4 : " + arr4);
		// arr4 : [9999, 11]

		arr1.set(1, 8080);
		System.out.println("arr1 : " + arr1);
		// arr1 : [200, 8080, 11, 100]
		System.out.println("arr4 : " + arr4);
		// arr4 : [8080, 11]

		arr4.add(1234);
		System.out.println("arr1 : " + arr1);
		// arr1 : [200, 8080, 11, 1234, 100]
		System.out.println("arr4 : " + arr4);
		// arr4 : [8080, 11, 1234]

		arr1.add(789);
		// arr4의 길이가 더 짧고,
		// arr1의 마지막에 추가되므로, arr4에서는 arr1에서 추가된 요소를 찾을수 없어 Error
		// Error 의 원인은 shallow copy
		// 크기 변경시 원본 리스트의 주소가 변경되어 참조하고 있는 subList 객체의 주소가 유효하지 않음
		System.out.println("arr1 : " + arr1);
		// arr1 : [200, 8080, 11, 1234, 100, 789]
//		System.out.println("arr4 : " + arr4); // Error
		System.out.println("arr5 : " + arr5);
		// arr5 : [456456, 11]

		arr1.add(741);
		arr5.add(369);

		System.out.println("arr1 : " + arr1);
		// arr1 : [200, 8080, 11, 1234, 100, 789, 741]
		System.out.println("arr5 : " + arr5);
		// arr5 : [456456, 11, 369]
		System.out.println("arr1 과 arr5는 deep copy 이므로 서로에게 영향을 주지 않습니다.");

		System.out.println(arr1.containsAll(arr5)); // false

		ArrayList arr11 = new ArrayList(arr1);
		ArrayList arr55 = new ArrayList(arr5);
		System.out.println("arr1 : " + arr1);
		System.out.println("arr11 : " + arr11);
		System.out.println("arr5 : " + arr5);
		System.out.println("arr55 : " + arr55);
		arr11.removeAll(arr5); // arr11에서 arr5에 있는 원소들을 지움
		System.out.println("arr11.removeAll(arr5) : arr11에서 arr5에 있는 원소들을 지움");
		arr55.retainAll(arr1); // arr1 과 arr55의 교집합 만 남기고 다 지워
		// 즉 arr55가 가지고 있는 arr1의 원소들을 제외한 나머지가 지워 집니다.
		System.out.println("arr55.retainAll(arr1) : arr1 과 arr55의 교집합 만 남기고 다 지워");
		System.out.println("arr1 : " + arr1);
		System.out.println("arr11 : " + arr11);
		System.out.println("arr5 : " + arr5);
		System.out.println("arr55 : " + arr55);

		arr1.addAll(arr5);
		System.out.println("arr1 : " + arr1);
		// arr1에 arr5의 원소들을 추가합니다.
//		arr1.retainAll(arr5);
//		System.out.println("arr1 : " + arr1);
		arr1.removeAll(arr5);
		System.out.println("arr1 : " + arr1);

		AAA a1 = new AAA();
		arr1.add(a1);
		System.out.println("arr1 : " + arr1);

		arr1.add(a1.toString());
		System.out.println("arr1 : " + arr1);

		arr1.add(a1);
		System.out.println("arr1 : " + arr1);

		String str = "851";
		arr1.add(str);
		System.out.println("arr1 : " + arr1);

		Integer i = 815;
		arr1.add(i);
		System.out.println("arr1 : " + arr1);

		Object[] oo = arr1.toArray();
		// 배열화 합니다.
		System.out.println(Arrays.toString(oo));

		ArrayList arr6 = (ArrayList) arr1.clone();
		System.out.println("arr6 : " + arr6);

		arr1.clear(); // arr1 의 ArrayList의 내용을 모두 지웁니다.
		System.out.println("arr1 : " + arr1);

		System.out.println("arr6 : " + arr6);
		
		System.out.println("ArrayList 에는 모든 객체가 다 들어갈수 있습니다.");
	}

}
