package GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GridBagLayoutTest extends JFrame {

	GridBagLayout grid = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public GridBagLayoutTest() {
		// TODO Auto-generated constructor stub

		setLayout(grid);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0; // 초기 가로 비율
		gbc.weighty = 1.0; // 초기 세로 비율

		JButton btn1 = new JButton();

		make(btn1, 0, 0, 1, 1);

		add(btn1);

		JButton btn2 = new JButton();
		make(btn2, 1, 0, 1, 1);
		add(btn2);

		JButton btn3 = new JButton();
		make(btn3, 2, 0, 1, 1);
		add(btn3);

		JButton btn4 = new JButton();
		make(btn4, 3, 0, 1, 3);
		add(btn4);

		JButton btn5 = new JButton();
		make(btn5, 0, 1, 3, 1);
		add(btn5);

		JButton btn6 = new JButton();
		make(btn6, 0, 2, 3, 1);
		add(btn6);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임의 x버튼을 활성화하여 닫기버튼이 실행가능해짐

		setSize(300, 300); // 프레임 사이즈 지정

		setVisible(true); // 프레임을 보이게 함

		setLocationRelativeTo(null); // 프레임 실행시 위치 중앙

	}

	public void make(JComponent c, int x, int y, int w, int h) {

		gbc.gridx = x;

		gbc.gridy = y;

		gbc.gridwidth = w;

		gbc.gridheight = h;

		grid.setConstraints(c, gbc);

		// GridBagLayout의 GridBagConstraints의 set하는 방법
	}

	public static void main(String[] args) {
		new GridBagLayoutTest();
	}

}
