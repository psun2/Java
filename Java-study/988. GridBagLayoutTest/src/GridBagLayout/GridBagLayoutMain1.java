package GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridBagLayoutMain1 extends JFrame {

	JPanel p1 = new JPanel();

	JButton b1 = new JButton("1");

	JButton b2 = new JButton("2");

	public GridBagLayoutMain1() {
		// TODO Auto-generated constructor stub

		p1.setLayout(new GridBagLayout()); // 레이아웃 생성자

		GridBagConstraints gbc = new GridBagConstraints();

		// GridBagLayout에 필요한 GridBagConstraints의 생성자

		gbc.fill = GridBagConstraints.BOTH;

		// x,y축 다채움

		gbc.weightx = 0.2;// 비율이 0.2:0.1이므로 버튼의 크기는 가로축으로 2배

		gbc.gridx = 0;

		gbc.gridy = 0; // 버튼이 두개로 0,0 기준으로 생성

		p1.add(b1, gbc);

		gbc.weightx = 0.1; // 비율이 0.2:0.1이므로 버튼의 크기는 가로축으로 1배

		gbc.gridx = 1;

		gbc.gridy = 0; // 버튼이 두개로 1,0 가 버튼 생성시작점

		p1.add(b2, gbc); //

		add(p1);

		// ------------------------------------------------

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임의 x버튼을 활성화하여 닫기버튼이 실행가능해짐

		setSize(500, 500); // 프레임 사이즈 지정

		setVisible(true); // 프레임을 보이게 함

		setLocationRelativeTo(null); // 프레임 실행시 위치 중앙
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GridBagLayoutMain1();
	}

}
