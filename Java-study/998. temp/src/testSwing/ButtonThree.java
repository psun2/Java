package testSwing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ButtonThree extends JFrame {
	JButton b1 = new JButton("1");
	JButton b2 = new JButton("2");
	JButton b3 = new JButton("3");
	JButton b4 = new JButton("안녕버튼");

	public ButtonThree() {
		// 제목 설정
		super("제목부분입니다.");

		// 레이아웃 설정
		this.setLayout(new FlowLayout());

		// JPanel ??
//		JPanel pane = new JPanel();

		// input 추가
//		JTextField textPeriod = new JTextField(5);

		// pane
//		pane.add(textPeriod);

		Panel panel1 = new Panel();
		// 색을 빨강으로 지정
		panel1.setBackground(Color.RED);
		// 추가
		add(panel1);
		// 크기
		this.setSize(400, 400);
		this.setVisible(true);

		// 버튼 추가
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);

		// 프레임 크기 지정
		this.setSize(400, 400);

		// 프레임 보이도록 설정
		this.setVisible(true);

		// X버튼 눌렀을 때 닫히도록 설정 (프로그램 종료)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// 실행
		new ButtonThree();

		String str1 = "aa";
		String str2 = "bb";
		String str3 = "cc";
		String str4 = "aa";
		System.out.println(str1.compareTo(str2));
		System.out.println(str1.compareTo(str3));
		System.out.println(str2.compareTo(str3));
		System.out.println(str1.compareTo(str4)); // true == 0
	}

}