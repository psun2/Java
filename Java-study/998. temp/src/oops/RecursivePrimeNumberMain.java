package oops;

public class RecursivePrimeNumberMain {

	static int primeCnt(int no) {
		int res = primeChk(no - 1, no);

		if (no > 2)
			res += primeCnt(no - 1);
		else if (no == 2)
			res = 1;

		return res;
	}

	static int primeChk(int i, int no) { // 여기서부터 만듬

//		res = no % i / (i - 1);
		int res = no % i;

		if (i > 2 && res != 0)
			res = primeChk(i - 1, no);

//		System.out.println(i + ": " + res);

		return res;
	}

	public static void main(String[] args) {

		int res = primeCnt(10);
		System.out.println("main: " + res); // 소수 일시 1 , 아닐시 0 반환

	}

}
