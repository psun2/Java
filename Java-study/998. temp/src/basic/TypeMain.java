package basic;

public class TypeMain {

	public static void main(String[] args) {

		System.out.println("1. 정수 >>>>>>");
	
		byte bb = 10;
		System.out.println(bb);
		bb = 127;
		System.out.println(bb);
//		bb = 128;
//		Type mismatch: cannot convert from int to byte
//		유형 불일치 : int에서 byte로 변환 할 수 없습니다
//		bb = 12.345;
//		bb = true;
		bb = 'a'; // 97
		System.out.println("\'a\' = " + bb);
		
		short sh = 128;
		System.out.println(sh);
		sh = 32767;
		sh = -32768;
//		sh = -32769;
		System.out.println(sh);
		
		int ii = -32769;
		ii = 2147483647;
//		ii = 2147483648;
		System.out.println(ii);
		
		long lo = 2147483647;
		System.out.println(lo);
//		lo = 2147483648;
//		The literal 2147483648 of type int is out of range
//		int 유형의 리터럴 2147483648이 범위를 벗어났습니다.
		lo = 2147483648L;
//		TODO 정수는 자료형이 int => long의 영역 까지 표현하기 위해서는 숫자뒤에  L 혹은 l 표기
		
		System.out.println();
		System.out.println("2. 실수 >>>>>>");
		
		float ff = 123.456789789789f;
//		TODO 실수는 자료형이  double => float의 영역을 표현하기 위해서는 숫자뒤에  F 혹은 f 표기
		ff = 123; // 123.0
		System.out.println(ff);
		
		double dd = 123.456789789789;
		dd = 123; // 123.0
		System.out.println(dd);
		
		System.out.println();
		System.out.println("3. 문자 >>>>>>");
		 
		char ch = 'a';
		ch = '가';
//		ch = '';
//		ch = 'korea';
//		ch = "a";
		ch = 100; // d
		System.out.println(ch);
		
		System.out.println();
		System.out.println("4. boolean >>>>>>");
		boolean bo = true;
//		bo = 1;
//		bo = 0;
//		bo = TRUE;
//		bo = "true";
//		bo = 't';
		bo = false;
		System.out.println(bo);
		
	}

}
