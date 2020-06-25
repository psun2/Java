package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

class ToolBar extends JFrame {

	public ToolBar(String title) {
		setTitle(title);
		init();
	}

	void init() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.println(screen); // java.awt.Dimension[width=1920,height=1080]
		int xpos = (int) (screen.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2);
		int width = 400;
		int height = 400;
		setBounds(xpos - (width / 2), ypos - (height / 2), width, height);
		setIconImage(new ImageIcon("위메프img/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 툴바 안에는 모든 컴포넌트 들이 들어 갑니다....

		JToolBar toolBar = new JToolBar("툴바");
		toolBar.setBackground(Color.pink);

		JButton btn = new JButton("파일");
		btn.setToolTipText("파일열기"); // 마우스 오버시 상태를 보여 줍니다.

		toolBar.add(new JButton(new ImageIcon("위메프img/휴대폰.png")));
		toolBar.add(new JLabel("휴대폰모드 : "));

		JComboBox<String> box = new JComboBox<String>();
		box.addItem("일반");
		box.addItem("광학");
		box.addItem("접근");
		box.addItem("전방");
		box.setSelectedItem("접근");
		toolBar.add(box);

		toolBar.add(btn);

		add(toolBar, "South"); // Layout의 defalut가 borderLayout 이므로 동서남북 center의 값을 줄 수 있다.
		setVisible(true);

	}

}

public class JToolBarMain {

	public static void main(String[] args) {
		new ToolBar("JToolBar");
	}

}
