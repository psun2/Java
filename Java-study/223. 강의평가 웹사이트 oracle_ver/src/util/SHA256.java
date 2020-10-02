package util;

import java.security.MessageDigest;

public class SHA256 {

	public static String getSHA256(String input) {
		StringBuffer result = new StringBuffer();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] salt = "Hello This is Salt".getBytes();
			digest.reset();
			digest.update(salt);

			byte[] chars = digest.digest(input.getBytes("UTF-8"));

			for (byte c : chars) {
				String hex = Integer.toHexString(0xff & c);
				// 0xff 16 진수
				// 0xff : 이것이 16진수 
				// & 엔드연산을 통해서 좌측이 true
				// 우측이 true 면 
				// value 는 우측 값
				// 좌측이 true 일때
				// 무조건 우측의 값이 true 여야 true
				if (hex.length() == 1) {
					result.append("0");
					System.out.println("hex.length() == 1: " + c);
					System.out.println("hex.length() == 1: " + hex);
				}
				result.append(hex);
			}

			return result.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}

}
