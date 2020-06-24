package theory.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class TextField {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JTextField");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		JLabel label = new JLabel("라벨이 나와요");
		label.setBounds(30, 30, 100, 100);
		frame.add(label);

		JTextField input = new JTextField("playHolder");
		input.setBounds(30, 140, 100, 100);
		frame.add(input);

		JTextField input2 = new JTextField();
		input2.setBounds(30, 250, 100, 100);
		frame.add(input2);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
