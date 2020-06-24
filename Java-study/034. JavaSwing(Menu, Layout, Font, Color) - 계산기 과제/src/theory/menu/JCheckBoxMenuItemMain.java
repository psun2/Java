package theory.menu;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

class CheckBoxMenuItem extends JFrame {

	public CheckBoxMenuItem(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(600, 400, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		// 메뉴 안에 메뉴가 들어가고 그 메뉴가 Item을 가지는 구조를 합니다.

		JMenu menu = new JMenu("JCheckBoxMenuItem");
		menuBar.add(menu);

		JCheckBoxMenuItem menu_item1 = new JCheckBoxMenuItem("안내선");
		JCheckBoxMenuItem menu_item2 = new JCheckBoxMenuItem("격자");
		menu.add(menu_item1);
		menu.add(menu_item2);
		
		
		JRadioButtonMenuItem item3_3 = new JRadioButtonMenuItem("데스크탑");
		JRadioButtonMenuItem item3_4 = new JRadioButtonMenuItem("태블릿");
		JRadioButtonMenuItem item3_5 = new JRadioButtonMenuItem("휴대폰");

		setVisible(true);
	}
}

public class JCheckBoxMenuItemMain {

	public static void main(String[] args) {

		new CheckBoxMenuItem("JCheckBoxMenuItem");

	}

}
