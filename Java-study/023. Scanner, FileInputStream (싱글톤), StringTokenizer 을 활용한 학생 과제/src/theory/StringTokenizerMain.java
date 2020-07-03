package theory;

import java.util.Arrays;
import java.util.StringTokenizer;

public class StringTokenizerMain {

	public static void main(String[] args) {

		/*
		 * String Tokenizer
		 * 
		 * 보통 문자열을 쪼개서 사용을 할때 많이 사용하는 것이 String Tokenizer
		 * 
		 * 특정 구분자로 문자를 자를때 Null 값을 출력하지 못함
		 * 
		 * 그래서 SDK 1.4 부터 나온 Split() 사용
		 */

		String ttt = "박시현,이주혁,,김연섭$김현준.오민석,장정호";

		String[] arr = ttt.split("[,.$]");

		System.out.println(Arrays.toString(arr));

		for (int i = 0; i < arr.length; i++) {
			System.out.println(i + " : " + arr[i]);
		}

//		0 : 박시현
//		1 : 이주혁
//		2 : 
//		3 : 김연섭
//		4 : 김현준
//		5 : 오민석
//		6 : 장정호

		// 2번 index의 공백 존재

		System.out.println("------------------------------");

		ttt = "박시현,이주혁,,김연섭$김현준.오민석,장정호";

		StringTokenizer stk = new StringTokenizer(ttt, "[,.$]");

		int i = 0;

		while (stk.hasMoreTokens()) {

			System.out.println(i + " : " + stk.nextToken());

			i++;

		}

//		0 : 박시현
//		1 : 이주혁
//		2 : 김연섭
//		3 : 김현준
//		4 : 오민석
//		5 : 장정호

		// split 과 다른 점이 보이시나요? StringTokenizer 는 공백을 취급하지 않습니다.

	}

}
