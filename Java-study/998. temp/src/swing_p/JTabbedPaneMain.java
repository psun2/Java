package swing_p;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JTabbedPaneMain extends JFrame {

	public JTabbedPaneMain() {

		setBounds(100, 100, 700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JTabbedPane");

		JTabbedPane tab = new JTabbedPane();

		Color[] cc = { Color.pink, Color.cyan, Color.green };

		for (int i = 0; i < cc.length; i++) {
			JPanel pp = new JPanel();
			pp.setBackground(cc[i]);
			tab.addTab("화면" + i, pp);
		}

		add(tab);

		setVisible(true);
	}

	public static void main(String[] args) {

		new JTabbedPaneMain();

	}

}
