package theory.ByteArrayStream;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteArrayOutputStreamMain {

	public static void main(String[] args) {

		try {

			// 읽을때 input
			// 저장 output
			// 1byte 기반 스트림

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			bos.write(12);
			bos.write(24);
			bos.write(56);
			bos.write(12);
			bos.write(100);
			bos.write(12);
			bos.write(126);
			bos.write(128); // 형변환이 되어 들어 갑니다.
			bos.write(254); // 형변환이 되어 들어 갑니다.
			bos.write(255); // 형변환이 되어 들어 갑니다.
			bos.write(256); // 형변환이 되어 들어 갑니다.

			System.out.println(bos); // 8 d ~���
			// OutStream 은 쓰기 전용 이므로 읽을수 없습니다.

			byte[] arr = bos.toByteArray();

			bos.close();

			// 형변환이 되어 들어 갑니다.
			System.out.println(Arrays.toString(arr));
			// [12, 24, 56, 12, 100, 12, 126, -128, -2, -1, 0]

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
