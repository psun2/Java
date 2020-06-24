package theory.Component;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Button {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Button Exam");
		frame.setBounds(10, 10, 400, 400);
		frame.setLayout(null); // 레이아웃을 사용자 설정으로...

		JButton btn1 = new JButton("버튼1");
		btn1.setBounds(10, 10, 70, 30);
		frame.add(btn1);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
