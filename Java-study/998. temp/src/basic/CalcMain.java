package basic;

public class CalcMain {

	public static void main(String[] args) {

		System.out.println("<<<<1.1 1항연산자>>>>");
		int x = -10;
		boolean bo = false;
		System.out.println(-x);
		System.out.println(!bo);
		
		System.out.println("<<<<1.2 증감연산자>>>>");
		
		x = 5;
//		x = x + 1;
		
//		후치연산자
		x++;
		System.out.println(x);
		x--;
		System.out.println(x);
//		x**;
//		x//;
//		x%%
		
//		전치연산자
		++x;
		System.out.println(x);
		--x;
		System.out.println(x);
		
		x = 10;
		int y = x++;
		System.out.println(x+", "+y);
		
		x = 10;
		y = ++x;
		System.out.println(x+", "+y);
		
		int aa = 5, bb = 6, cc = 10, dd;
		dd = aa++ + ++cc - bb-- * --aa + aa++;
		
		System.out.println(aa + ", " + bb + ", " + cc + ", " + dd);
		System.out.println(dd);

		System.out.println("<<<<2.1 산술연산자>>>>");
//		param : 숫자, 문자
//		ret : 숫자, 문자
		int a = 15, b = 7;
		String buf = a + " + " + b + " = " + a + b;
		String buf2 = a + " + " + b + " = " + (a + b);
		System.out.println(buf); // 15 + 7 = 157
		System.out.println(buf2); // 15 + 7 = 22
		System.out.println(a + b); // 22
		System.out.println(a + " - " + b + " = " + (a - b));
		System.out.println(a + " * " + b + " = " + (a * b));
		System.out.println(a + " / " + b + " = " + (a / b));
		System.out.println(a + " % " + b + " = " + (a % b));
		System.out.println(3 + 4 * 5); // 23
		System.out.println((3 + 4) * 5); // 35

//		문자열 : + ==> 문자열 연결
		String str1 = "소지섭", str2 = "차은우";
		System.out.println(str1 + "\t" + str2); // 소지섭차은우
//		System.out.println(str1 - str2);
//		System.out.println(str1 * str2);
//		System.out.println(str1 / str2);
//		System.out.println(str1 % str2);

		System.out.println(str1 + b); // 소지섭7
//		System.out.println(str1 - b);
//		System.out.println(str1 * b);
//		System.out.println(str1 / b);
//		System.out.println(str1 % b);

		System.out.println("<<<<2.2 비교연산자>>>>");
//		param : 숫자, 문자, boolean
//		ret : boolean

		a = 10;
		b = 20;

		System.out.println(a + " > " + b + " = " + (a > b + 30)); // false
//		비교 연산자보다 산술 연산자의 우선순위가 먼저가 됩니다. 
//		산술 연산자가 비교 연산자보다 우선순위가 위에 있으므로, b에 30을 더한뒤, 비교합니다.

		System.out.println(a >= b); // false
		System.out.println(a <= b); // true
		System.out.println(a < b); // true
		System.out.println(a == b); // false
		System.out.println(a != b); // true

		System.out.println("----------------");

		str1 = "현빈";
		str2 = "원빈";
//		System.out.println(str1 > str2); 
//		System.out.println(str1 >= str2);
//		System.out.println(str1 <= str2);
//		System.out.println(str1 < str2); 
		System.out.println(str1 == str2);
		System.out.println(str1 != str2);

		System.out.println("----------------");

		boolean bo1 = true, bo2 = false;
//		System.out.println(bo1 > bo2);
//		System.out.println(bo1 >= bo2);
//		System.out.println(bo1 <= bo2);
//		System.out.println(bo1 < bo2); 
		System.out.println(bo1 == bo2);
		System.out.println(bo1 != bo2);

		System.out.println("<<<<2.3 논리연산자>>>>");
//		param : boolean
//		ret : boolean

		bo1 = true;
		bo2 = false;

		System.out.println(bo1 && bo2);
		System.out.println(bo1 || bo2);
		System.out.println(bo1 ^ bo2);
//		^(Xor) 이라고 하며, 같지 않아야  ture가 됩니다. 
//		즉, 좌항과 우항이 같지 않을때만 true입니다.

		String color = "파랑";
		int age = 29;

//		boolean ccChk = color == "빨강";
//		boolean ageChk = age < 25;
//
////		System.out.println("And" + ccChk && ageChk);
//		System.out.println("And : " + (ccChk && ageChk));
//		System.out.println("Or : " + (ccChk || ageChk));
//		System.out.println("Xor : " + (ccChk ^ ageChk));

		System.out.println("And : " + (color == "빨강" && age < 25));
		System.out.println("Or : " + (color == "빨강" || age < 25));
		System.out.println("Xor : " + (color == "빨강" ^ age < 25));

//		for (int i = 0; i < 7; i++) {
//			System.out.println((int) Math.floor(Math.random() * 45 + 1));
//		}

//		System.out.println(a + "+" + b + "=" + plus(a, b));
//		System.out.println(a + "-" + b + "=" + minus(a, b));

		System.out.println("<<<<2.4 shift연산자>>>>");

		System.out.println("6 << 2 : " + (6 << 2));
//		0110 << 2
//		0110 00
//		0001 1000

		System.out.println("6 >> 2 : " + (6 >> 2));
//		0110 >> 2
//		00 0110
//		0001 10(버려짐)

		
//		1111 1010 << 2
//		1111 1010 00
//		11 1110 1000
//		1110 1000
		System.out.println("-6 << 2 : " + (-6 << 2));
		
		
//		1111 1010 << 2
//		11 1111 1010 (음수)
//		1111 1110 10(버림)
//		1111 1110
		System.out.println("-6 >> 2 : " + (-6 >> 2));

//		>>> 음수 양수 상관없이 0이 들어옴
//		1111 1111 1111 1111 1111 1111 1111 1010 >>> 2
//		00 1111 1010 (음수)
//		0011 1110 10(버림)
//		0011 1110
		System.out.println("-6 >> -2 : " + (-6 >>> 2));

//		System.out.format("%o\n", 176);
//		System.out.format("%x\n", 176);

		System.out.println("<<<<2.5 bit연산자>>>>");
		
		System.out.println("5 & 3 : "+ (5 & 3));
		System.out.println("5 | 3 : " + (5 | 3));
		System.out.println("5 ^ 3 : " + (5 ^ 3));
		
		/*
		 
		0101	0:false,	1:true
		0011
		---------
		&	0001
		|	0111
		^	0110
		
		*/
		
		System.out.println("<<<<2.7 대입연산자>>>>");
		
		System.out.println("a = 5 : " + (a = 5));
		System.out.println("a += 10 : " + (a += 10));
		System.out.println("a -= 3 : " + (a -= 3));
		System.out.println("a *= 4 : " + (a *= 4));
		System.out.println("a /= 3 : " + (a /= 3));
		System.out.println("a %= 6 : " + (a %= 6));
		System.out.println("a <<= 2 : " + (a <<= 2));
//		4 << 2
//		0100 << 2
//		0100 00	=>	0001 0000
		
		System.out.println("<<<<3 조건연산자>>>>");
		
		System.out.println(true  ? "참" : 1234);
		System.out.println(a > 0 || a == 0 ? a : -1);
		
		int jum = 88;
		String res = jum >= 80 ? "합격" : "불합격";
		System.out.println(jum + " : " + res);
		
		res = jum >= 80 ? "우수" : jum >= 60 ? "정상" : "미달";
		System.out.println(jum + " : " + res);
	}

//	public static int plus(int a, int b) {
//		return a + b;
//	}
//
//	public static int minus(int a, int b) {
//		return a - b;
//	}
//
//	public static int temps(int a, int b) {
//		return a * b;
//	}
//
//	public static int division(int a, int b) {
//		return a / b;
//	}
}
