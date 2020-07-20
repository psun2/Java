package swing_p;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class testButton extends JFrame {

	public testButton() {

		setBounds(0, 0, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JButton btn = new JButton("test버튼");
		btn.setSize(50, 50);
//		btn.setBorder(null);
		btn.setBorderPainted(false); // 위와 같은 역할
		btn.setBackground(new Color(255));
//		btn.setMargin(MarginBorder.);
		add(btn);

		setVisible(true);

	}

	public static void main(String[] args) {

		new testButton();
	}

}
