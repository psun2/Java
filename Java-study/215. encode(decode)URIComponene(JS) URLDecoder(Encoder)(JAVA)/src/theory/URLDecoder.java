package theory;

import java.io.UnsupportedEncodingException;

public class URLDecoder {

	public static void main(String[] args) {

		// 결과 : 서버로 부터 받은 쿼리 스트링을 UTF-8로 다시 디코드 하여 java 파일 내에서 사용할때 쓰임
		// 서버로 쿼리스트링으로 넘겨줄땐 Encoding 사용

		try {
			String str = "한글 문자열!@#english";
			System.out.println(java.net.URLDecoder.decode(str, "UTF-8"));
			// 한글 문자열!@#english
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
