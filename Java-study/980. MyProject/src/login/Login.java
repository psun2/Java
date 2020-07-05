package login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame {

	private int password = 000000;

	public Login() {
		// TODO Auto-generated constructor stub
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 400;
		int height = 500;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setTitle("로그인");

		add(new LoginMainPanel());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Login();
	}

}
