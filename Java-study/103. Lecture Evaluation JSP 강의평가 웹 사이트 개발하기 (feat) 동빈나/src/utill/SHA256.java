package utill;

import java.security.MessageDigest;

// 회원 가입 이후 이메일 인증을 위하여
// 사용자가 특정 URL 로 진입하여 이메일 인증을 할 수 있게 도와 주는 기능을 담은 클래스
public class SHA256 {

	public static String getSHA256(String input) {
		// 특정한 입력값 : 우리는 Email 에 Hash 값을 적용 하여 반환

		StringBuffer result = new StringBuffer();

		try {
			// 실제로 사용자가 입력한 값을 SHA-256 으로 알고리즘을 적용 할 수 있도록 만듭니다.
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// 단순하게 SHA-256 을 적용시 해커에 의해 해킹을 당할 위험이 높아짐으로
			// 일반적으로 salt 값을 적용 합니다.
			byte[] salt = "Hello! This is Salt".getBytes();

			digest.reset();
			digest.update(salt);

			// Hash 를 적용한 값을 chars 변수에 담아 줍니다.
			byte[] chars = digest.digest(input.getBytes("UTF-8"));

			// byte배열을 문자열 형태로 만드는 과정
			for (int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if (hex.length() == 1)
					result.append("0"); // 16진수 형태로 출력 할 수 있게 만들어 줌
				result.append(hex);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result.toString();
	}

}
