package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

class TabbedPane extends JFrame {

	public TabbedPane(String title) {
		setTitle(title);
		init();
	}

	void init() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = screen.width / 2; // 가로 사이즈에 반에서 부터 시작 해야하니 /2 를 해줍니다.
		int ypos = screen.height / 2;
		int width = 400;
		int height = 400;

		setBounds(xpos - (width / 2), ypos - (height / 2), width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setIconImage(new ImageIcon("./위메프img/logo.png").getImage());

		JTabbedPane tab = new JTabbedPane();

		Color[] cc = { Color.pink, Color.cyan, Color.green };

		for (int i = 0; i < cc.length; i++) {
			JPanel pp = new JPanel();
			pp.setBackground(cc[i]);
			tab.addTab("화면" + i, pp); // tab 에 addTab 을 해줄땐 String(tab name)과 component 를 들고 갑니다.
		}

		add(tab);

		setVisible(true);

	}

}

public class JTabbedPaneMain {

	public static void main(String[] args) {
		new TabbedPane("JTabbedPane");
	}

}
