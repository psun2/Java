package test;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestSwingComponentType extends JFrame {

	public TestSwingComponentType() {
		// TODO Auto-generated constructor stub

		setSize(700, 700); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		getContentPane().setBackground(Color.blue);

		JPanel p = new JPanel();
		p.setBounds(0, 0, 700, 700);
		p.setBackground(Color.pink);
		add(p);

		JButton btn = new JButton();
		p.add(btn);

		System.out.println(p.getComponentCount()); // 버튼 1개

		JPanel p2 = new JPanel();
		p.add(p2);

		JLabel lb = new JLabel();
		p.add(lb);

		System.out.println(p.getComponentCount()); // 버튼 2개

		System.out.println(p.getComponents());

		for (Component c : p.getComponents()) {
			String comName = c.getClass().getName();
			int name = comName.lastIndexOf(".") + 1;
			System.out.println(name);
			System.out.println(comName.substring(name));
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

	}

	public static void main(String[] args) {
		new TestSwingComponentType();
	}

}
