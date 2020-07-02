package theory.ByteStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayOutputStreamMain {

	public static void main(String[] args) {

		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			System.out.println(bos.size()); // 0

			bos.write(12); // 데이타를 집어 넣을때 즉 빨때에서 바질때 => write 함수를 사용 하여 삽입 합니다.
			bos.write(24);
			bos.write(56);
			bos.write(12);
			bos.write(100);
			bos.write(12);
			bos.write(126);
			bos.write(128); // 형변환이 되어 들어 갑니다.
			bos.write(254); // 형변환이 되어 들어 갑니다.
			bos.write(255); // 형변환이 되어 들어 갑니다.
			bos.write(256); // 형변환이 되어 들어 갑니다.

			System.out.println(bos.size()); // 11

			byte[] arr = bos.toByteArray();
			System.out.println(Arrays.toString(arr)); // [12, 24, 56, 12, 100, 12, 126, -128, -2, -1, 0]
			System.out.println(arr.length); // 11
			System.out.println(bos.size()); // 11 => 모든 데이터가 저장소에 깔끔히 잘 들어 갔습니다.

			bos.close(); // 열어주엇으면 반듯이 닫아 주어야 합니다.

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
