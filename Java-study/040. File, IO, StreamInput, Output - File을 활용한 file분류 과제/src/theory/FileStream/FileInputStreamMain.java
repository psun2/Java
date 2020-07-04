package theory.FileStream;

import java.io.FileInputStream;

public class FileInputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림

		try {

			FileInputStream fis = new FileInputStream("./가사/미국/New Rules.txt");

			System.out.println(fis.available()); // 40

			while (fis.available() > 0) {
				System.out.print(fis.read()); // int 형태로 읽어드려...
				// 즉 int 형으로 형변환이 되어 숫자로 출력 됩니다.
			}

			System.out.println();

			// 한번 빨아드려 읽은 Stream 은 0이 되어 더이상 쓸모 없습니다.
			System.out.println(fis.available()); // 0

			while (fis.available() > 0) {
				System.out.println("진입");
				System.out.print((char) fis.read());

			}

			System.out.println();

			FileInputStream fis2 = new FileInputStream("./가사/미국/New Rules.txt");

			while (fis2.available() > 0) {
				System.out.print((char) fis2.read());
			}

//			New Rules
//			ëì¤ => 한국어 부분은 깨져서 출력이 됩니다. 한국어는 1byte 로 읽을 수 없습니다.
//			Dua Lipa
//			ë¯¸êµ­ =>  => 한국어 부분은 깨져서 출력이 됩니다. 한국어는 1byte 로 읽을 수 없습니다.
//			abc

			fis.close();
			fis2.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
