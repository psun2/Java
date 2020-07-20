package basic;

public class VarMain {
	
	public static void main(String[] args) {
		
//		5 + 3 = 8;
		int x; // 변수 선언 : 자료형 변수명;
//		정수 x;
//		5 + 3 = x;

		x = 5 + 3;
		
//		변수 = 값;
		
		System.out.println(x); // 8
		
//		int x; 동일한 이름의 변수 선언는 1회만 가능
//		Duplicate local variable x
//		지역 변수 x 중복
		
		x = 20; // 변수 값 대입
		System.out.println(x); // 변수 값 호출 20
		
//		y = 30; 선언되지 않은 변수 사용 불가
//		y cannot be resolved to a variable
//		y는 변수로 해석 될 수 없습니다
//		System.out.println(y); // 변수 값 호출
		
		int z;
//		System.out.println(z); // 값이 대입되지 않은 변수 호출 불가
		
		int k = 345; // 선언 및 초기화(대입) : 선언 과 같이 값 대입
		System.out.println(k); // 345
		
		int j = k; // 각종 수식에서 변수 값 호출 가능
		System.out.println(j); // 345
		
		int aa, bb, cc; // 변수 동시 선언 가능
		
//		int qq = ww = 5; // error
		
//		int qq = ww, ww = 5; 변수의 선언 순서는 왼쪽부터

		int ww = 5, qq = ww; 
		System.out.println(ww); // 5
		System.out.println(qq); // 5
		
//		변수명 (camel 표기법)
		
//		int 91 = 81;
//		int + = 45;
//		int ss dd = 78;
//		int int = 88;

		int as1df = 90;
		int $_as1d_f = 90;
		System.out.println(as1df);
		System.out.println($_as1d_f);
		
		int 국어 = 89;
		System.out.println(국어);
		
		String studentName = "정우성";
		System.out.println(studentName);
		
		int kor;
		int average;
		int avg;
		
		String studname = "정우성";
		String studName = "정우성"; // => camel 표기법 (낙타등)
		String stud_name = "정우성"; // 스네이크 표기법
		
	}

}
