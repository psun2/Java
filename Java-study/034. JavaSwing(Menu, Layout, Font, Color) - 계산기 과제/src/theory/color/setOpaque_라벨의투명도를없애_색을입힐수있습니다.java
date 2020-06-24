package theory.color;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

class setOpaque extends JFrame {

	public setOpaque(String title) {
		super(title);

		setBounds(10, 10, 700, 600);

		Color[] colors = { new Color(255, 0, 0), new Color(255, 94, 0), new Color(255, 255, 0), new Color(0, 255, 0),
				new Color(0, 0, 255), new Color(26, 35, 126), new Color(95, 0, 255) };

		setLayout(new GridLayout(colors.length, 1));

		for (Color color : colors) {
			JLabel colorLabel = new JLabel();
			colorLabel.setBackground(color);
			//
			colorLabel.setOpaque(true); // 투명도를 없애 보일 수 있게 합니다.
			//
			add(colorLabel);
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

public class setOpaque_라벨의투명도를없애_색을입힐수있습니다 {

	public static void main(String[] args) {
		new setOpaque("setOpaque_라벨의투명도를없애_색을입힐수있습니다");
	}

}
