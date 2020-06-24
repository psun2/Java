package theory.menu;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

class RadioButtonMenu extends JFrame {

	public RadioButtonMenu(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(600, 400, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		// 메뉴 안에 메뉴가 들어가고 그 메뉴가 Item을 가지는 구조를 합니다.

		JMenu menu = new JMenu("JRadioButtonMenu");
		menuBar.add(menu);

		JRadioButtonMenuItem menu_item1 = new JRadioButtonMenuItem("데스크탑");
		JRadioButtonMenuItem menu_item2 = new JRadioButtonMenuItem("태블릿", true);
		// 버튼과 마찬가지로 default 값 설정가능
		// true 를 지우거나, false로 바꿀시
		// 초기에 아무것도 선택 되지 않은 상태로 나오게 됩니다.
		JRadioButtonMenuItem menu_item3 = new JRadioButtonMenuItem("휴대폰");

		menu.add(menu_item1);
		menu.add(menu_item2);
		menu.add(menu_item3);

		ButtonGroup bg = new ButtonGroup();
		bg.add(menu_item1);
		bg.add(menu_item2);
		bg.add(menu_item3);
		// 버튼 구룹으로 묶어줌으로써 셋중 하나만 선택되게 할 수 있습니다.

		setVisible(true);
	}
}

public class JRadioButtonMenuItemMain {

	public static void main(String[] args) {

		new RadioButtonMenu("JRadioButtonMenu");

	}

}