package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

class ColorChooser extends JFrame {

	public ColorChooser(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 400;
		int height = 400;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);
		setBounds(x, y, width, height);
		setIconImage(new ImageIcon("./위메프img/성별.png").getImage());

		init();
	}

	void init() {

		JColorChooser cc = new JColorChooser();

		// Color 을 반환 하는 함수
		// Color showDialog(Component component, String title, Color initialColor)
		Color ccRet = cc.showDialog(null, "색깔고르라", Color.yellow);

		// System.out.println(cc);

		// 라벨은 css를 예로 들면 inline 요소 입니다.
		JLabel lb = new JLabel("선택한 색");
		// setOpaque : 불투명
		lb.setOpaque(true); // 라벨에 입힌 색을 표시해줍니다.
//		lb.setBounds(0, 0, 50, 50);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		if (ccRet != null) {
			lb.setBackground(ccRet);
			System.out.println("색을 바꿉니다.");
		} else {
			System.out.println("색을 선택하지 않아 색을 바꾸지 못했습니다.");
		}

		add(lb);

		setVisible(true);
	}

}

public class Chooser_JColorChooser {

	public static void main(String[] args) {
		new ColorChooser("JColorChooser");
	}

}
