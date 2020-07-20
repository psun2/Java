package oops;

//  지역변수와 맴버변수는 최소화 시켜주면서 재귀함수를 이용하여 최적화 할 수 있습니다.
//  단점: 보완

class Recursive {

	String pre = "";

//	void meth(int no) {
//		String my = pre;
//		pre += "\t";
//
//		System.out.println(my + "시작: " + no);
//
//		if (no > 0)
//			meth(no - 1); // 증감
//
//		System.out.println(my + "끝: " + no);
//	}

	int meth(int no) {
		String my = pre;
		pre += "\t";

		int res = no;

		System.out.println(my + "시작: " + no + ", " + res);

		if (no > 0)
			res += meth(no - 1); // 증감

		System.out.println(my + "끝: " + no + ", " + res);
		return res;
	}

	// 짝수들의 합
	int even(int no) {

		int result = no;

		if (no == 0)
			return 0;

		if (no % 2 == 0)
			result += even(no - 2);
		else {
			result = 0;
			result += even(no - 1);
		}
		return result;
	}

//		no -= no % 2 // 홀 수 일땐 -1 을 해주어 짝수를 만들어 주고 짝수일땐 -0을 해주어 값을 유지
//		int res = no;
//		
//		if(no > 0)
//			res += (no-2)

// 소수의 갯수
//	int count(int number) {
//		int count = 0;
//		int i = 2;
//
//		if (number == 2) {
//			count = 1;
//			return count;
//		}
//		if (number % i != 0) {
//			count++;
//			count += count(number - 1);
//		} else {
//			count += count(number - 1);
//		}
//
//		return count;
//
//	}
	int count(int number) {
		int count = 0;
		int i = 2;
		
		if(number > 1)
			count++;
			count += count(number - 1);
		
		return count;
		
	}
}

public class RecursiveCallMain {

	public static void main(String[] args) {

		Recursive rc = new Recursive();
//		rc.meth(3); // 초기값
		int rr = rc.meth(3); // 초기값
		System.out.println("메인: " + rr);

		int su = 10;
		int even = rc.even(su);
		System.out.println(su + "값 중에 짝수의 합 : " + even);

		int count = rc.count(su);
		System.out.println(su + "값 중에 소수의 갯수 : " + count);

		System.out.println(7 % 2);
	}

}



ㄴㅁ어ㅏㅣㅁㄴ이마러ㅏㅁ너아ㅓ마서ㅘ히녀5ㅇ8732ㅑㅓㅏ트추ㅏㅣ