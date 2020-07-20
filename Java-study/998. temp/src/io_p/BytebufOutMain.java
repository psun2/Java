package io_p;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class BytebufOutMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte[] arr1 = { 11, 12, 13, 14, 15, 16, 17, 18, 19 };
			byte[] arr2 = { 21, 22, 23, 24, 25, 26, 27, 28, 29 };
			byte[] arr3 = { 31, 32, 33, 34, 35, 36, 37, 38, 39 };

			// byte[] buf = new byte[7];

			// arr1 의 2번째 부터 5개
			bos.write(arr1, 2, 5); // [13, 14, 15, 16, 17]
			// bos.write(arr2, 7, 5); // 갯수 로 인한 Error
			bos.write(arr2, 7, 2); // 갯수 로 인한 Error // [13, 14, 15, 16, 17, 28, 29]
			bos.write(arr3); // [13, 14, 15, 16, 17, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38, 39]

			byte[] newArr = bos.toByteArray();
			System.out.println(Arrays.toString(newArr));
			// [11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31,
			// 32, 33, 34, 35, 36, 37, 38, 39]

			bos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
