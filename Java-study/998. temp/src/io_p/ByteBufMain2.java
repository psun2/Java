package io_p;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteBufMain2 {

	public static void main(String[] args) {

		try {

			// 원본배열
			byte[] arr = { 1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0 };
			// 임시배열
			byte[] buf = new byte[3];
			// 새롭게 생성할 배열
			byte[] newArr;

			System.out.println(arr.length); // 16
			System.out.println(arr.length % 3); // 1

			ByteArrayInputStream bis = new ByteArrayInputStream(arr);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			while (bis.available() > 0) {

				bos.write(bis.read());

			}

			newArr = bos.toByteArray();
			
			System.out.println(Arrays.toString(newArr));

			bis.close();
			bos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
