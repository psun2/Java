package theory;

import java.util.Map.Entry;
import java.util.TreeMap;

public class TreeMapYakSu {

	public static void main(String[] args) {

		// 다음 숫자들의 각 약수들의 사용갯수를 구하세요.
		// 0과 1만 빼고 자기 자신 포함
		int[] nums = { 22, 4, 56, 7, 8, 90, 12, 3, 45, 6, 22, 31, 45, 63, 86, 57 };

		TreeMap yaksu = new TreeMap();

		for (int i : nums) {

			for (int j = 2; j <= i; j++) {

				if (i % j == 0) {

					int cnt = 1;

					if (yaksu.containsKey(j))
						cnt += (int) yaksu.get(j);

					yaksu.put(j, cnt);
					
//					TreeMap 은 
//					key 과 value 로 put 해주며,
//					겹치는 key 갑이 들어오면,
//					해당 key의 값을 들어오는 key 의 value 로
//					덮어 씌웁니다.
				}
			}
		}

		for (Object obj : yaksu.entrySet()) {

			Entry en = (Entry) obj;

			System.out.println(en.getKey() + " : " + en.getValue());

		}

	}

}
