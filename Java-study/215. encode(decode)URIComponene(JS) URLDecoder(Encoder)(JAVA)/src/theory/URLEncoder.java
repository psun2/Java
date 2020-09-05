package theory;

public class URLEncoder {
	
	public static void main(String[] args) {
		
		// 결과 : JS 의 encodeURIComponene 와 같은 기능
		
		try {
			String str = "한글 문자열!@#english";
			System.out.println(java.net.URLEncoder.encode(str, "UTF-8"));
			// %ED%95%9C%EA%B8%80+%EB%AC%B8%EC%9E%90%EC%97%B4%21%40%23english
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
