package swing_p;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FontMain extends JFrame {

	public FontMain() {

		setBounds(10, 10, 700, 600);

		// 운영체제로 부터 정보를 가져 옵니다...
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		Font[] fonts = ge.getAllFonts();

		for (Font font : fonts) {
//			System.out.println(font);
			System.out.println(font.getFontName());
		}

		Font[] ffArr = { new Font("휴먼매직체", Font.ITALIC, 40), new Font("휴먼둥근헤드라인", Font.ITALIC, 40),
				new Font("휴먼편지체", Font.ITALIC, 40), new Font("Arial", Font.PLAIN, 40),
				new Font("Arial", Font.ITALIC, 40), new Font("Arial", Font.BOLD, 40),

		};

		String str = "코리아 IT 아카1234 @ Academy";
//		JLabel lb = new JLabel(str);

//		add(lb);
//		lb.setFont(new Font("휴먼매직체", Font.ITALIC, 40));

		for (Font font : ffArr) {
			JLabel lb = new JLabel(str);
			lb.setFont(font);
			add(lb);
		}

//		setLayout(new GridLayout(6, 1));
		setLayout(new GridLayout(ffArr.length, 1));

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

		new FontMain();

	}

}
