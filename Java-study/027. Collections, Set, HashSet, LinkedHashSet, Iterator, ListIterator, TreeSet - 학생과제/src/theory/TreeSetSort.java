package theory;

import java.util.Comparator;
import java.util.TreeSet;

class ArrayCom implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		// o1 과 o2 가 값이 같을경우 0 반환 return 0 이므로 본인만 출력
		// 양수 = 정렬되어 출력
		// 음수 = 역순 정렬
		return -1;
	}

}

class DescCom implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {

//		System.out.println("arg0" + arg0);
//		System.out.println("arg1 : " + arg1);
//		System.out.println(" arg0 : arg1  => " + arg0 + " : " + arg1);

		int me = (int) arg0;

		int you = (int) arg1;
		
//		you - me => 음수 : me 의 앞쪽으로 이동
//				 => 양수  : me 의 뒤쪽으로 이동
//				 =>  0  : 제자리

		return you - me;
	}

}

public class TreeSetSort {

	public static void main(String[] args) {

		Object[] arr = { 11, 44, 33, 77, 88, 22, 44, 11, 99, 123, 456, 123, 66, 33 };

		System.out.println("TreeSet 정렬 Exam");
		System.out.println("Comparator 는 Interface 입니다.");
		TreeSet set1 = new TreeSet();
		TreeSet set2 = new TreeSet(new ArrayCom());
		TreeSet set3 = new TreeSet(new DescCom());

		for (Object object : arr) {
			set1.add(object);
			set2.add(object);
			set3.add(object);

		}

		System.out.println("set 1 : " + set1);
		System.out.println("set 2 : " + set2);
		System.out.println("set 3 : " + set3);
	}

}
