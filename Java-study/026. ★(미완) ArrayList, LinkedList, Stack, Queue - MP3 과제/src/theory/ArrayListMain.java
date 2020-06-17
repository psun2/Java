package theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// coll_p

class AAA {

	@Override
	public String toString() {
		return "AAA [ArrayList 는 객체도 담을 수 있다.]";
	}

}

public class ArrayListMain {

	public static void main(String[] args) {

		System.out.println("ArrayList == class 입니다. 배열과 비슷한 기능과 구조를 가지고 있지만 배열은 아닙니다.");
		System.out.println("ArrayList는 CRUD 기능(데이터 처리 기능) 을 제공합니다.");
		System.out.println("C : create(생성)");
		System.out.println("R : read(읽기)");
		System.out.println("U : update(갱신)");
		System.out.println("D : delete(삭제)");

		System.out.println();

		ArrayList arr1 = new ArrayList();

		System.out.println(arr1); // []
		// ✔ size() 메소드는 배열이나 문자열의 길이를 알아보는 length 와 같은 기능을 합니다.
		System.out.println(arr1.size()); // 0

		arr1.add(11);
		arr1.add(34);
		arr1.add(5);
		arr1.add(11);
		arr1.add(78);

		System.out.println(arr1); // [11, 34, 5, 11, 78]
		System.out.println(arr1.size()); // 5

		// get(index) 메소는 배열에서 index번호에 따른 값을 뽑아낼떄
		// 사용하는 arr[index] 형식과 유시합니다.
		System.out.println(arr1.get(0)); // 11 0번 idnex의 데이터를 얻어냅니다.

		System.out.println();
		System.out.println("ArrayList 의 반복문");
		for (int i = 0; i < arr1.size(); i++) {
			System.out.println("arr1.get(" + i + ") : " + arr1.get(i));
		}

//		arr1.get(0) : 11
//		arr1.get(1) : 34
//		arr1.get(2) : 5
//		arr1.get(3) : 11
//		arr1.get(4) : 78

		System.out.println();
		System.out.println("ArrayList 의 값 추가 add() -- list의 마지막 index를 늘려 추가 합니다.");
		arr1.add(1000);
		System.out.println(arr1); // [11, 34, 5, 11, 78, 1000]

		System.out.println();
		System.out.println("ArrayList 의 원하는 index에 값 추가 add() -- 원하는 index에 추가하면서 한칸씩 밀어버립니다.");
		arr1.add(3, 500);
		System.out.println(arr1); // [11, 34, 5, 500, 11, 78, 1000]

		System.out.println();
		System.out.println("ArrayList 의 수정 set() -- 원하는 index에 데이터를 수정합니다.");
		arr1.set(3, 50);
		System.out.println(arr1); // [11, 34, 5, 50, 11, 78, 1000]

		System.out.println();
		System.out.println("ArrayList 의 삭제 remove() -- index번호 혹은 List 에 있는 데이터를 직접적으로 인자로 사용합니다.");
		System.out.println("arr1.size() : " + arr1.size()); // 7
		arr1.remove(3);
		System.out.println(arr1); // [11, 34, 5, 11, 78, 1000]
		System.out.println("arr1.size() : " + arr1.size()); // 6

		arr1.remove((Object) 11);
		System.out.println(arr1); // [34, 5, 11, 78, 1000]
		System.out.println("arr1.size() : " + arr1.size()); // 5

		System.out.println();
		System.out.println("ArrayList 의 indexOf() -- 해당 데이터의 index번호를 알 수 있으며, String.indexOf 와는 조금 차이점이 있습니다.");
		System.out.println("arr1.indexOf(5) : " + arr1.indexOf(5)); // 데이터 값 5 는 1번 index에 있음을 알 수 있습니다.

		System.out.println();
		arr1.add(34);
		arr1.add(3, 34);
		System.out.println("ArrayList 의 lastIindexOf() -- ArrayList 의 해당데이터가 마지막에 등장하는 index 번호를 반환합니다.");
		System.out.println(arr1);
		System.out.println("arr1.lastIndexOf() : " + arr1.lastIndexOf(34)); // 6
		// 뒤에서 첫번째로 등장하는 34의 index번호를 반환 받습니다.

		System.out.println();
		System.out.println("ArrayList 의 contains() -- 해당 데이터를 포함하는지 booleane 값을 반환 합니다..");
		System.out.println("arr1.contains(5) : " + arr1.contains(5)); // true
		System.out.println("arr1.contains(74) : " + arr1.contains(74)); // false

		System.out.println();
		System.out.println("ArrayList 의 주소 개념");
		ArrayList arr2 = arr1;
		ArrayList arr3 = new ArrayList(arr1);
		System.out.println("arr1.hashCode() : " + arr1.hashCode()); // 2007587789
		System.out.println("arr2.hashCode() : " + arr2.hashCode()); // 2007587789
		System.out.println("arr3.hashCode() : " + arr3.hashCode()); // 2007587789
		System.out.println("arr1.equals(arr2) : " + arr1.equals(arr2)); // true
		System.out.println("arr1.equals(arr3) : " + arr1.equals(arr3)); // true
		System.out.println("arr1 == arr2 : " + (arr1 == arr2)); // true
		System.out.println("arr1 == arr3 : " + (arr1 == arr3)); // false

		System.out.println("hashCode() 와 equals() 는 ArrayList 라는 동일 객체로 보고 있는 것");
		System.out.println("반면 == 연산자는 객체 않에 데이타를 비교 상황에따라 비교하는 것 이 중요");

		System.out.println();
		System.out.println("List");
		System.out.println("shallow copy");
		List arr4 = arr1.subList(1, 3);
		System.out.println("arr4 : " + arr4); // arr4 : [5, 11]
		System.out.println("arr1 : " + arr1); // arr1 : [34, 5, 11, 34, 78, 1000, 34]
		arr1.set(1, 100000);
		System.out.println("arr4 : " + arr4); // arr4 : [5, 11]
		System.out.println("arr1 : " + arr1); // arr1 : [34, 5, 11, 34, 78, 1000, 34]
		//////////////////////////////////////////////////////////////////////////////////
		arr4.add(123);
		System.out.println(arr1);
//		arr1.add(456);
//		System.out.println(arr4); // Error
		// 1. arr4 와 arr1의 길이는 다릅니다.
		// 2. arr4의 마지막 index에 add 됨
		// 3. arr1 은 arr4의 size() 를 포함 할 수 있습니다.
		// 4. arr1에 마지막 index에 add가 되면
		// 5. arr4는 arr1의 size()보다 작으므로, 해당 index를 arr4의 index에 붙일 수 없습니다.

		System.out.println();
		System.out.println("deep copy");
		List arr5 = new ArrayList(arr1.subList(1, 3));
		System.out.println("arr5 : " + arr5); // arr5 : [100000, 11]
		System.out.println("arr1 : " + arr1); // arr1 : [34, 100000, 11, 34, 78, 1000, 34]
		arr1.set(1, 3000000);
		System.out.println("arr5 : " + arr5); // arr5 : [100000, 11]
		System.out.println("arr1 : " + arr1); // arr1 : [34, 3000000, 11, 34, 78, 1000, 34]
		System.out.println("arr1 과 arr5는 deep copy 이므로 서로에게 영향을 주지 않습니다.");
		System.out.println();
		System.out.println("ArrayList 의 deep copy2");
		ArrayList arrDeepCopy = (ArrayList) arr1.clone();
		System.out.println("arr1 : " + arr1);
		// arr1 : [34, 3000000, 11, 123, 34, 78, 1000, 34]
		System.out.println("arrDeepCopy : " + arrDeepCopy);
		// arrDeepCopy : [34, 3000000, 11, 123, 34, 78, 1000, 34]

		System.out.println();
		System.out.println("me.containsAll(you) => me 에 you의 전부가 포함이 되나요? => boolean 값으로 반환");
		System.out.println("arr4 : " + arr4); // arr4 : [3000000, 11]
		System.out.println("arr1 : " + arr1); // arr1 : arr1 : [34, 3000000, 11, 34, 78, 1000, 34]
		System.out.println(arr1.containsAll(arr4)); // true
		System.out.println("arr5 : " + arr5); // arr5 : [100000, 11]
		System.out.println("arr1 : " + arr1); // arr1 : [34, 3000000, 11, 34, 78, 1000, 34]
		System.out.println(arr1.containsAll(arr5)); // false

		System.out.println();
		System.out.println("removeAll");
		ArrayList arr11 = new ArrayList(arr1);
		ArrayList arr55 = new ArrayList(arr5);

		System.out.println("arr1 : " + arr1);
		// arr1 : [34, 3000000, 11, 123, 34, 78, 1000, 34]
		System.out.println("arr11 : " + arr11);
		// arr11 : [34, 3000000, 11, 123, 34, 78, 1000, 34]
		System.out.println("arr5 : " + arr5);
		// arr5 : [100000, 11]
		System.out.println("arr55 : " + arr55);
		// arr55 : [100000, 11]

		System.out.println("arr11.removeAll(arr55) : arr11 에서 arr55의 요소들을 지웁니다.");
		arr11.removeAll(arr55);
		System.out.println("arr11 : " + arr11);
		// arr11 : [34, 3000000, 123, 34, 78, 1000, 34]
		System.out.println("arr55 : " + arr55);
		// arr55 : [100000, 11]

		System.out.println();
		System.out.println("ArrayLst 에 객체(class instance) 추가");
		AAA a1 = new AAA();
		arr1.add(a1);
		System.out.println("arr1 : " + arr1);
		// arr1 : [34, 3000000, 11, 123, 34, 78, 1000, 34, AAA [ArrayList 는 객체도 담을 수
		// 있다.]]

		Integer i = 815;
		arr1.add(i);
		System.out.println("arr1 : " + arr1);
		// arr1 : [34, 3000000, 11, 123, 34, 78, 1000, 34, AAA [ArrayList 는 객체도 담을 수
		// 있다.], 815]
		System.out.println("ArrayList 에는 모든 객체가 다 들어갈수 있습니다.");

		System.out.println();
		System.out.println("ArrayList 를 배열화 하기");
		Object[] oo = arr1.toArray();
		// 배열화 합니다.
		System.out.println(Arrays.toString(oo));
		// [34, 3000000, 11, 123, 34, 78, 1000, 34, AAA [ArrayList 는 객체도 담을 수 있다.], 815]

		System.out.println();
		System.out.println("ArrayList 의 deep copy2");
		ArrayList arr6 = (ArrayList) arr1.clone();
		System.out.println("arr6 : " + arr6);
		// arr6 : [34, 3000000, 11, 123, 34, 78, 1000, 34, AAA [ArrayList 는 객체도 담을 수
		// 있다.], 815]

		System.out.println();
		System.out.println("ArrayList 의 clear() ---- list 의 모든 내용을 지웁니다.");
		arr6.clear(); // arr1 의 ArrayList의 내용을 모두 지웁니다.
		System.out.println("arr1 : " + arr1);
		// arr1 : [34, 3000000, 11, 123, 34, 78, 1000, 34, AAA [ArrayList 는 객체도 담을 수
		// 있다.], 815]
		System.out.println("arr6 : " + arr6);
		// arr6 : []
	}

}
