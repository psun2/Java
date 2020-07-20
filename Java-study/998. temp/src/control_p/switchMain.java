package control_p;

public class switchMain {

	public static void main(String[] args) {

//		switch(10) {
//		switch(true) {		// error
//		switch(123.456) {	// error
//		switch("아기상어") {
		switch (100) {

		case 5:
			System.out.println("5 입니다.");
			break;
		case 20:
			System.out.println("20 입니다.");
			break;
		case 7:
			System.out.println("7 입니다.");
			break; // 제어문 탈출 예약어 - if 문 제외
		case 12:
			System.out.println("12 입니다.");
			break;
		case 8:
			System.out.println("8 입니다.");
			break;
		default:
			System.out.println("존재하지 않습니다.");
			break;

		}

//		String department = "인사부" ;
//		switch(department) {
//		
//		case "인사부":
//			System.out.println("mt 장소는 바다 입니다.");
//			break;
//		case "영업부":
//			System.out.println("mt 장소는 산 입니다.");
//			break;
//		case "부부":
//			System.out.println("mt 장소는 안방 입니다.");
//			break;
//		case "두부":
//			System.out.println("mt 장소는 콩밭 입니다.");
//			break;
//		default:
//			System.out.println("야근 입니다.");
//			
//		}

		String department = "인사부";
		String mt;
		switch (department) {

		case "인사부":
			mt = "mt 장소는 바다 입니다.";
			break;
		case "영업부":
			mt = "mt 장소는 산 입니다.";
			break;
		case "부부":
			mt = "mt 장소는 안방 입니다.";
			break;
		case "두부":
			mt = "mt 장소는 콩밭 입니다.";
			break;
		default:
			mt = "야근 입니다.";

		}

		System.out.println(mt);

		double rr = 1.1;
		String res;

//		TODO switch case 문은 실수로 사용이 불가능 합니다.
//		switch (rr) {
//		case 4.5:
//			res = "A+";
//			break;
//		case 4.0:
//			res = "A";
//			break;
//		case 3.5:
//			res = "B+";
//			break;
//		case 3.0:
//			res = "B";
//			break;
//		case 2.5:
//			res = "C+";
//			break;
//		case 2.0:
//			res = "C";
//			break;
//		case 1.5:
//			res = "D+";
//			break;
//		case 1.0:
//			res = "D";
//			break;
//		default:
//			res = "F";
//		}

		System.out.println((int) (rr * 2)); // 5
		switch ((int) (rr * 2)) {
		case 9:
//		조건인 4.5를 정수로 바꾸기위해 10을 곱해줍니다.
//		조건들이 5의 배수입니다. 5로 나눠 줍니다.
//		10 과 5가 약분이 됩니다. 결국 4.5 에 2를 곱해줍니다.
//		switch 조건에 똑같은 작업을 해줌으로써,
//		두 로직이 일치하게 됩니다.
			res = "A+";
			break;
		case 8:
			res = "A";
			break;
		case 7:
			res = "B+";
			break;
		case 6:
			res = "B";
			break;
		case 5:
			res = "C+";
			break;
		case 4:
			res = "C";
			break;
		case 3:
			res = "D+";
			break;
		case 2:
			res = "D";
			break;
		default:
			res = "F";
		}

		System.out.println(rr + " : " + res);

		System.out.println("====================================");

		System.out.println("다른방법");

		String res2 = "F";
		System.out.println((int) rr); // 2
		switch ((int) rr) {
		case 4:
			res2 = "A";
			break;
		case 3:
			res2 = "B";
			break;
		case 2:
			res2 = "C";
			break;
		case 1:
			res2 = "D";
			break;
		}

//		System.out.println("d" + (int) rr + 0.5);
//		System.out.println(rr - (int) rr + 0.5);

//		2.98 - 2 = 0.98
//		2.98 - 2 + 0.5 = 1.48		
		if (rr - (int) rr >= 0.5 && rr >= 1)
			res2 += "+";

		System.out.println(rr + " : " + res2);

//		선생님 코드 
//		String res2="F";
//		switch((int)rr) {
//			
//			case 4:
//				res2 = "A";
//				break;
//			
//			case 3:
//				res2 = "B";
//				break;
//			
//			case 2:
//				res2 = "C";
//				break;
//			
//			case 1:
//				res2 = "D";
//				break;	
//		}
//		
//		if((int)(rr-(int)rr+0.5)==1 && rr>1)
//			res2+="+";
//		
//		System.out.println(rr+":"+res2);

		System.out.println("====================================");

		System.out.println("다른방법");

//		A는 아스키코드로 65입니다.
		System.out.println((char) 65);
		System.out.println((char) 'A');
		System.out.println((char) 69);
		System.out.println((char) 'E');

		String res3 = "";
		if (rr < 1) {
			res3 = "F";
		} else {
			res3 += (char) ('E' - (int) rr);
			if (rr - (int) rr >= 0.5 && rr >= 1)
				res3 += "+";
		}

		System.out.println(rr + " : " + res3);

//		String res3 = "";
//		if (rr < 1)
//			res3 = "F";
//		else {
//			res3 += (char) ('E' - (int) rr);
//			if ((int) (rr - (int) rr + 0.5) == 1)
//				res3 += "+";
//		}
//
//		System.out.println(rr + " : " + res3);

//		선생님 코드
//		String res3 = "";
//		if (rr < 1)
//			res3 = "F";
//		else {
//			res3 += (char) ('E' - (int) rr);
//			if ((int) (rr - (int) rr + 0.5) == 1)
//				res3 += "+";
//		}
//
//		System.out.println(rr + " : " + res3);
//		System.out.println((char) ('E' - (int) rr));

	}

}
