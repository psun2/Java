package swing_p;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LayoutMain {

	public static void main(String[] args) {

		JFrame f1 = new JFrame("Border");
		f1.setBounds(10, 10, 300, 200);
		// 대소문자 구분 필요..... 소문자 일시 error

		// 기본값 : f1.setLayout(new BorderLayout());
//		f1.setLayout(new FlowLayout());
		f1.add(new JButton("btn1"), "North");
		f1.add("South", new JButton("btn2"));
		f1.add("East", new JButton("btn3"));
		f1.add(new JButton("btn4"), "West");
		f1.add(new JButton("btn5"), "Center");
		// 위치가 겹쳐 한가지 밖에 안나옴

		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

///////////////////////////////////////////////////////////////////////////////

		JFrame f2 = new JFrame("Flow");
		f2.setBounds(320, 10, 300, 200);

//		FlowLayout(int align, int hgap, int vgap)

		// 알아서 가운데 정렬을 합니다.
//		f2.setLayout(new FlowLayout());

		// 오른쪽 정렬
//		f2.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// 왼쪽 정렬
		f2.setLayout(new FlowLayout(FlowLayout.RIGHT, 100, 10));
		// 가로 간격 : 100, 위 아래 간격 : 10

		for (int i = 0; i < 10; i++) {
			f2.add(new JButton("btn" + i));
		}

		f2.setVisible(true);
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

///////////////////////////////////////////////////////////////////////////////

		JFrame f3 = new JFrame("Grid");
		f3.setBounds(630, 10, 300, 200);

//		GridLayout(int rows, int cols, int hgap, int vgap)
		f3.setLayout(new GridLayout(4, 3, 10, 10));
		// 행을 맞추고 열을 맞춥니다.
		// 행 : 가로
		// 열 : 세로

		for (int i = 0; i < 27; i++) {
			f3.add(new JButton("btn" + i));
		}

		f3.setVisible(true);
		f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

///////////////////////////////////////////////////////////////////////////////

		JFrame f4 = new JFrame("Box");
		f4.setBounds(940, 10, 300, 200);

//		f4.setLayout(new BoxLayout(f4.getContentPane(), BoxLayout.Y_AXIS));
		f4.setLayout(new BoxLayout(f4.getContentPane(), BoxLayout.X_AXIS));

		for (int i = 0; i < 5; i++) {
			f4.add(new JButton("btn" + i));
		}

		JPanel pan = new JPanel();
		pan.setSize(300, 100);
		pan.setBackground(Color.PINK);
		pan.setLayout(new GridLayout(2, 5));
		for (int i = 0; i < 5; i++) {
			pan.add(new JButton("btn" + i));
		}
		f4.add(pan);

		f4.setVisible(true);
		f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

///////////////////////////////////////////////////////////////////////////////

		JFrame f5 = new JFrame("Card");
		f5.setBounds(1250, 10, 300, 200);

		CardLayout card = new CardLayout();
		f5.setLayout(card);

		Color[] colors = { new Color(255, 0, 0), new Color(255, 94, 0), new Color(255, 255, 0), new Color(0, 255, 0),
				new Color(0, 0, 255), new Color(26, 35, 126), new Color(95, 0, 255) };

		String index = "빨주노초파남보";

		for (int i = 0; i < colors.length; i++) {
			JPanel pp = new JPanel();
			pp.setBackground(colors[i]);
			f5.add(pp, index.substring(i, i + 1));
		}

		f5.setVisible(true);
		f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int i = 0;

		while (true) {
			try {
				Thread.sleep(1);
				card.show(f5.getContentPane(), index.substring(i, i + 1));
				i++;
				i %= index.length();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
