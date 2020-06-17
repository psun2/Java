package exam;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {

		String test = "AMERICANO_2,ASIANO_3,AFRICANO_1";

		String[] name = test.split("[,]");
		String[] Element = new String[0];
		int[] cnt = new int[0];

		StringTokenizer stn = new StringTokenizer(test, "[_,]");

		while (stn.hasMoreTokens()) {

			String str = stn.nextToken();

			try {

				int i = Integer.parseInt(str); // 숫자로 바뀌면 Error가 아님

				int[] temp = new int[cnt.length + 1];

				for (int j = 0; j < cnt.length; j++) {
					temp[j] = cnt[j];
				}

				temp[cnt.length] = i;

				cnt = temp;

			} catch (Exception e) {

				// 문자열이면 Error 이므로, catch에서 처리

				String[] temp = new String[Element.length + 1];

				for (int i = 0; i < Element.length; i++) {
					temp[i] = Element[i];
				}

				temp[Element.length] = str;

				Element = temp;
			}

		}

		System.out.println(Arrays.toString(name));
		System.out.println(Arrays.toString(Element));
		System.out.println(Arrays.toString(cnt));

	}

}
