package theory.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class addSeparator extends JFrame {

	public addSeparator(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(600, 400, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar(); // 메뉴 바 생성

//		add(menuBar); // add 가 아님
		setJMenuBar(menuBar);

		JMenu menu1 = new JMenu("메뉴1"); // 메뉴생성
		menuBar.add(menu1);

		JMenuItem item1 = new JMenuItem("아이템1"); // 아이템 생성
		menu1.add(item1);

		JMenuItem item2 = new JMenuItem("아이템2"); // 아이템 생성
		menu1.add(item2);
		menu1.addSeparator();
		// 경계가 나뉘는건 item이지만....
		// 해당 메뉴에서 경계선을 생성해야 하기 때문에
		// 접근을 menu로 합니다.

		JMenuItem item3 = new JMenuItem("아이템3"); // 아이템 생성
		menu1.add(item3);

		JMenuItem item4 = new JMenuItem("아이템4"); // 아이템 생성
		menu1.add(item4);

//////////////////////////////////////////////////////////////////////

		JMenu menu2 = new JMenu("메뉴2"); // 메뉴생성
		menuBar.add(menu2);

		JMenu menu2_1 = new JMenu("메뉴2_1"); // 메뉴생성
		menu2.add(menu2_1);

		JMenuItem menu2_1_item1 = new JMenuItem("메뉴2_1_아이템1");
		menu2_1.add(menu2_1_item1);

		JMenuItem menu2_1_item2 = new JMenuItem("메뉴2_1_아이템2");
		menu2_1.add(menu2_1_item2);
		menu2_1.addSeparator();

		JMenuItem menu2_1_item3 = new JMenuItem("메뉴2_1_아이템3");
		menu2_1.add(menu2_1_item3);

		JMenu menu2_2 = new JMenu("메뉴2_2"); // 메뉴생성
		menu2.add(menu2_2);

		setVisible(true);

	}

}

public class addSeparator_경계선_생성 {

	public static void main(String[] args) {

		new addSeparator("경계선 생성");

	}

}
