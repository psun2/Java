package theory.Component;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Box_JCheckBox {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JCheckBox");
		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		JCheckBox cb1 = new JCheckBox("독서");
		cb1.setBounds(20, 330, 70, 30);
		frame.add(cb1);

		JCheckBox cb2 = new JCheckBox("게임");
		cb2.setBounds(100, 330, 70, 30);
		frame.add(cb2);

		JCheckBox cb3 = new JCheckBox("잠자기");
		cb3.setBounds(180, 330, 70, 30);
		frame.add(cb3);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
