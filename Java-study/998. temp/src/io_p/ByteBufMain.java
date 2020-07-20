package io_p;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteBufMain {

	public static void main(String[] args) {

		try {

			// 원본배열
			byte[] arr = { 1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0 };
			// 임시배열
			byte[] buf = new byte[3];
			// 새롭게 생성할 배열
			ByteArrayInputStream bis = new ByteArrayInputStream(arr);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			while (bis.available() > 0) {
				System.out.println("읽어요");

//				System.arraycopy(buf, pos, b, off, len);
//				buf = 원본
//				pos = 복사할 위치
//				b = 복사할 대상
//				off = 시작위치
//				len = 끝 

				int len = bis.read(buf);

				bos.write(buf, 0, len);
			}

			byte[] newArr = bos.toByteArray();

			bis.close();
			bos.close();

			System.out.println(Arrays.toString(newArr));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
