package theory.FileStream_Buf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Exam_FileStream_Buf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 이해가 되지 않는다면 theory.ByteArrayStream_Buf 참고

		// 길이가 20인 buf 를 사용 하여
		// 예제폴더 의 exam1.txt 을 읽어들여
		// 예제폴더 2에 같은 내용으로 복사합니다.

		new File("예제폴더2").mkdir();

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림

		try {

			FileInputStream fis = new FileInputStream("./예제폴더/exam1.txt");
			FileOutputStream fos = new FileOutputStream("./예제폴더2/exam1.txt");

			// byte[] buf = new byte[fis.available()];
			byte[] buf = new byte[20]; // 20으로 잡아주면 안되지만 공부를 위해서 20의 사이즈를 줍니다.

			while (fis.available() > 0) {

				System.out.println(fis.available());// 186

				int len = fis.read(buf);
				// read 는 원래 한글자를 int 형으로 반환하는 수를 반환합니다.
				// 하지만 read의 인자로 배열이 들어 갔을때,
				// read 는 배열의 길이를 반환 합니다.

				System.out.println(len); // 186
				System.out.println(buf.length); // 186

				// System.out.println(fis.read());

				// 1byte 기반의 FileInputStream은 여기선 한글깨짐
				System.out.print(new String(buf, 0, len));
				// new String(대상(target), 시작index, 끝 index)
				System.out.println();

				// 파일 형태로 써야 하기때문에 어떤 파일에 쓸 것인지 알아야합니다.
				fos.write(buf, 0, len); // 마지막의 공백 제거
				// 써질때는 읽어들여오는 것 과 는 달리 잘 써집니다.
				// 써질때는 읽어들여오는 것 과 는 달리 잘 써집니다.
				// 써질때는 읽어들여오는 것 과 는 달리 잘 써집니다.
				// 써질때는 읽어들여오는 것 과 는 달리 잘 써집니다.

			}

			fis.close();
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
