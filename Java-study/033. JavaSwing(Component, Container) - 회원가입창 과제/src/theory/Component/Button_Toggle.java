package theory.Component;

import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class Button_Toggle {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Button Toggle");
		frame.setBounds(10, 10, 400, 400);
		frame.setLayout(null); // 레이아웃을 사용자 설정으로...

		JToggleButton tbtn1 = new JToggleButton("토글버튼");
		tbtn1.setBounds(10, 10, 100, 30);
		frame.add(tbtn1);

		JToggleButton tbtn2 = new JToggleButton("토글버튼");
		tbtn2.setBounds(120, 10, 100, 30);
		frame.add(tbtn2);
		
		JToggleButton tbtn3 = new JToggleButton("토글버튼");
		tbtn3.setBounds(230, 10, 100, 30);
		frame.add(tbtn3);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
