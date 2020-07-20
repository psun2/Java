package swing_p;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

class Game extends JFrame {

	public Game(String title) {
		super(title);
		setBounds(10, 10, 800, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu1 = new JMenu("게임(G)");
		JMenu menu2 = new JMenu("도움말(H)");

		menuBar.add(menu1);
		menu1.setMnemonic(KeyEvent.VK_G);

		menuBar.add(menu2);
		menu2.setMnemonic(KeyEvent.VK_H);

		JMenuItem menu1_item1 = new JMenuItem("새 게임(N)");
		JMenuItem menu1_item2 = new JMenuItem("게임 선택(G)");
		JMenuItem menu1_item3 = new JMenuItem("실행 취소(U)");
		JMenuItem menu1_item4 = new JMenuItem("힌트(H)");
		JMenuItem menu1_item5 = new JMenuItem("통계(S)");
		JMenuItem menu1_item6 = new JMenuItem("옵션(O)");
		JMenuItem menu1_item7 = new JMenuItem("모양 변경(A)");
		JMenuItem menu1_item8 = new JMenuItem("끝내기(X)");

		menu1.add(menu1_item1);
		menu1_item1.setAccelerator(KeyStroke.getKeyStroke("F2"));
		menu1_item1.setMnemonic(KeyEvent.VK_N);

		menu1.add(menu1_item2);
		menu1_item2.setAccelerator(KeyStroke.getKeyStroke("F3"));
		menu1_item2.setMnemonic(KeyEvent.VK_G);

		menu1.addSeparator(); // 경계선

		menu1.add(menu1_item3);
		menu1_item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		menu1_item3.setMnemonic(KeyEvent.VK_U);
		menu1_item3.setEnabled(false); // 비활성화

		menu1.add(menu1_item4);
		menu1_item4.setAccelerator(KeyStroke.getKeyStroke("H"));
		menu1_item4.setMnemonic(KeyEvent.VK_H);

		menu1.add(menu1_item5);
		menu1_item5.setAccelerator(KeyStroke.getKeyStroke("F4"));
		menu1_item5.setMnemonic(KeyEvent.VK_S);

		menu1.add(menu1_item6);
		menu1_item6.setAccelerator(KeyStroke.getKeyStroke("F5"));
		menu1_item6.setMnemonic(KeyEvent.VK_O);

		menu1.add(menu1_item7);
		menu1_item7.setAccelerator(KeyStroke.getKeyStroke("F7"));
		menu1_item7.setMnemonic(KeyEvent.VK_A);

		menu1.addSeparator(); // 경계선

		menu1.add(menu1_item8);
		menu1_item8.setMnemonic(KeyEvent.VK_X);

		JMenuItem menu2_item1 = new JMenuItem("도움말 보기(V)");
		JMenuItem menu2_item2 = new JMenuItem(title + " 정보(A)");
		JMenuItem menu2_item3 = new JMenuItem("온라인에서 다른 게임 보기(M)");

		menu2.add(menu2_item1);
		menu2_item1.setAccelerator(KeyStroke.getKeyStroke("F1"));
		menu2_item1.setMnemonic(KeyEvent.VK_V);

		menu2.add(menu2_item2);
		menu2_item2.setMnemonic(KeyEvent.VK_A);

		menu2.addSeparator(); // 경계선

		menu2.add(menu2_item3);
		menu2_item3.setMnemonic(KeyEvent.VK_M);

		ImageIcon icon = new ImageIcon("img/game.png");
		JButton game = new JButton(icon);
		game.setBounds(0, 0, 800, 400);
		add(game);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

public class GameMenuExam {

	public static void main(String[] args) {

		new Game("프리셀");

	}

}
