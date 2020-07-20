package io_p;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopyMain {

	public static void main(String[] args) {
		try {

			FileInputStream fis = new FileInputStream("fff/bbb.txt");
			FileOutputStream fos = new FileOutputStream("dst/qqq.txt");

			byte[] buf = new byte[20];

			while (fis.available() > 0) {
				int len = fis.read(buf);
				System.out.print(new String(buf, 0, len)); // 여기선 한글깨짐
				fos.write(buf, 0, len); // 여긴 제대로 됩니다.
				// 중간에 읽어들이는 것과 파일에 쓰는것은 차이가 있습니다.

			}

			fos.close();
			fis.close();

			/// fff/bbb.txt -> dst/qqq.txt 복사

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
