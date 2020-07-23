package an.adminLogin;

import javax.swing.JFrame;

import an.adminMain.AdminMainControl;

public class an_adminLoginControl {

	an_adminLoginView loginview; // 로그인 뷰 불러오기.

	ChangePWFrame cPWFrame;
	an_adminLoginControl adiminControl;

	public an_adminLoginControl() {
		loginview = new an_adminLoginView(this);
	}

	public an_adminLoginControl(an_adminLoginView loginView) {
		this.loginview = loginView;
	}

	void loginViewClose() {

		// loginview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginview.dispose();
		new AdminMainControl(this);
	}

	void changePW(String aD_PW) {
		AdminLogDTO dto = new AdminLogDTO();
		dto.setAD_PW(aD_PW);
		new AdminLogDAO().modify(dto);
	}

}
