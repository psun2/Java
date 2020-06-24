package theory.Component;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Color {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Color");
		frame.setBounds(10, 10, 400, 400);
		frame.setLayout(null); // 레이아웃을 사용자 설정으로...

		JButton btn1 = new JButton("버튼1");
		btn1.setBounds(10, 10, 70, 30);
		frame.add(btn1);

		JButton btn2 = new JButton("버튼2");
		btn2.setBounds(90, 10, 70, 30);
		btn2.setBackground(java.awt.Color.pink);
		frame.add(btn2);

		JButton btn3 = new JButton("버튼3");
		btn3.setBounds(180, 10, 70, 30);
		btn3.setForeground(java.awt.Color.blue);
		frame.add(btn3);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
