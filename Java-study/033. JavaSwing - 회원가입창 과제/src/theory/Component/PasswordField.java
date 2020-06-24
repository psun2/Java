package theory.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class PasswordField {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JTextField");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		JLabel label = new JLabel("라벨이 나와요");
		label.setBounds(30, 30, 100, 100);
		frame.add(label);

		JPasswordField pwInput = new JPasswordField("playHolder");
		pwInput.setBounds(30, 140, 100, 100);
		frame.add(pwInput);
		
		JPasswordField pwInput2 = new JPasswordField();
		pwInput2.setBounds(30, 250, 100, 100);
		frame.add(pwInput2);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
