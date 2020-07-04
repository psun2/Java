package theory.FileReader;

import java.io.FileInputStream;
import java.io.FileReader;

public class FileReaderMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 2byte 기반 스트림
		// 2byte 의 장점 - 문자열 만 있는 파일에서 아주 강력합니다.

		try {

			// **** FileReader 은 2바이트 기반 스트림으로 써 FileInputStream(1바이트 기반 스트림)
			// 과는 달리 글자가 깨지지 않고 정확히 잘 나옵니다.

			FileReader fr = new FileReader("./예제폴더/exam1.txt");

//			for (int i = 0; i < 100; i++) {
//				System.out.println(fr.read()); // 똑같이 반환될 것이 없으면 -1 반환
//			}

			// *** Reader 는 available() 이 없습니다.

			int data;
			int i = 0;
			while ((data = fr.read()) >= 0) { // -1 이면 반환 될 것이 없습니다.
				// System.out.print(i + " : " + (char) data + ", ");
				System.out.print((char) data);
				i++;
			}

			fr.close();

			System.out.println("\n----------------------------------------");
			System.out.println();

			FileInputStream fis = new FileInputStream("./예제폴더/exam1.txt");

			while (fis.available() > 0) {

				System.out.print((char) fis.read());

			}

			fis.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
