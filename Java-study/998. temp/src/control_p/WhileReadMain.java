package control_p;

import java.io.IOException;

public class WhileReadMain {

	public static void main(String[] args) throws IOException {

		System.out.print("수1 : ");
//		String no1 = "";
		int no1 = 0;

		while (true) {
			int ch = System.in.read();

			if (ch == 13)
				break;

			no1 *= 10;
			no1 += ch - '0';

//			System.out.println(no1);
		}

		System.in.read(); // 엔터키가 13, 10 두개의 값을 받으므로 한개를 더 버려야함

		System.out.println(no1);

		System.out.print("수2 : ");

		int no2 = 0;

		while (true) {
			int ch = System.in.read();

			if (ch == 13)
				break;

			no2 *= 10;
			no2 += ch - '0';

//				System.out.println(no1);
		}

		System.out.println(no2);
		System.out.println("결과 : " + (no1 + no2));
	}

}
