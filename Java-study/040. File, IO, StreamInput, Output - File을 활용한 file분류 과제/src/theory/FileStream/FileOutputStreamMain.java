package theory.FileStream;

import java.io.File;
import java.io.FileOutputStream;

public class FileOutputStreamMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 읽을때 input
		// 저장 output
		// 1byte 기반 스트림

		try {

			new File("예제폴더").mkdir();
			// 경로가 존재 하지 않으면 파일이 생기지 않습니다.
			FileOutputStream fos = new FileOutputStream("예제폴더/exam1.txt");
			// 파일 형태로 저장 해야 하기 때문에 ByteArrayOutputStream 과는 달리
			// 저장 위치가 필요 합니다.

			fos.write('a');
			fos.write('s');
			fos.write('d');
			fos.write('f');
			// asdf

			// Error
//			fos.write("\n"); // byte가 기본 형 이기 때문에 byte형이 아니면 들어가지 않습니다.
			fos.write('\n'); // 엔터
			fos.write('\n'); // 엔터

			String str = "여긴 참나무숲\t장수 풍뎅이와\n사슴 벌레가 살지\n낮엔 숨어있다\t밤엔 살금살금\n신나게 놀러다녀\n\n";

			// String 을 byte 배열로 바꾸어 Output 할 수 있습니다.
			byte[] arr = str.getBytes();

			for (byte b : arr) {
				fos.write(b);
			}
			// byte OutPut 결과물
//			여긴 참나무숲	장수 풍뎅이와
//			사슴 벌레가 살지
//			낮엔 숨어있다	밤엔 살금살금
//			신나게 놀러다녀

			// char 형태로 들어가긴 합니다... // but 들어갈때 이상하게 들어 갑니다.
			char[] arr2 = str.toCharArray();

			for (char c : arr2) {
				fos.write(c);
			}
			// char OutPut 결과물
//			�4 8�42	� ��t@
//			�� �

			fos.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
