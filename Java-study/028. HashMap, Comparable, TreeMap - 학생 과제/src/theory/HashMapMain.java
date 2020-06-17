package theory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class HMAAA {

}

public class HashMapMain {

	public static void main(String[] args) {

		// map 의 구조 js 의 Object 와 같습니다.
//		const a = {
//				id : "아이디",
//				pw : "패스워드"
//		}

		// Map
//		┌───────────────────────┐
//		│value│value│value│value│
//		├───────────────────────┤
//		│key  │key  │key  │key  │
//		└───────────────────────┘

		// 배열
//		┌──────────────────────────┐
//		│value   │value   │value   │
//		├──────────────────────────┤
//		│index(0)│index(0)│index(0)│
//		└──────────────────────────┘

		// Map 과 Set 은 밀접환 관계가 있습니다.
		// Map 이 key 로 인해 반복이 되지 않아,
		// key 값을 지우고 나타난게 Set
		// Set 과 의 차이점은 key 가 있냐 없냐 의 차이입니다.

		// map 은 index 의 번호가 아니기때문에 반복문 사용 불가

		Map map = new HashMap();

		// Set 의 add 대신 put 이랑 메소드로 넣어줍니다.
		map.put("red", "빨강");
		map.put("blue", "파랑");
		map.put("yellow", "노랑");
		map.put("blue", "퍼랭이");
		map.put("red", "빨간맛");
		map.put("orange", "오우렌지");
		map.put("green", "녹색");
		map.put("rrr", "빨강");
		map.put(123, "오우렌지"); // Integer 삽입 가능
		map.put(true, "부울리언"); // bollean 삽입 가능
		map.put(new HMAAA(), "123.456"); // 클래스 삽입 가능

//		while(mm.isEmpty()) {
//		// 여기서 하나씩을 못 빼니까 반복 사용 불가
//	}

		System.out.println(map);
		// {red=빨간맛, orange=오우렌지, green=녹색, rrr=빨강, blue=퍼랭이, yellow=노랑,
		// theory.HMAAA@15db9742=123.456, 123=오우렌지, true=부울리언}

		// Set의 특성상 중복되지않고, 마구잡이 식으로 들어가기 때문에....
		// 순서에 상관없이 마구 잡이로 들어 갑니다.

		System.out.println(map.get(0)); // null, key 값이 0인 것이 없습니다.
		// Map 은 index 번호가 아니기 때문에 0 이 가르키는건 key 값이 됩니다.

		System.out.println("red 의 value : " + map.get("red"));
		// red 의 value : 빨간맛
		// put 을 해주면서 동일 key 값이 존재 하면 마지막으로 put 한 값으로 업데이트 합니다.

		map.remove("rrr");
		System.out.println(map); // 삭제 가능
		// {red=빨간맛, orange=오우렌지, green=녹색, blue=퍼랭이, yellow=노랑,
		// theory.HMAAA@15db9742=123.456, 123=오우렌지, true=부울리언}
		System.out.println(map.size()); // 8

		// 존재 key 값의 존재 유무 - boolean 반환
		System.out.println(map.containsKey("red")); // true
		System.out.println(map.containsKey("pink")); // false

		// value 의 존재 유무 - boolean 반환
		System.out.println(map.containsValue("오우렌지")); // true
		System.out.println(map.containsValue("사과")); // false

//		for (Object oo : map) {
//
//		} // for 문과 iterrator 사용 불가.....

		System.out.println();
		System.out.println("key 값");
		Set keySet = map.keySet();
		System.out.println(keySet);
		// [red, orange, green, blue, yellow, theory.HMAAA@15db9742, 123, true]
		System.out.println();
		System.out.println("keySet 의 반복문");
		for (Object object : keySet) {
			System.out.println(object);
		}
//		keySet 의 반복문
//		red
//		orange
//		green
//		blue
//		yellow
//		theory.HMAAA@15db9742
//		123
//		true

		System.out.println();
		System.out.println("value 값");
		Collection valueSet = map.values(); // Collection 으로 사용가능
		System.out.println(valueSet);
		// [빨간맛, 오우렌지, 녹색, 퍼랭이, 노랑, 123.456, 오우렌지, 부울리언]
		System.out.println();
		System.out.println("valueSet 의 반복문");
		for (Object object : valueSet) {
			System.out.println(object);
		}
//		valueSet 의 반복문
//		빨간맛
//		오우렌지
//		녹색
//		퍼랭이
//		노랑
//		123.456
//		오우렌지
//		부울리언

		System.out.println();
		System.out.println("key 값 과 value 값 : Entry");
		Set entrySet = map.entrySet(); // 형변환으로 사용가능
		System.out.println(entrySet);
		// [red=빨간맛, orange=오우렌지, green=녹색, blue=퍼랭이, yellow=노랑,
		// theory.HMAAA@15db9742=123.456, 123=오우렌지, true=부울리언]
		System.out.println();
		System.out.println("entrySet 의 반복문");
		for (Object object : entrySet) {

			Entry entry = (Entry) object;

			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
//		entrySet 의 반복문
//		red : 빨간맛
//		orange : 오우렌지
//		green : 녹색
//		blue : 퍼랭이
//		yellow : 노랑
//		theory.HMAAA@15db9742 : 123.456
//		123 : 오우렌지
//		true : 부울리언

		System.out.println();

		for (Object object : map.entrySet()) {

			Entry entry = (Entry) object;

			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
//		red : 빨간맛
//		orange : 오우렌지
//		green : 녹색
//		blue : 퍼랭이
//		yellow : 노랑
//		theory.HMAAA@15db9742 : 123.456
//		123 : 오우렌지
//		true : 부울리언

		TreeMap tMap = new TreeMap();

		System.out.println(tMap.size());

	}

}
