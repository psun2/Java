package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class MapSort {

	public static void main(String[] args) {

		HashMap<String, String> hashMap = new HashMap<String, String>();

		hashMap.put("test3", "name5");
		hashMap.put("test4", "name2");
		hashMap.put("test5", "name1");
		hashMap.put("test1", "name3");
		hashMap.put("test2", "name4");
		
		System.out.println(hashMap);

		System.out.println("==========오름차순============");
		// 오름차순
		TreeMap<String, String> treeMap = new TreeMap<String, String>(hashMap);
		
		Iterator<String> treeMapIter = treeMap.keySet().iterator();

		while (treeMapIter.hasNext()) {

			String key = treeMapIter.next();
			String value = treeMap.get(key);

			System.out.println(key + " : " + value);

		}

		System.out.println("==========내림차순============");
		// 내림차순
		TreeMap<String, String> treeMapReverse = new TreeMap<String, String>(Collections.reverseOrder());
		System.out.println(treeMapReverse);
		treeMapReverse.putAll(hashMap);

		Iterator<String> treeMapReverseIter = treeMapReverse.keySet().iterator();

		while (treeMapReverseIter.hasNext()) {

			String key = treeMapReverseIter.next();
			String value = treeMapReverse.get(key);

			System.out.println(key + " : " + value);

		}

	}

}
