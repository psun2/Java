package theory.ByteStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayInputStreamMain {

	public static void main(String[] args) {

		// 스트림 : 빨대
		// 크게 두 종류로 나눌 수 있습니다.
		// input : 읽을때 => 데이터를 읽는다 => 즉 빨대에 데이터가 들어온다 => 저장된 원래 위치 에서 빼내 옵니다.
		// output : 저장 할때 => 인풋과 반대로 데이터를 저장소에 저장합니다.
		try {

			byte[] arr = { 1, 3, 5, 7, 9, 10, 20, 30, 40, 127, -128, -127, -126, -2, -1, 0 };

			ByteArrayInputStream bis = new ByteArrayInputStream(arr);

			System.out.println(arr.length); // 16
			System.out.println(bis.available()); // 16 => 데이타가 저장된 갯수

			System.out.println(bis.read()); // 1 => 1개가아닌 요소 하나하나를 가져 옵니다.
			System.out.println(arr.length); // 16 => 배열에서 나간 데이터가 아니기 때문에 배열의 길이는 변화 없습니다.
			// 즉 배열은 건드리지 않습니다.
			System.out.println(bis.available()); // 15 => 데이타가 저장된 갯수
			// 요소 1개가 빠져나가 저장된 데이터 갯수가 줄어들었습니다.

			// read 함수는 int 형으로 반환 받습니다.
			// 즉 byte 형태를 int 형태로 돌려 주어 int 의 범위대로 반환을 받습니다.
//			System.out.println(bis.read() + " : " + bis.available()); // 3 : 14
//			System.out.println(bis.read() + " : " + bis.available()); // 5 : 13
//			System.out.println(bis.read() + " : " + bis.available()); // 7 : 12
//			System.out.println(bis.read() + " : " + bis.available()); // 9 : 11
//			System.out.println(bis.read() + " : " + bis.available()); // 10 : 10
//			System.out.println(bis.read() + " : " + bis.available()); // 20 : 9
//			System.out.println(bis.read() + " : " + bis.available()); // 30 : 8
//			System.out.println(bis.read() + " : " + bis.available()); // 40 : 7
//			System.out.println(bis.read() + " : " + bis.available()); // 127 : 6
//			
//			System.out.println();
//			
//			System.out.println(bis.read() + " : " + bis.available()); // 128 : 5 => int형 반환
//			System.out.println(bis.read() + " : " + bis.available()); // 129 : 4 => int형 반환
//			System.out.println(bis.read() + " : " + bis.available()); // 130 : 3 => int형 반환
//			System.out.println(bis.read() + " : " + bis.available()); // 254 : 2 => int형 반환
//			System.out.println(bis.read() + " : " + bis.available()); // 255 : 1 => int형 반환
//			System.out.println(bis.read() + " : " + bis.available()); // 0 : 0 => int형 반환
//			System.out.println();
//			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0 => 더이상 빼올 데이터가 없을때 -1 을 반환합니다.
//			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0 => 더이상 빼올 데이터가 없을때 -1 을 반환합니다.
//			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0 => 더이상 빼올 데이터가 없을때 -1 을 반환합니다.
//			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0 => 더이상 빼올 데이터가 없을때 -1 을 반환합니다.
//			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0 => 더이상 빼올 데이터가 없을때 -1 을 반환합니다.
//			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0 => 더이상 빼올 데이터가 없을때 -1 을 반환합니다.
//			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0 => 더이상 빼올 데이터가 없을때 -1 을 반환합니다.
//			System.out.println(bis.read() + " : " + bis.available()); // -1 : 0 => 더이상 빼올 데이터가 없을때 -1 을 반환합니다.

			while (bis.available() > 0) {
				
				System.out.println((byte) bis.read());

			}

			bis.close(); // 열어주엇으면 반듯이 닫아 주어야 합니다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
