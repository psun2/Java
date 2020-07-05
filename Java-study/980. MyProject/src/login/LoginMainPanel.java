package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

class LoginMainPanel extends JPanel {

	public LoginMainPanel() {
		// TODO Auto-generated constructor stub

		setBackground(Color.cyan);

		LoginIdPanel id = new LoginIdPanel();
		LoginPasswordPanel pw = new LoginPasswordPanel();
		

		
		System.out.println(id.getWidth());
		id.setPreferredSize(new Dimension(400,100));
//		id.setSize(this.getWidth(), 50);

		add(id);
		add(pw);

	}

}