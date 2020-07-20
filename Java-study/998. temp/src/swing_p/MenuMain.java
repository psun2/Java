package swing_p;

import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class MenuMain extends JFrame { // JFrame 를 상속받아 사용 가능

	public MenuMain() {

		setBounds(10, 10, 300, 200);

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		JMenu menu1 = new JMenu("파일");
		JMenu menu2 = new JMenu("편집");
		JMenu menu3 = new JMenu("보기");

		mb.add(menu1);
		mb.add(menu2);
		mb.add(menu3);

		ImageIcon icon = new ImageIcon("img/camera.png");
		JMenuItem item1 = new JMenuItem("새로 만들기", icon);
		JMenuItem item2 = new JMenuItem("개로 만들기");
		item2.setText("닭으로 만들기"); // 메뉴명 변경
		JMenuItem item3 = new JMenuItem("소로 만들기");
		item3.setAccelerator(KeyStroke.getKeyStroke('a')); // 단축키 설정
		JMenuItem item4 = new JMenuItem("가로 만들기");
		item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));

		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu1.addSeparator(); // 경계선 생성
		menu1.add(item4);

		JMenu menu2_1 = new JMenu("용지");
		JMenu menu2_2 = new JMenu("속성");
		menu2.add(menu2_1);
		menu2.add(menu2_2);

		JMenuItem item2_1 = new JMenuItem("A4");
		JMenuItem item2_2 = new JMenuItem("B4");
		JMenuItem item2_3 = new JMenuItem("C4");
		menu2_1.add(item2_1);
		menu2_1.add(item2_2);
		menu2_1.addSeparator(); // 경계선 생성
		menu2_1.add(item2_3);

		item2_2.setEnabled(false); // 비활성화
		item2_3.setSelected(false);

		// 체크박스 메뉴 아이템
		JCheckBoxMenuItem item3_1 = new JCheckBoxMenuItem("안내선");
		JCheckBoxMenuItem item3_2 = new JCheckBoxMenuItem("격자");

		JRadioButtonMenuItem item3_3 = new JRadioButtonMenuItem("데스크탑");
		JRadioButtonMenuItem item3_4 = new JRadioButtonMenuItem("태블릿");
		JRadioButtonMenuItem item3_5 = new JRadioButtonMenuItem("휴대폰");

		menu3.add(item3_1);
		menu3.add(item3_2);

		menu3.add(item3_3);
		menu3.add(item3_4);
		menu3.add(item3_5);

		ButtonGroup bg = new ButtonGroup();
		bg.add(item3_3);
		bg.add(item3_4);
		bg.add(item3_5);

		// 토글버튼은 아이템이 없다.

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

		new MenuMain();

	}

}
