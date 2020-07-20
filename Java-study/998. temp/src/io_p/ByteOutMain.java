package io_p;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteOutMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

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

			byte[] arr = bos.toByteArray();

			bos.close();

			// 형변환이 되어 들어 갑니다.
			System.out.println(Arrays.toString(arr)); // [12, 24, 56, 12, 100, 12, 126, -128, -2, -1, 0]

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
