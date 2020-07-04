package theory.ByteArrayStream_Buf;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

public class Buf_ByteArrayInputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림

		try {

			byte[] arr = { 1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0 };

			ByteArrayInputStream bis = new ByteArrayInputStream(arr);

			byte[] buf = new byte[10]; // 즉 데이터를 보낼 갯수만큼 배열을 생성
			// 네트워크의 최소 단위 1kbyte = 1024byte 로 1024의 길이로 잡아 주는것이 가장 좋은 방법이라고 합니다.
			// 하지만 그만큼 위험하기도 한다고 합니다.

			System.out.println("bis.available() => buf에 보내기 [전] 데이타 수 : " + bis.available());
			// bis.available() => buf에 보내기전 데이타 수 : 16

			int length = bis.read(buf);
			System.out.println(Arrays.toString(buf));
			// [1, 3, 5, 7, 9, 10, 20, 30, 40, 127]
			System.out.println(buf.length); // 10

			System.out.println(length); // 10
			// 보낸 데이터를 int 로 형변환하는 것이 아니라, 데이타 배열을 보내주엇기때문에
			// 들어간 데이터 양 만큼 으로 환산되어 갯수가 나옵니다.

			System.out.println("bis.available() => buf에 보낸 [후] 데이타 수 : " + bis.available());
			// bis.available() => buf에 보낸 [후] 데이타 수 : 6

			// 여기서 이미 buf 는 꽉 차있습니다.
			// buf 배열이 위와 아래에서 어떻게 바뀌었는지 유심히 살펴 주세요.
			// bound를 정해서 데이터를 읽어 올 수 있습니다.
			length = bis.read(buf, 1, 5);
			// buf = > 데이터를 가지고 있는 배열
			// 1 => 시작인덱스
			// ★★★ 5 => 끝 인덱스 and 가지고 올 데이터 의 양
			// buf의 1번 인덱스부터 5번 인덱스 까지
			// read 즉 5개를 읽어 옵니다.

			System.out.println(Arrays.toString(buf));
			// [1, -128, -127, -126, -2, -1, 20, 30, 40, 127]
			System.out.println(buf.length); // 10
			System.out.println(length); // 5
			// length 가 5인 이유는 5개의 데이터를 넘겨주었기 때문입니다.

			bis.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
