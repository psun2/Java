package theory.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class MultiMenuItem extends JFrame {

	public MultiMenuItem(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(600, 400, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		// 메뉴 안에 메뉴가 들어가고 그 메뉴가 Item을 가지는 구조를 합니다.

		JMenu menu2 = new JMenu("편집");
		menuBar.add(menu2);

		JMenu menu2_menu1 = new JMenu("용지");
		menu2.add(menu2_menu1);

		JMenu menu2_menu2 = new JMenu("속성");
		menu2.add(menu2_menu2);

		JMenuItem menu1_item1 = new JMenuItem("A4");
		menu2_menu1.add(menu1_item1);

		JMenuItem menu1_item2 = new JMenuItem("B4");
		menu2_menu1.add(menu1_item2);

		JMenuItem menu1_item3 = new JMenuItem("C4");
		menu2_menu1.add(menu1_item3);

		setVisible(true);
	}

}

public class MultiMenuItem_다중메뉴아이템 {

	public static void main(String[] args) {

		new MultiMenuItem("다중메뉴아이템");

	}

}
