package basic;

import java.io.IOException;

public class ReadMain {

	public static void main(String[] args) throws IOException {

		String ttt = "";

//		사용자의 입력을 받습니다.
//		​콘솔창에 입력 되는 값은 모두 아스키코드 값으로 입력받는다.
		int ch = System.in.read();
		ttt += (char) ch;

//		System.out.println(ch);
//		System.out.println(ch + " : " + (char) ch);

		ch = System.in.read();
//		System.out.println(ch); // 13 cursor을 맨앞으로 이동
		ttt += (char) ch;

		ch = System.in.read();
//		System.out.println(ch); // 10 enter 기능
		ttt += (char) ch;


		System.out.println(ttt);
	}
}
