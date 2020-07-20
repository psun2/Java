package basic;

public class Test {

	public static void main(String[] args) {
		
//		주석 == 설명이나 참조내용 들 코드 진행과 상관없이 기재가능
		System.out.println(1234); // 정수출력
//		char 형식은 '(홀 따옴표)로 표현이 가능합니다.
		char str = 'ㄱ';
		System.out.println('a'); // 문자 - 반드시 한글자 이어야 한다.
		System.out.println(str);
//		String 형식은 "(따옴표)로 만 표현이 가능합니다.
		String  name = "엄마상어"; // 문자열 - 글자의 길이가 자유롭다. 
		System.out.println("아기상어");
		System.out.println(name);
		System.out.println(123.456);
		
		/* 
		 	주석 구간
		 	System.out.println("내용");  내용 출력 후 줄바꿈 진행 O
		 	
		 	System.out.print("내용");  	내용 출력 후 줄바꿈 진행 X
		 	
		 	;(세미콜론)	현재 명령문 종결
		 */
		
//		print 는 출력과 동시에 줄바꿈이 되지 않습니다.
		System.out.print("개나리");
		System.out.print("개나리");
		System.out.print("얼룩 개나리");
		System.out.print("개나리");
		
//		println 은 출력과 동시에 줄바꿈이 됩니다.
		System.out.println("개나리");
		System.out.println("개나리");
		System.out.println("얼룩 개나리");
		System.out.println("개나리");
		
//		\t : tab
//		\n : enter 줄바꿈을 의미합니다.
//		\b : 백스페이스 (뒤로 한칸 이동)
//		\r : \n 과 동일한 기능 리눅스에서는 \n밖에 사용되지 않습니다.
//		\" : 글자 "(따옴표)로 인지합니다.
//		\' : 글자 '(홀 따옴표)로 인지합니다.
//		\\ : 역슬러쉬가 서식표시가 아니라고 표현하여 역슬러쉬를 출력합니다.
		System.out.print("123	456\t789\n123\b456\n789\n");
		System.out.println("나폴레옹은 '이 산이 아닌데?' 라고 생각했다.");
		System.out.println("나폴레옹은 \"불이야\" 라고 생각했다.");
		System.out.println("나폴레옹은 \'불이야\" 라고 생각했다.");
		System.out.println("나폴레옹은 \\ 라고 생각했다.");
	
		System.out.println("  +----------------+");
		System.out.println(" /♨♨♨♨♨♨♨♨♨  /|");
		System.out.println("+----------------+ |");
		System.out.println("|                | |");
		System.out.println("|     ^     ^    | |");
		System.out.println("|     ♥     ♥    | |");
		System.out.println("|        ▽                        | |");
		System.out.println("|        〓                        | +");
		System.out.println("|                |/");
		System.out.println("+----------------+");
		
		
//		System.out.println(" ");
//		
//		System.out.println("200");
//		System.out.format("%o\n", 200); // 310
//		System.out.format("%x\n", 200); // c8
	}
	
}
