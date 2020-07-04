package theory.ByteArrayStream_Buf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class Exam_ByteArray_Buf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림

		// 원본배열에서 길이가 3개인 buf를 사용하여, 원본 배열과 똑같은 새로운 배열 만들기

		try {

			// 원본배열
			byte[] arr = { 1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0 };
			// buf
			byte[] buf = new byte[3];

			ByteArrayInputStream bis = new ByteArrayInputStream(arr);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			while (bis.available() > 0) {

				int length = bis.read(buf);

				bos.write(buf, 0, length);
				// 이렇게 하는 이유는 마지막 글자는 몇개일지 몰라서.... 공백을 제거 하기 위한 방법 입니다.

			}

			byte[] newArr = bos.toByteArray();

			bis.close();
			bos.close();

			System.out.println(Arrays.toString(newArr));
			// [1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0]

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
