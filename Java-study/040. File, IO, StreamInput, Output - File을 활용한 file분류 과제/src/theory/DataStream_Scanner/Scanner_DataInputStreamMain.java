package theory.DataStream_Scanner;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class Scanner_DataInputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림
		// DataStream 은 혼자선 사용 불가능 합니다.

		try {

			FileInputStream fis = new FileInputStream("./예제폴더/exam3.txt");
			DataInputStream dis = new DataInputStream(fis);

			int i = 0;
			while (dis.available() > 0) {
				System.out.println(i + " : " + dis.readUTF());
				i++;
			}

			fis.close();
			dis.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
