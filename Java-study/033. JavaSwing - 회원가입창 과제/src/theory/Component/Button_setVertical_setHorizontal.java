package theory.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class Button_setVertical_setHorizontal {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Button_setVertical_setHorizontal");
		frame.setBounds(10, 10, 400, 400);
		frame.setLayout(null); // 레이아웃을 사용자 설정으로...

		javax.swing.ImageIcon icon = new javax.swing.ImageIcon("./exam_img/naver.png");
		JButton btn1 = new JButton("버튼1", icon);
		btn1.setBounds(10, 10, 300, 200);

		// Horizontal : 수평
//		btn1.setHorizontalTextPosition(SwingConstants.RIGHT);
		btn1.setHorizontalTextPosition(SwingConstants.LEFT);
//		btn1.setHorizontalTextPosition(SwingConstants.CENTER);

		// Vertical : 수직
//		btn1.setVerticalTextPosition(SwingConstants.TOP);
//		btn1.setVerticalTextPosition(SwingConstants.BOTTOM);

		// btn1.setHorizontalTextPosition(0); // 짝수 // left // center // right
		// btn1.setVerticalTextPosition(0); // 홀수 // top // bottom

		frame.add(btn1);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
