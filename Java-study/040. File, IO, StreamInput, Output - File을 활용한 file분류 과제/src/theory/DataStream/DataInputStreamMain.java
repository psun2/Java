package theory.DataStream;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class DataInputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림
		// DataStream 은 혼자선 사용 불가능 합니다.

		try {

			FileInputStream fis = new FileInputStream("./예제폴더/exam2.txt");

			// DataStream 은 혼자선 사용 불가능 합니다.
			DataInputStream dis = new DataInputStream(fis);

			// 장점 : 출력시 정상 출력되며 깨지지 않습니다.
			// 장점 : 특히 문자열에 특화되어 있습니다.
			// 단점 : 순서가 꼬이면 이상한 것들을 반환 합니다....

			// 순서 있게 가져오는 것이 가장 중요 !!!!!!!!!!!!!!!!!
			System.out.println(dis.readBoolean());
			System.out.println(dis.readChar());
			System.out.println(dis.readDouble());
			System.out.println(dis.readInt());
			System.out.println(dis.readFloat());
			System.out.println(dis.readLong());
			System.out.println(dis.readUTF());
			System.out.println(dis.readUTF());

			fis.close();
			dis.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
