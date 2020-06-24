package theory.Component;

import javax.swing.JFrame;

public class Frame_Layout {

	public static void main(String[] args) {

		JFrame frame = new JFrame("setLayout");
		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
