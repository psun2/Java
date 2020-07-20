package io_p;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			FileInputStream fis = new FileInputStream("img/son_2.jpg");
			FileOutputStream fos = new FileOutputStream("dst/child_2.jpg");

			byte[] buf = new byte[20];

			while (fis.available() > 0) {
				int len = fis.read(buf);
				System.out.print(new String(buf, 0, len)); // 여기선 한글깨짐
				fos.write(buf, 0, len); // 여긴 제대로 됩니다.
				// 중간에 읽어들이는 것과 파일에 쓰는것은 차이가 있습니다.

			}

			fos.close();
			fis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		file.

	}

}
