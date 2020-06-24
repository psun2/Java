package theory.Component;

import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Box_JComboBox_Vector {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JComboBox_Vector");
		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		String[] strings = { "다음", "구글", "야후" };
		Vector<String> emails = new Vector<String>();

		for (String str : strings) {
			emails.add(str);
		}

		JComboBox<String> emailBox = new JComboBox<String>(emails);
		emailBox.setBounds(20, 250, 120, 30);
		emailBox.setSelectedItem("구글");

		frame.add(emailBox);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
