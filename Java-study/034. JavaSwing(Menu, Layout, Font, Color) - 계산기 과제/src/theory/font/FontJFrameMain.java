package theory.font;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

class FontJFrame extends JFrame {

	public FontJFrame(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(10, 10, 700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		Font[] font = ge.getAllFonts();

		Font[] ffArr = { new Font("휴먼매직체", Font.ITALIC, 40), new Font("휴먼둥근헤드라인", Font.ITALIC, 40),
				new Font("휴먼편지체", Font.ITALIC, 40), new Font("Arial", Font.PLAIN, 40),
				new Font("Arial", Font.ITALIC, 40), new Font("Arial", Font.BOLD, 40),

		};

		String str = "코리아 IT 아카1234 @ Academy";

		for (Font fo : ffArr) {
			JLabel lb = new JLabel(str);
			lb.setFont(fo);
			add(lb);
		}

		setLayout(new GridLayout(ffArr.length, 1));

		setVisible(true);

	}

}

public class FontJFrameMain {

	public static void main(String[] args) {

		new FontJFrame("setFont");

	}

}
