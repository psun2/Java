package theory.layout;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class Grid extends JFrame {

	public Grid(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(10, 10, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		GridLayout(int rows, int cols, int hgap, int vgap)
		setLayout(new GridLayout(4, 3, 10, 10));
		// 행을 맞추고 열을 맞춥니다.
		// 행 : 가로
		// 열 : 세로
		// 행 ex : 21 ~ 24 Line 의 행은 4행

		for (int i = 0; i < 27; i++) {
			add(new JButton("btn" + i));
		}

		setVisible(true);

	}
}

public class GridLayoutMain {

	public static void main(String[] args) {

		new Grid("GridLayout");

	}

}
