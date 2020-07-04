package theory.ByteArrayStream_Buf;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class Buf_ByteArrayOutputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림

		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte[] arr1 = { 11, 12, 13, 14, 15, 16, 17, 18, 19 };
			byte[] arr2 = { 21, 22, 23, 24, 25, 26, 27, 28, 29 };
			byte[] arr3 = { 31, 32, 33, 34, 35, 36, 37, 38, 39 };

			// *** Input의 read 와 Output의 write 의 차이점 !!!!
			// read("읽어서 쓸 대상",시작위치, 끝위치 );

			// 중요 !!!
			// 만약 write의 갯수가 배열의 길이 보다 크다면 Error
			// write("읽어드릴 대상", 시작위치, 갯수);

			bos.write(arr1, 2, 5); // [13, 14, 15, 16, 17]

			// ErrorPoint 시작 인덱스에서 배열의 길이보다 초과하는 데이터를 가져 오려고 할때....
			// bos.write(arr2, 7, 5); // Error 이지만 에러 메세지는 나오지 않는 기이한 현상이 벌어 집니다...

			bos.write(arr2, 7, 2); // [13, 14, 15, 16, 17, 28, 29]

			// 해당배열 전부를 쓰게 할 수 있습니다.
			bos.write(arr3);
			// [13, 14, 15, 16, 17, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38, 39]

			byte[] newArr = bos.toByteArray();
			System.out.println(Arrays.toString(newArr));

			bos.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
