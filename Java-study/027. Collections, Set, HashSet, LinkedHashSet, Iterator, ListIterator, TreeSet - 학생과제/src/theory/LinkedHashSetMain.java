package theory;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class LinkedHashSetMain {

	public static void main(String[] args) {

		// set
		// 순서가 없다.
		// 중복되지 않는다.
		// 똑같은데이터를 입력하면 둘중하나만 ... 기억한다
		//
		// 순서와 관련된 모든 메소드 사용불가
		//
		// subList
		// get
		// indexOF 등....
		// add(index, value);
		// remove(index)

		Object[] arr = { 11, 44, 33, 77, 88, 22, 44, 11, 99, 123, 456, 123, 66, 33 };

		HashSet set1 = new HashSet();

		LinkedHashSet set2 = new LinkedHashSet();

		TreeSet set3 = new TreeSet();

		for (Object object : arr) {
			set1.add(object);
			set2.add(object);
			set3.add(object);
		}

		System.out.println("HashSet set1 : " + set1);
		System.out.println("중복 허용 X, 자리 순서가 무작위로 add");
		System.out.println("--------------------------------------");
		System.out.println("LinkedHashSet set2 :" + set2);
		System.out.println("중복 허용 X, 자리를 기억하며 자리 순서대로 add");
		System.out.println("--------------------------------------");
		System.out.println("TreeSet set3 : " + set3);
		System.out.println("중복 허용 X, 정렬되어 add");
		System.out.println("--------------------------------------");

		}

	}

