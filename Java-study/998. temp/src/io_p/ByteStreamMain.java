package io_p;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteStreamMain {

	public static void main(String[] args) {

		try {

			byte[] arr = { 1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0 };

			// 읽을때 input
			// 저장 output

			// prams : 읽어드릴 대상
			ByteArrayInputStream bis = new ByteArrayInputStream(arr);

			System.out.println(arr.length); // 16
			System.out.println(bis.available()); // 16 => 데이타의 갯수

			System.out.println(bis.read()); // 1 0번째 부터 순차대로 빼옴
			System.out.println(bis.available()); // 15 => 남은 데이타의 갯수
			System.out.println(bis.read()); // 3 한개를 빼옴
			System.out.println(bis.available()); // 14 => 남은 데이타의 갯수

			// read() 는 int 형태로 받습니다.
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());
//			System.out.println(bis.read() + " : " + bis.available());

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			while (bis.available() > 0) {

				int data = bis.read();
				System.out.println((byte) data);
				bos.write(data);

			}

			byte[] newArr = bos.toByteArray();
			System.out.println(Arrays.toString(newArr));

//			while (bis.available() > 0) {
//				System.out.println((byte) bis.read());
//			}

			bis.close(); // 열었으면 무조건 닫아 주어야 함
			bos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
