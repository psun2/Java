package utill;

import java.security.MessageDigest;

// 회원 가입 이후 이메일 인증을 위하여
// 사용자가 특정 URL 로 진입하여 이메일 인증을 할 수 있게 도와 주는 기능을 담은 클래스
public class SHA256 {

	public static String getSHA256(String input) {
		// 특정한 입력값 : 우리는 Email 에 Hash 값을 적용 하여 반환

		// StringBuffer는 multi thread환경에서 다른 값을 변경하지 못하도록 하므로 web이나 소켓환경과 같이 비동기로 동작하는
		// 경우가 많을 때는 StringBuffer를 사용하는 것이 안전할 것이다.
		StringBuffer result = new StringBuffer();
		// String은 새로운 값을 할당할 때마다 새로 생성되기 때문이다. 이와 달리 StringBuffer는 값은 memory에 append하는
		// 방식으로 클래스를 직접생성하지 않는다. 논리적으로 따져보면 클래스가 생성될 때 method들과 variable도 같이 생성되는데,
		// StringBuffer는 이런 시간을 사용하지 않는다.

		// 또한 지금은 한 번만 생성되었지만 수십번 String이 더해지는 경우에는 각 String의 주소값이 stack에 쌓이고 클래스들은
		// Garbage Collector가 호출되기 전까지 heap에 지속적으로 쌓이게 된다. 메모리 관리적인 측면에서는 치명적이라고 볼 수 있다.

		// String에서 저장되는 문자열은 알고보면 char의 배열형태로 저장되며 이 값들은 외부에서 접근할 수 없도록 private으로 보호된다.
		// 또한 final형이기 때문에 초기값으로 주어진 String의 값은 불변으로 바뀔 수가 없게 되는 것이다.

		try {
			// MessageDigest 클래스 API 문서 URL

			// http://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html
			// [출처] [Java] 암호화를 위한 MessageDigest 클래스, Java로 MD5, SHA-256 암호화|작성자 자바킹

			// 실제로 사용자가 입력한 값을 SHA-256 으로 알고리즘을 적용 할 수 있도록 만듭니다.
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// SHA (Sechre Hash Standard)

			// SHA(Secure Hash Algorithm, 안전한 해시 알고리즘) 함수들은 서로 관련된 암호학적 해시 함수들의 모음이다.

			// 여러 종류가 존재하며 그중 SHA-1은 SHA 함수들 중 가장 많이 쓰이며, TLS, SSL, PGP, SSH, IPSec 등 많은 보안
			// 프로토콜과 프로그램에서 사용되고 있다. SHA-1은 이전에 널리 사용되던 MD5를 대신해서 쓰이기도 한다. 혹자는 좀 더 중요한 기술에는
			// SHA-256이나 그 이상의 알고리즘을 사용할 것을 권장한다.
			// [출처] [Java] 암호화를 위한 MessageDigest 클래스, Java로 MD5, SHA-256 암호화|작성자 자바킹

			////////////////////////////////////////////////////////////////////////////////////////
			// SHA-256은 충돌 공격에 대해 128 비트 보안을 제공하기위한 256 비트 해시 함수이고, SHA-512는 256 비트 보안을
			// 제공하기위한 512 비트 해시 함수입니다. 384 비트 해시는 SHA-512 출력을 잘라서 얻을 수 있습니다.
			///////////////////////////////////////////////////////////////////////////////////////

			// 단순하게 SHA-256 을 적용시 해커에 의해 해킹을 당할 위험이 높아짐으로
			// 일반적으로 salt 값을 적용 합니다.
			byte[] salt = "Hello! This is Salt".getBytes();

			digest.reset();
			digest.update(salt);

			// Hash 를 적용한 값을 chars 변수에 담아 줍니다.
			byte[] chars = digest.digest(input.getBytes("UTF-8"));

			// byte배열을 문자열 형태로 만드는 과정
			for (int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]); // 16진수 형태로 출력 할 수 있게 만들어 줌
				if (hex.length() == 1)
					result.append("0"); // 0을 더하여 한번더 암호화를 함
				result.append(hex);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result.toString();
	}

}
