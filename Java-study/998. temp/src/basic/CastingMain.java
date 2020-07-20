package basic;

public class CastingMain {

	public static void main(String[] args) {
		 
		byte bb = 123;
		short sh = bb; // 자동 형변환(casting)
		System.out.println(bb);
		System.out.println(sh);

		sh = 129;
		sh = 130;
		sh = 254;
		sh = 255;
		sh = 256;
		sh = 257;
		bb = (byte)sh; // 강제 형변환
		System.out.println(bb);
		System.out.println(sh);
		System.out.println("----------------");
		
		sh = 32767;
		int ii = sh;
		System.out.println(sh);
		System.out.println(ii);
		
		ii = 32770;
		sh = (short)ii;
		System.out.println(sh);
		System.out.println(ii);
		
		System.out.println("정수 <-> 실수----------------");
		ii = 123;
		double dd = ii;
		System.out.println(ii);
		System.out.println(dd);
		
		dd = 123.456;
		ii = (int)dd;
		System.out.println(ii);
		System.out.println(dd);
	
		System.out.println("숫자 <-> 문자----------------");
		char ch = 'a';
		ii = ch;
		System.out.println(ch);
		System.out.println(ii);
		
		ii = 100;
		ch = (char)ii;
		System.out.println(ch);
		System.out.println(ii);
		System.out.println('a' + 100); // 197
		System.out.println('1' + 1); // 50
		
		int qq = '2'+ 3;
		System.out.println(qq); // 53
		System.out.println(qq - '0'); // 50
		

	}

}
