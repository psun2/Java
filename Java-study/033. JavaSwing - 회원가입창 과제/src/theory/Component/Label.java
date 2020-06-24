package theory.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Label {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JLabel");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		JLabel label = new JLabel("라벨이 나와요");
		label.setBounds(30, 30, 100, 100);
		frame.add(label);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
