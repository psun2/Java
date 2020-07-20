package swing_p;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ColorMain extends JFrame {

	public ColorMain(String title) {
		super(title);
		setBounds(10, 10, 700, 600);

//		Color(float r, float g, float b, float a)
//		Color(int r, int g, int b, int a)
		Color[] ccArr = { Color.red, new Color(255, 0, 0), new Color(255, 0, 0, 100), new Color(1.0f, 0.0f, 0.0f),
				new Color(1.0f, 0.0f, 0.0f, 0.5f), new Color(255), new Color(16711680), new Color(255, 120, 255) };

		// GridLayout(int rows, int cols, int hgap, int vgap)

		setLayout(new GridLayout(ccArr.length, 1));

		for (Color color : ccArr) {
			JButton btn = new JButton();
			btn.setBackground(color);
			add(btn);
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		new ColorMain("Color");

	}

}
