package GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridBagLayoutMain2 extends JFrame {
	JPanel p1 = new JPanel();

	JButton b1 = new JButton("1");

	JButton b2 = new JButton("2");

	JButton b3 = new JButton("3");

	JButton b4 = new JButton("4");

	JButton b5 = new JButton("5");

	JButton b6 = new JButton("6");

	JButton b7 = new JButton("7");

	public GridBagLayoutMain2() {
		// TODO Auto-generated constructor stub
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;

		gbc.weightx = 1.0;

		gbc.weighty = 1.0;

		// y축의 비율은 1이며 아래는 0.5이다. 그렇다면 아래 전까지 모든 y셀은 두배가된다

		// 1번과 4번 버튼의 y축의 길이는 6번과 7번축보다 2배라는 뜻

		gbc.gridx = 0;

		gbc.gridy = 0;

		gbc.gridwidth = 1;

		gbc.gridheight = 1;

		add(b1, gbc);

		gbc.gridx = 1;

		gbc.gridy = 0;

		gbc.gridwidth = 1;

		gbc.gridheight = 1;

		add(b2, gbc);

		gbc.gridx = 2;

		gbc.gridy = 0;

		gbc.gridwidth = 1;

		gbc.gridheight = 1;

		add(b3, gbc);

		gbc.gridx = 0;

		gbc.gridy = 1;

		gbc.gridwidth = 2;

		gbc.gridheight = 1;

		add(b4, gbc);

		gbc.gridx = 2;

		gbc.gridy = 1;

		gbc.gridwidth = 1;

		gbc.gridheight = 1;

		add(b5, gbc);

		gbc.weighty = 0.5;

		// y축의 비율은 1이며 아래는 0.5이다. 그렇다면 아래 전까지 모든 y셀은 두배가된다

		// 1번과 4번 버튼의 y축의 길이는 6번과 7번축보다 2배라는 뜻

		gbc.gridx = 0;

		gbc.gridy = 2;

		gbc.gridwidth = 3;

		gbc.gridheight = 1;

		add(b6, gbc);

		gbc.gridx = 0;

		gbc.gridy = 3;

		gbc.gridwidth = 1;

		gbc.gridheight = 1;

		add(b7, gbc);

		// ------------------------------------------------

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임의 x버튼을 활성화하여 닫기버튼이 실행가능해짐

		setSize(500, 500); // 프레임 사이즈 지정

		setVisible(true); // 프레임을 보이게 함

		setLocationRelativeTo(null); // 프레임 실행시 위치 중앙
	}

	public static void main(String[] args) {
		new GridBagLayoutMain2();
	}

}
