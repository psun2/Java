package theory.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class TextPane {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JTextPane");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		JLabel label = new JLabel("라벨이 나와요");
		label.setBounds(30, 30, 100, 100);
		frame.add(label);

//		JTextPane input = new JTextPane("playHolder");
		// 사용 불가
//		input.setBounds(30, 140, 100, 100);
//		frame.add(input);

		JTextPane input2 = new JTextPane();
		input2.setBounds(30, 250, 100, 100);
		frame.add(input2);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
