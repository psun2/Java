package digest;

import java.security.MessageDigest;
import java.util.Arrays;

public class DigestMain {

	public static void main(String[] args) {

		try {
			// TODO: MessageDigest 인스턴스 생성
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			System.out.println(digest);
			// SHA-256 Message Digest from SUN, <initialized>

			// TODO: hash값 리셋
			digest.reset();
			System.out.println(digest);
			// SHA-256 Message Digest from SUN, <initialized>

			// TODO: 해커들의 공격이있을시 반환할 salt
			byte[] salt = "안녕하세요 해커님".getBytes();
			System.out.println(Arrays.toString(salt));
			// [-20, -107, -120, -21, -123, -107, -19, -107, -104, -20, -124, -72, -20,
			// -102, -108, 32, -19, -107, -76, -20, -69, -92, -21, -117, -104]

			StringBuffer binary = new StringBuffer(); // 2진수
			StringBuffer octal = new StringBuffer(); // 8진수
			StringBuffer decimal = new StringBuffer(); // 10진수
			StringBuffer hexaDecimal = new StringBuffer(); // 16진수

			for (int i = 0; i < salt.length; i++) {
				binary.append(Integer.toBinaryString(salt[i]));
				octal.append(Integer.toOctalString(salt[i]));
				decimal.append(Integer.toString(salt[i]));
				hexaDecimal.append(Integer.toHexString(salt[i]));
			}

			System.out.println();
			System.out.println("2진수 : " + binary);
			System.out.println("8진수 : " + octal);
			System.out.println("10진수 : " + decimal);
			System.out.println("16진수 : " + hexaDecimal);
			System.out.println();

			// TODO: hash값 업데이트
			digest.update(salt);
			System.out.println(digest);
			// SHA-256 Message Digest from SUN, <in progress>

			// digest 를 이용하면 똑같은 값이 변환됩니다.
			byte[] temp = "안녕하세요 해커님".getBytes("UTF-8");
			byte[] chars = digest.digest("안녕하세요 해커님".getBytes("UTF-8"));
			System.out.println();
			System.out.println(Arrays.toString(temp));
			System.out.println();
			System.out.println(Arrays.toString(chars));
			System.out.println();

			StringBuffer tempBuffer2 = new StringBuffer();
			StringBuffer resultString2 = new StringBuffer();
			StringBuffer result2 = new StringBuffer();

			for (byte b : temp) {

				tempBuffer2.append(Integer.toString(b));

				resultString2.append(Integer.toString(b));

				// 한번더 암호화를 하기 위해 16진수로 변경
				result2.append(Integer.toHexString(b));
			}

			System.out.println("tempBuffer2 : " + tempBuffer2);
			System.out.println("resultString2 : " + resultString2);
			System.out.println("result2 : " + result2);

			System.out.println();

			StringBuffer tempBuffer = new StringBuffer();
			StringBuffer resultString = new StringBuffer();
			StringBuffer resultHex = new StringBuffer();
			StringBuffer result = new StringBuffer();

			for (byte b : chars) {

				tempBuffer.append(Integer.toString(b));

				resultString.append(Integer.toString(b));

				resultHex.append(Integer.toHexString(b));

				// 한번더 암호화를 하기 위해 16진수로 변경
				result.append(Integer.toHexString(0xff & b));
			}

			System.out.println("tempBuffer : " + tempBuffer);
			System.out.println("resultString : " + resultString);
			System.out.println("resultHex : " + resultHex);
			System.out.println("result : " + result);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
