package coll_p;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

class ArrayCom implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
//		return 0; // 본인만 출력
//		return 1; // 1을 주는 순간 ArrayList 랑 똑같아 집니다.
		return -1; // ArrayList 랑 똑같아 집니다. 역순 출력
	}

}

class DescCom implements Comparator {

	@Override
	public int compare(Object o1, Object o2) { // sort(index => a - b) 와 비슷한 구조 같음.
		System.out.println("o1 : " + o1);
		System.out.println("o2 : " + o2);
		System.out.println(" o1 : o2  => " + o1 + " : " + o2);

		// TODO Auto-generated method stub
		int me = (int) o1;

		int you = (int) o2;

		return you - me;
	}

}

public class LinkedHashSetMain { // HashSet 보단 LinkedHashSet 사용

	public static void main(String[] args) {

		Object[] arr = { 11, 44, 33, 77, 88, 22, 44, 11, 99, 123, 456, 123, 66, 33 };

		HashSet set1 = new HashSet();

		LinkedHashSet set2 = new LinkedHashSet();

		TreeSet set3 = new TreeSet();
		TreeSet set4 = new TreeSet(new ArrayCom());
		TreeSet set5 = new TreeSet(new DescCom());

		for (Object object : arr) {
			set1.add(object);
			set2.add(object);
			set3.add(object);
			set4.add(object);
			set5.add(object);

			System.out.println();
		}

		System.out.println(set1);
		// [33, 66, 99, 22, 88, 456, 11, 123, 44, 77]
		System.out.println(set2);
//		HashSet 과 LinkedHashSet 은 똑같이 중복을 제거하지만,

//		들어 오는 순서를 보았을때 LinkedHashSet 은 순서를 기억하여 앞부터 차례대로 들어 갑니다.
		// [11, 44, 33, 77, 88, 22, 99, 123, 456, 66]

		System.out.println(set3);
		// TreeSet 정렬이 되어 들어갑니다.
		// [11, 22, 33, 44, 66, 77, 88, 99, 123, 456]

		System.out.println(set4);
		System.out.println(set5);
	}

}

//set
//순서가 없다.
//중복되지 않는다.
//똑같은데이터를 입력하면 둘중하나만 ... 기억한다
//
//순서와 관련된 모든 메소드 사용불가
//
//subList
//get
//indexOF 등....
//add(index, value);
//remove(index)
