package theory.Component;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ImageIcon {

	public static void main(String[] args) {

		JFrame frame = new JFrame("ImageIcon");
		frame.setBounds(10, 10, 400, 400);
		frame.setLayout(null); // 레이아웃을 사용자 설정으로...

		javax.swing.ImageIcon icon = new javax.swing.ImageIcon("./exam_img/naver.png");

		JButton btn1 = new JButton(icon);
		btn1.setBounds(10, 10, 240, 40);
		frame.add(btn1);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
