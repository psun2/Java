package theory.Component;

import javax.swing.ButtonGroup;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class Button_JRadioButton {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JRadioButton");
		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		ButtonGroup bg = new ButtonGroup(); // 한팀으로 설정
		JRadioButton rb1 = new JRadioButton("남자");
		rb1.setBounds(20, 290, 100, 30);
		frame.add(rb1);

		JRadioButton rb2 = new JRadioButton("여자", true); // 기본값 설정
		rb2.setBounds(120, 290, 100, 30);
		frame.add(rb2);

		JRadioButton rb3 = new JRadioButton("it인");
		rb3.setBounds(220, 290, 100, 30);
		frame.add(rb3);

		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
