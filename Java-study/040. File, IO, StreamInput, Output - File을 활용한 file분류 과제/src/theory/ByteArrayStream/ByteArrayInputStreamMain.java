package theory.ByteArrayStream;

import java.io.ByteArrayInputStream;

public class ByteArrayInputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// 읽을때 input
			// 저장 output
			// 1byte 기반 스트림

			byte[] arr = { 1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0 };

			// prams : 읽어드릴 대상
			ByteArrayInputStream bis = new ByteArrayInputStream(arr);

			// 데이터 갯수 확인
			System.out.println(arr.length); // 배열 : 16
			System.out.println(bis.available()); // 16

			// Stream 은 queue 입니다.
			// Queue 는 선입선출 로 서 먼저 들어간게 먼저 빠져 나옵니다.
			// read => 데이터 한개를 빼서 읽어 드립니다
			System.out.println(bis.read()); // 1 0번째 부터 순차대로 빼옴
			System.out.println(bis.available()); // 15 => 남은 데이타의 갯수
			System.out.println(bis.read()); // 3 한개를 빼옴
			System.out.println(bis.available()); // 14 => 남은 데이타의 갯수

			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());

			System.out.println(bis.read() + " : " + bis.available()); // -128 (X) // 128 반환
			// read 는 int 형태로 반환하기 때문에 int 으로 형변환 되어
			// int 자료형의 범위로 나오게 됩니다.

			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			// 더이상 빼올 데이터가 없으면 -1 을 반환 합니다.
			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());
			System.out.println(bis.read() + " : " + bis.available());

/////////////////////////////////////////////////////////////////////////////////////////////////////		

			ByteArrayInputStream bis2 = new ByteArrayInputStream(arr);

			// 반복문을 활용한 출력 방법
			while (bis2.available() > 0) {

				System.out.println(bis2.read());

			}

			bis.close();
			bis2.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
