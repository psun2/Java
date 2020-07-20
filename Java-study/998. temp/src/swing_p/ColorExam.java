package swing_p;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ColorExam extends JFrame {

	public ColorExam(String title) {
		super(title);

		setBounds(10, 10, 700, 600);

		Color[] colors = { new Color(255, 0, 0), new Color(255, 94, 0), new Color(255, 255, 0), new Color(0, 255, 0),
				new Color(0, 0, 255), new Color(26, 35, 126), new Color(95, 0, 255)};

		setLayout(new GridLayout(colors.length, 1));

		for (Color color : colors) {
//			JButton colorBtn = new JButton();
			JLabel colorLabel = new JLabel();
			colorLabel.setOpaque(true); // 투명도를 없애 보일 수 있게 합니다.
			colorLabel.setBackground(color);
			add(colorLabel);
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		new ColorExam("무지개");

	}

}
