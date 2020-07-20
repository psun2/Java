package coll_p;

import java.util.ArrayList;

class TV {
}

class Phone {
}

class HandPhone extends Phone {
}

public class GenericArrayListMain {

	public static void main(String[] args) {

		ArrayList arr1 = new ArrayList();
		ArrayList<TV> arr2 = new ArrayList();
		ArrayList<Phone> arr3 = new ArrayList();
		ArrayList<HandPhone> arr4 = new ArrayList();

		arr1.add("김예솔");
		arr1.add(new TV());
		arr1.add(new Phone());
		arr1.add(new HandPhone());

//		arr2.add("김예솔");
		arr2.add(new TV());
//		arr2.add(new Phone());
//		arr2.add(new HandPhone());

//		arr3.add("김예솔");
//		arr3.add(new TV());
		arr3.add(new Phone());
		arr3.add(new HandPhone());

//		arr4.add("김예솔");
//		arr4.add(new TV());
//		arr4.add(new Phone());
		arr4.add(new HandPhone());

		TV tv;
		tv = (TV) arr1.get(1); // casting 해주어야함
		tv = arr2.get(0); // Generic Type이 TV 이기 때문에 형변환 필요 X;

		Phone ph = arr3.get(0);
		ph = arr3.get(1);
		ph = arr4.get(0);

		HandPhone hp;
//		hp = arr3.get(0);
		hp = (HandPhone) arr3.get(1); // arr3 의 Generic이 Phone 이기 때문에 뱉을때 Phone 로 뱉기 때문에 형변환 필요
		hp = arr4.get(0);
	}

}
