package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

// 인증을 도와주는 Authenticator 클래스를 상속 받습니다.

public class Gmail extends Authenticator {

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("tjddjs90test", "!@#$qwer1234");
		// 구글 => 해당 아이디 로그인 => 설정 => 로그인 및 보안 => 계정 액세스 권한을 가진 앱 => 보안 수준이 낮은 앱 => 사용함

	}
}
