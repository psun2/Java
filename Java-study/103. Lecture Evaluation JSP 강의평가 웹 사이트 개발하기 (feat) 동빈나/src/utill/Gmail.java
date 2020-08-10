package utill;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

// extends Authenticator : 인증 수행을 도와주는 클래스를 상속 받습니다.

public class Gmail extends Authenticator {

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		// return super.getPasswordAuthentication();
		
		// 실제로 사용자에게 메일을 보내는 사람 (관리자) 전송하는 사람의 아이디 비밀번호
		// return new PasswordAuthentication("아이디", "비밀번호");
		return new PasswordAuthentication("tjddjs90test", "!@#$qwer1234");
	}

}
