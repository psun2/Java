package login;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginIdPanel extends JPanel {

	public LoginIdPanel() {
		// TODO Auto-generated constructor stub

		setBackground(Color.red);

		JLabel idLb = new JLabel("아이디 : ");
		add(idLb);

		JTextField idInput = new JTextField(10);
		add(idInput);

//		idLb.setVerticalAlignment(JLabel.BOTTOM); // 콘텐츠가 전부를 차지 했을때만 가능
//		idLb.setHorizontalAlignment(); // 콘텐츠가 전부를 차지 했을때만 가능
//		idInput.setHorizontalAlignment(JTextField.LEFT); // 콘텐츠가 전부를 차지 했을때만 가능

	}

}
