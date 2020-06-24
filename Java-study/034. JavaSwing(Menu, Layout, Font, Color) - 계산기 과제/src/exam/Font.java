package exam;

import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Font extends JFrame {

	public Font() {

		setBounds(50, 50, 350, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		java.awt.Font[] fonts = ge.getAllFonts();

		for (java.awt.Font font : fonts) {
//			System.out.println(font);
			System.out.println(font.getFontName());
		}

		setVisible(true);

	}

	public static void main(String[] args) {

		new Font();

	}

}
