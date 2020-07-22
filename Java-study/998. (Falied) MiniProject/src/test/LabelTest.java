package test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JButton;

public class LabelTest extends JFrame {

	public LabelTest() {
		// TODO Auto-generated constructor stub
		setSize(500, 600); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon().getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.pink); // 임시 : 개발중 알아보기 편하도록 프레임 색깔 설정

		JLabel asd = new JLabel(new ImageIcon("./images/1.png"));
		asd.setBounds(200,200,150,150);
		asd.setBackground(Color.pink);
		asd.setOpaque(true);
		add(asd);

		


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

	}

	class RoundBorder implements Border {

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			// TODO Auto-generated method stub

		}

		@Override
		public Insets getBorderInsets(Component c) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isBorderOpaque() {
			// TODO Auto-generated method stub
			return false;
		}

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

	}

	public static void main(String[] args) {
		new LabelTest();
	}
}
