package theory.Component;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Button_setEnabled {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Button setEnabled");
		frame.setBounds(10, 10, 400, 400);
		frame.setLayout(null); // 레이아웃을 사용자 설정으로...

		JButton btn1 = new JButton("버튼1");
		btn1.setBounds(10, 10, 70, 30);
		btn1.setEnabled(true);
		frame.add(btn1);

		JButton btn2 = new JButton("버튼2");
		btn2.setBounds(90, 10, 70, 30);
		btn2.setEnabled(false); // 버튼 비활성화
		frame.add(btn2);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
