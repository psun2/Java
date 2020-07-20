package io_p;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteBufInMain {

	public static void main(String[] args) {

		// 네트워크의 최소 단위 1kbyte = 1024byte

		try {

			byte[] arr = { 1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0 };

			byte[] buf = new byte[10]; // 즉 데이터를 보낼 갯수만큼 배열을 생성
			// 네트워크의 최소 단위 1kbyte = 1024byte 로 1024의 길이로 잡아 주는것이 가장 좋은 방법이라고 합니다.

			ByteArrayInputStream bis = new ByteArrayInputStream(arr);

			System.out.println("bis.available()_1 : " + bis.available()); // bis.available()_1 : 16 => 보내기전 데이터 갯수

			// int cnt = bis.read(buf);

			// ✔✔보낼 데이터의 양을 조절 할 수 도 있습니다.
			int cnt = bis.read(buf, 3, 6); // 데이타, 시작위치, 갯수

			System.out.println("cnt : " + cnt); // cnt : 10
			System.out.println("bis.available()_2 : " + bis.available()); // bis.available()_2 : 6 => 보낸후 데이터 갯수
			System.out.println("buf : " + Arrays.toString(buf)); // buf : [1, 3, 5, 7, 9, 10, 20, 30, 40, 127]

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			System.out.println();

			// 남은 데이터 보내기

			// cnt 가 6인 이유 => 위에서 보내주엇기때문에 남은 데이터 갯수가 6개 이므로....
			// cnt = bis.read(buf);
			cnt = bis.read(buf, 1, 6); // 데이타, 시작위치, 갯수

			System.out.println("cnt : " + cnt); // cnt : 6
			System.out.println("bis.available()_2 : " + bis.available()); // bis.available()_2 : 0 : 6 => 보낸후 데이터 갯수

			// 단점
			// 위의 1, 3, 5, 7, 9, 10 부분을
			// 남은 6개로 채워 업데이트 되어 버립니다.
			System.out.println("buf : " + Arrays.toString(buf)); // buf : [-128, -127, -126, -2, -1, 0, 20, 30, 40, 127]
			// 한번에 많은 양의 데이터를 보낼 수 있지만 단점은 정확하지 않은 데이터가 올 수 도 있습니다.

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			System.out.println();

			cnt = bis.read(buf, 1, 6); // read 해서 가지고 온 갯수
			System.out.println("cnt : " + cnt); // cnt : 4
			System.out.println("bis.available()_3 : " + bis.available()); // bis.available()_3 : 0 => 보낸후 데이터 갯수
			System.out.println("buf : " + Arrays.toString(buf)); // buf : [0, -126, -2, -1, 0, -128, -127, 9, 10, 0]

			bis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
