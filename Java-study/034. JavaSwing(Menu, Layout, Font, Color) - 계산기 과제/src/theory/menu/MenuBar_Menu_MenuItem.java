package theory.menu;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

class MenuExam extends JFrame {

	public MenuExam(String title) {
		super(title);
		init();
	}

	void init() {

		setBounds(600, 400, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar(); // 메뉴 바 생성

//		add(menuBar); // add 가 아님
		setJMenuBar(menuBar);

		JMenu menu1 = new JMenu("파일"); // 메뉴생성
		menuBar.add(menu1);

		ImageIcon icon = new ImageIcon("theory.menu_img/camera.png");
		JMenuItem menu1_item1 = new JMenuItem("새로 만들기", icon);
		// 메뉴 아이템에 아이콘 사용 가능
		menu1.add(menu1_item1);

		JMenuItem menu1_item2 = new JMenuItem("개로 만들기");
		menu1.add(menu1_item2);
		menu1_item2.setText("닭으로 만들기(메뉴아이템 명 변경 ok)"); // 메뉴명 변경가능

		JMenuItem menu1_item3 = new JMenuItem("소로 만들기");
		menu1.add(menu1_item3);
		menu1_item3.setAccelerator(KeyStroke.getKeyStroke('a'));
//		menu1_item3.setAccelerator(KeyStroke.getKeyStroke("a")); // String 은 먹히지 않음

		JMenuItem menu1_item4 = new JMenuItem("개로 만들기");
		menu1_item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));

		menu1.add(menu1_item4);

		setVisible(true);

	}

}

public class MenuBar_Menu_MenuItem {

	public static void main(String[] args) {

		new MenuExam("메뉴구성 연습");

	}

}
