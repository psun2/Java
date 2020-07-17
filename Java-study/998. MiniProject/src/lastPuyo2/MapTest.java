package lastPuyo2;

import java.util.HashMap;
import java.util.Map.Entry;

public class MapTest {

	public MapTest() {
		// TODO Auto-generated constructor stub

		HashMap<String, String> asd = new HashMap<String, String>();

		asd.put("key1", "value1");
		asd.put("key2", "value2");
		asd.put("key3", "value3");
		asd.put("key4", "value4");
		asd.put("key5", "value5");
		asd.put("key6", "value6");

		for (Entry<String, String> iterable_element : asd.entrySet()) {
//			System.out.println(iterable_element instanceof String);

			System.out.println(iterable_element.getKey() instanceof String);

			for (String asd2 : asd.get(iterable_element.getKey())) {
				System.out.println(asd2);
			}
		}

	}

}
