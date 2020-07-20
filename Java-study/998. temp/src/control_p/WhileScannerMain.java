package control_p;

import java.util.Scanner;

public class WhileScannerMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

//		while (true) {
//
//			System.out.print(" 입력 : ");
//
//			int no1 = sc.nextInt();
//
//			if(no1 == 0)
//				break;
//			
//			System.out.println(no1 + 100);
//			
//		}
//		
//		System.out.println("종료");

//		최대값, 합계, 평균

		int max = 0, total = 0, count = 0, min = 0;

		while (true) {

			System.out.print(" 입력 : ");

			int no = sc.nextInt();

			if (no <= 0)
				break;

//			max = no > max ? no : max;
//			min = no > min ? min : no; // 0 을 왜받지 ?
			if (no > max)
				max = no;
			else
				min = no;

			total += no;
			count += 1;

			System.out.println("입력 값 : " + no + " 입니다.");

		}
		double avg = (double) total / count;

		sc.close();
		System.out.println("종료");
		System.out.println("최대값 : " + max + " 입니다.");
		System.out.println("최소값 : " + min + " 입니다.");
		System.out.println("총 합 : " + total + " 입니다.");
		System.out.println("평균 : " + avg + " 입니다.");

	}

}
