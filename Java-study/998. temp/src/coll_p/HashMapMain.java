package coll_p;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class HMAAA {

}

public class HashMapMain {

	public static void main(String[] args) {

		// map 은 index 의 번호가 아니기때문에 반복문 사용 불가

		Map mm = new HashMap();

		// Set 의 add 대신 put 이랑 메소드로 넣어줍니다.
		mm.put("red", "빨강");
		mm.put("blue", "파랑");
		mm.put("yellow", "노랑");
		mm.put("blue", "퍼랭이");
		mm.put("red", "빨간맛");
		mm.put("orange", "오우렌지");
		mm.put("green", "녹색");
		mm.put("rrr", "빨강");
		mm.put(123, "오우렌지");
		mm.put(true, "부울리언");
		mm.put(new HMAAA(), "123.456");

//		while(mm.isEmpty()) {
//			// 여기서 하나씩을 못 빼니까 반복 사용 불가
//		}

		System.out.println(mm);
//		{red=빨간맛, orange=오우렌지, green=녹색, rrr=빨강, blue=퍼랭이, yellow=노랑, coll_p.HMAAA@15db9742=123.456, 123=오우렌지, true=부울리언}
// 		위치는 지 멋대로 들어갑니다.
// 		key 값이 겹쳐지면 마지막 key 값과 value 가 들어 갑니다.
//		value 는 겹쳐도 상관없습니다.

//		const a = {
//				a: 1,
//				b:2
//				};

//				console.log(a.a);
//				1

		System.out.println(mm.get(0)); // null, key 값이 0인 것이 없습니다.
		System.out.println("red 의 value : " + mm.get("red"));
		// key : 빨간맛
		mm.remove("rrr");
		System.out.println(mm);
		// {red=빨간맛, orange=오우렌지, green=녹색, blue=퍼랭이, yellow=노랑,
		// coll_p.HMAAA@15db9742=123.456, 123=오우렌지, true=부울리언}
		System.out.println(mm.size()); // 8

		// 존재 key 값의 존재 유무 - boolean 반환
		System.out.println(mm.containsKey("red")); // true
		System.out.println(mm.containsKey("pink")); // false

		// value 의 존재 유무 - boolean 반환
		System.out.println(mm.containsValue("오우렌지")); // true
		System.out.println(mm.containsValue("사과")); // false

//		for (Object oo : mm) {
//			
//		} for 문과 iterrator 사용 불가.....

		// Key 값만 뽑기
//		TreeSet kSet = (TreeSet) mm.keySet(); // key 값만 가져옴
		// 사용 가능
		Set kSet = mm.keySet(); // key 값만 가져옴
		System.out.println("kSet(keySet) : " + kSet);
		// kSet(keySet) : [red, orange, green, blue, yellow, coll_p.HMAAA@15db9742, 123,
		// true]

		Set enSet = mm.entrySet();
		System.out.println("enSet(entrySet) : " + enSet);
		// enSet(entrySet) : [red=빨간맛, orange=오우렌지, green=녹색, blue=퍼랭이, yellow=노랑,
		// coll_p.HMAAA@15db9742=123.456, 123=오우렌지, true=부울리언]

		// value 만 뽑을땐 Collection 에 담을 수 있습니다.
		Collection value = mm.values();
		System.out.println("Collection(mm.values) : " + value);
		// Collection(mm.values) : [빨간맛, 오우렌지, 녹색, 퍼랭이, 노랑, 123.456, 오우렌지, 부울리언]

		System.out.println();

		System.out.println("kSet : ----------------------------");
		for (Object object : kSet) {
			System.out.println(object + " : " + mm.get(object));
		}

		System.out.println();

		System.out.println("value : ----------------------------");
		for (Object object : value) {
			System.out.println(object);
		}

		System.out.println();

		System.out.println("enSet : ----------------------------");
		for (Object object : enSet) {
			Map.Entry en = (Map.Entry) object;
//			System.out.println(object);
//			System.out.println(en);
			System.out.println(en.getKey() + " : " + en.getValue());
		}

		System.out.println();

//		Iterator klit = kSet.iterator();
//		Iterator vlit = value.iterator();
//
//		while (klit.hasNext()) {
//
//			Object Key = klit.next();
//			Object Value = vlit.next();
//
//			System.out.println("key : " + Key + ", value : " + Value);
//
//		}

	}

}
