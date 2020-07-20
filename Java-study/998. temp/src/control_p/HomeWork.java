package control_p;

public class HomeWork {

	public static void main(String[] args) {

//		과목 : 국어 40%, 영어 20% , 수학 10% , 국사 30%,   가산점 :  코딩 + 0.2
//			   87       62        77        86                     0.1
//	    ex)    87 * 40% + 62*20% + 77 * 10% + 86 * 30%  = 87.9  ->3.9555 +0.1 =   >  4.0555
//	       평점만점 : 4.5 로 환산 하여 등급을 구하시오
//		4.5    A+
//		4.0    A
//		3.5    B+
//		3.0    B
//		2.5    C+
//		2.0    C
//		1.5    D+
//		1.0    D
//		       F

		double kor, eng, math, his, total, exchange, result; // average 없어도 됩니다.
		kor = 87 * 0.4;
		eng = 62 * 0.2;
		math = 77 * 0.1;
		his = 86 * 0.3;
		total = kor + eng + math + his;
//		average = total / 4; // 기본적으로 틀리고 없어도 됩니다.
		exchange = 0.045;
		result = total * exchange;

		System.out.println("kor : " + kor);
		System.out.println("eng : " + eng);
		System.out.println("math : " + math);
		System.out.println("his : " + his);
		System.out.println("total : " + total);
//		System.out.println("average : " + average); // 기본적으로 틀리고 없어도 됩니다.
		System.out.println("exchange : " + exchange);
		System.out.println("result : " + result);

		System.out.println("");
		System.out.println("<방법1>");

		System.out.println((char) 'E');
		System.out.println((char) 69);

		String res = "";
		if (result < 1)
			res = "F";
		else {
			res += (char) ('E' - (int) result);
			if (result - (int) result >= 0.5 && result > 1)
				res += "+";
		}

		System.out.println(result + " : " + res);

		System.out.println("-----------------------------");

		System.out.println("<방법2>");

//		System.out.println((int) result);
		String res2 = "F";
		switch ((int) result) {

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

		if (result - (int) result >= 0.5 && result >= 1)
			res2 += "+";

		System.out.println(result + " : " + res2);

		System.out.println("-----------------------------");

		System.out.println("<방법3>");

		System.out.println((int) (result * 10) / 5);
		System.out.println((int) (result * 2));
//		case 를 보니 4.5, 4.0, 3.5 .....
//		switch 문은 정수로 밖에 작동이 안되니, 정수형으로 만들어 주어야합니다.
//		소수점을 없애기 위해 * 10 을 해줍니다.
//		모든 case 들이 5를 공약수로 가지고 있습니다.
//		5로 나누어 줍니다.
//		여기서 10은 5의 약수 이므로 결국 result * 2와 같습니다.

		String res3;

		switch ((int) (result * 2)) {

		case 9:
			res3 = "A+";
			break;
		case 8:
			res3 = "A";
			break;
		case 7:
			res3 = "B+";
			break;
		case 6:
			res3 = "B";
			break;
		case 5:
			res3 = "C+";
			break;
		case 4:
			res3 = "C";
			break;
		case 3:
			res3 = "D+";
			break;
		case 2:
			res3 = "D";
			break;
		default:
			res3 = "F";

		}

		System.out.println(result + " : " + res3);

	}

}
