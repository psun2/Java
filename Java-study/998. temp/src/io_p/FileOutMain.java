package io_p;

import java.io.FileOutputStream;

public class FileOutMain {

	public static void main(String[] args) {

		try {

			FileOutputStream fos = new FileOutputStream("fff/aaa.txt");

			String str = "sadjakjfklasjkldjakfasd asdafasdas asd";
			str = "여긴 참나무숲\t장수 풍뎅이와\n사슴 벌레가 살지\n낮엔 숨어있다\t밤엔 살금살금\n신나게 놀러다녀";
			byte[] arr = str.getBytes();

			fos.write(arr);

//			fos.write('a');
//			fos.write('s');
//			fos.write('d');
//			fos.write('f');

			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
