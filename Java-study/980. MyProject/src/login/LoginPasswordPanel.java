package login;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPasswordPanel extends JPanel {

	public LoginPasswordPanel() {
		// TODO Auto-generated constructor stub

		setBackground(Color.blue);
		

		
		JLabel pwLb = new JLabel("비밀번호 : ");
		add(pwLb);
		
		JTextField pwInput = new JTextField(10);
		add(pwInput);
		
	}

}
